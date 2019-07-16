package com.gahee.rss_v1.mainTab.pagerAdapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.gahee.rss_v1.news.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.roomDatabase.FavEntities;
import com.gahee.rss_v1.roomDatabase.RepositoryRoom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wajahatkarim3.clapfab.ClapFAB;

import java.util.ArrayList;

public class DetailPagerAdapter extends PagerAdapter {

    private static final String TAG = "DetailPagerAdapter";

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Article> articles;
    private RepositoryRoom repositoryRoom;
    private FirebaseAnalytics mFirebaseAnalytics;

    public DetailPagerAdapter(Context context, ArrayList<Article> articles){
        this.context =context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        Log.d(TAG, "instantiateItem");
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if (layoutInflater != null) {
            view = layoutInflater.inflate(R.layout.article_detail_slider, container, false);
        }

        String link = articles.get(position).getArticleLink();
        String articleTitle = articles.get(position).getArticleTitle();

        assert view != null;
        loadWebView(view, link);
        loadClapButton(view, position);
        loadShareButton(view, link, articleTitle);
        loadOpenInBrowserButton(view, link);

        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem");
        View view = (View) object;
        container.removeView(view);
    }

    private void loadWebView(View view, String link){

        WebView webView = view.findViewById(R.id.article_detail_web_view);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        Warning:(84, 9) Using `setJavaScriptEnabled` can introduce XSS vulnerabilities into your application, review carefully.
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(link);

    }


    private void loadClapButton(View view, final int position){

        repositoryRoom = new RepositoryRoom(context);
        //save article info to the database and display it in the my favorites section
        final String articleTitle = articles.get(position).getArticleTitle();
        final String link = articles.get(position).getArticleLink();
        final String media = articles.get(position).getMedia();
        final String pubDate = articles.get(position).getPubDate();
        final String articleDesc = articles.get(position).getArticleDescription();

        ClapFAB clapFAB = view.findViewById(R.id.clapFAB);
        clapFAB.setClapListener(new ClapFAB.OnClapListener() {
            @Override
            public void onFabClapped(@NonNull ClapFAB clapFab, int count, boolean isMaxReached) {
                //insert whenever the star button is clicked
                repositoryRoom.insertMyFav(new FavEntities(count, articleTitle, link,
                        media, pubDate, articleDesc), context);

                //to avoid storing duplicate articles by only updating the "star count" column
                repositoryRoom.updateMyFavTable(count, articleTitle);
            }

        });

    }

    private void loadShareButton(View view, final String link, final String articleTitle){

        final FloatingActionButton shareButton = view.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //share the link
                shareUrlLink(link, articleTitle);
            }
        });
    }

    private void loadOpenInBrowserButton(View view, final String link){

        FloatingActionButton browserButton = view.findViewById(R.id.open_in_web_button);
        browserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                context.startActivity(intent);
            }
        });
    }


    private void shareUrlLink(String link, String articleTitle){
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_SUBJECT, articleTitle);
            share.putExtra(Intent.EXTRA_TEXT, link);
            context.startActivity(Intent.createChooser(share, "Share Article link!"));
    }



    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }




}
