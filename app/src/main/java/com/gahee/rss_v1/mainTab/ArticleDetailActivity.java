package com.gahee.rss_v1.mainTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.pagerAdapters.DetailPagerAdapter;
import com.gahee.rss_v1.remoteDataSource.RepositoryRemote;
import com.gahee.rss_v1.roomDatabase.FavEntities;
import com.gahee.rss_v1.roomDatabase.RepositoryRoom;

import java.util.ArrayList;

import static com.gahee.rss_v1.helpers.Constants.ADAPTER_POSITION;
import static com.gahee.rss_v1.helpers.Constants.ARTICLES;

public class ArticleDetailActivity extends AppCompatActivity {

    private static final String TAG = "ArticleDetailActivity";
    private RepositoryRoom repositoryRoom = new RepositoryRoom(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        final ArrayList<Article> articles = getIntent().getParcelableArrayListExtra(ARTICLES);
        int adapterPosition = getIntent().getExtras().getInt(ADAPTER_POSITION);

        final ViewPager viewPager = findViewById(R.id.article_detail_view_pager);
        final PagerAdapter pagerAdapter = new DetailPagerAdapter(this, articles, adapterPosition);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(adapterPosition);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "on resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on destroy");
    }
}
