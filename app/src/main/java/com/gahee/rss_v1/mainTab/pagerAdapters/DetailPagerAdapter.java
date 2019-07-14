package com.gahee.rss_v1.mainTab.pagerAdapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.viewpager.widget.PagerAdapter;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wajahatkarim3.clapfab.ClapFAB;

import java.util.ArrayList;

public class DetailPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Article> articles;
    private int adapterPosition;

    public DetailPagerAdapter(Context context, ArrayList<Article> articles, int adapterPosition){
        this.context =context;
        this.articles = articles;
        this.adapterPosition = adapterPosition;
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.article_detail_slider, container, false);

        String link = articles.get(position).getArticleLink();

        loadWebView(view, position, link);
        loadClapButton(view);
        loadShareButton(view, link);
        loadOpenInBrowserButton(view);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    private void loadWebView(View view, int position, String link){

        WebView webView = view.findViewById(R.id.article_detail_web_view);
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(link);


    }

    private void loadClapButton(View view){

        //save article info to the database and display it in the my favorites section

        ClapFAB clapFAB = (ClapFAB) view.findViewById(R.id.clapFAB);
        clapFAB.setClapListener(new ClapFAB.OnClapListener() {
            @Override
            public void onFabClapped(@NonNull ClapFAB clapFab, int count, boolean isMaxReached) {
                // count is the current count of the clapping
                // isMaxReached is true when button has already reached the maximum count
                // and is not being clapped anymore. Otherwise its false
            }
        });
    }

    private void loadShareButton(View view, String link){

        FloatingActionButton shareButton = view.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //share the link
            }
        });
    }

    private void loadOpenInBrowserButton(View view){

        FloatingActionButton browserButton = view.findViewById(R.id.open_in_web_button);
        browserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open link in user selected browser
            }
        });
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
