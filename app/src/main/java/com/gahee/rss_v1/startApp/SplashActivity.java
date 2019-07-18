package com.gahee.rss_v1.startApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.gahee.rss_v1.helpers.CheckIfNew;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.MainTabActivity;
import com.gahee.rss_v1.remoteDataSource.RepositoryRemote;
import com.gahee.rss_v1.roomDatabase.TopicStrings;
import com.gahee.rss_v1.roomDatabase.ViewModelRoom;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    private List<TopicStrings> topicStrings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ViewModelRoom viewModelRoom = ViewModelProviders.of(this).get(ViewModelRoom.class);

        CheckIfNew checkIfNew = new CheckIfNew(this);
        boolean isFirstTimeLaunch = checkIfNew.getIsFirstTimeLaunch();
        if(isFirstTimeLaunch){
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
            finish();
        }else{
            viewModelRoom.getTopicStrings().observe(this, new Observer<List<TopicStrings>>() {
                @Override
                public void onChanged(List<TopicStrings> topicStrings) {
                    if(RepositoryRemote.getInstance().getList() != null){
                        RepositoryRemote.getInstance().getList().clear();
                    }
                    for(int i = 0; i < topicStrings.size(); i++){
                        RepositoryRemote.getInstance().requestDataAsync(topicStrings.get(i).getTopicString());
                    }
                    Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }



}
