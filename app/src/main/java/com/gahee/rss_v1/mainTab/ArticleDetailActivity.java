package com.gahee.rss_v1.mainTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.pagerAdapters.DetailPagerAdapter;

import java.util.ArrayList;

import static com.gahee.rss_v1.helpers.Constants.ADAPTER_POSITION;
import static com.gahee.rss_v1.helpers.Constants.ARTICLES;

public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ArrayList<Article> articles = getIntent().getParcelableArrayListExtra(ARTICLES);
        int adapterPosition = getIntent().getExtras().getInt(ADAPTER_POSITION);

        ViewPager viewPager = findViewById(R.id.article_detail_view_pager);
        PagerAdapter pagerAdapter = new DetailPagerAdapter(this, articles, adapterPosition);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(adapterPosition);

    }

}
