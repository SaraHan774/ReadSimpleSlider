package com.gahee.rss_v1.topicSelection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.MainTabActivity;
import com.gahee.rss_v1.remoteDataSource.RepositoryRemote;
import com.gahee.rss_v1.roomDatabase.TopicStrings;
import com.gahee.rss_v1.roomDatabase.ViewModelRoom;
import com.gahee.rss_v1.startApp.StartActivity;

import java.util.List;

public class TopicSelectionActivity extends AppCompatActivity {

    private static final String TAG = "TopicSelectionActivity";

    private RecyclerView recyclerView;
    private TopicSelectionAdapter topicSelectionAdapter;
    private ViewModelRoom viewModelRoom;
    private List<TopicStrings> topicStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        //set up recycler view
        recyclerView = findViewById(R.id.rv_topic_selection_setting);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //view model from the database which observes strings of selected topics
        viewModelRoom = ViewModelProviders.of(this).get(ViewModelRoom.class);
        viewModelRoom.getTopicStrings().observe(this, new Observer<List<TopicStrings>>() {
            @Override
            public void onChanged(List<TopicStrings> topicStrings) {
                //send topic strings to the adapter
                Log.d(TAG, "topic string observed ");
                topicSelectionAdapter = new TopicSelectionAdapter(TopicSelectionActivity.this, topicStrings);
                recyclerView.setAdapter(topicSelectionAdapter);
                TopicSelectionActivity.this.topicStrings = topicStrings;
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(RepositoryRemote.getInstance().getList() != null){
            RepositoryRemote.getInstance().getList().clear();
        }
        for(int i = 0; i < topicStrings.size(); i++){
            RepositoryRemote.getInstance().requestDataAsync(topicStrings.get(i).getTopicString());
        }
        Intent intent = new Intent(TopicSelectionActivity.this, MainTabActivity.class);
        startActivity(intent);
        finish();
    }
}
