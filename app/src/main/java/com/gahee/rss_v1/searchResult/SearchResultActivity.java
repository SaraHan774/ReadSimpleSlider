package com.gahee.rss_v1.searchResult;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gahee.rss_v1.news.model.Article;
import com.gahee.rss_v1.R;

import java.util.ArrayList;
import java.util.Objects;

import static com.gahee.rss_v1.helpers.Constants.SEARCH_RESULT_INTENT;

public class SearchResultActivity extends AppCompatActivity {

    private static final String TAG = "SearchResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getString(R.string.search_result_appbar_title));

        ArrayList<Article> articleSearchResults = Objects.requireNonNull(getIntent().getExtras()).getParcelableArrayList(SEARCH_RESULT_INTENT);

        Log.d(TAG, "results : " + articleSearchResults);
        if(articleSearchResults != null){
            //set up recycler view & adapter
            RecyclerView rv_searchResults = findViewById(R.id.rv_search_results);
            rv_searchResults.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rv_searchResults.setLayoutManager(layoutManager);

            SearchResultAdapter searchResultAdapter = new SearchResultAdapter(this, articleSearchResults);
            searchResultAdapter.notifyDataSetChanged(); //just in case
            rv_searchResults.setAdapter(searchResultAdapter);
        }else{
            Toast.makeText(this, getString(R.string.search_result_fail), Toast.LENGTH_SHORT).show();
        }

    }

}
