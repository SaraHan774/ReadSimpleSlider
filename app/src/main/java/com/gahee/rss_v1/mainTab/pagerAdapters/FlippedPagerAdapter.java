package com.gahee.rss_v1.mainTab.pagerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;

import java.util.ArrayList;

public class FlippedPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Article> articles;

    public FlippedPagerAdapter(Context context, ArrayList<Article> articles){
        this.context = context;
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_news_flipped_slider, container, false);

        //load web view
        WebView webView = view.findViewById(R.id.web_view);
        webView.loadUrl(articles.get(position).getArticleLink());

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
