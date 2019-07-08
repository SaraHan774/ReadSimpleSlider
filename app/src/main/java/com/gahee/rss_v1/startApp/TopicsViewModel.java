package com.gahee.rss_v1.startApp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TopicsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<String>> topics;

    public LiveData<ArrayList<String>> getTopics(){
        if(topics == null){
            topics = new MutableLiveData<>();
        }
        return topics;
    }


    public void setTopics(MutableLiveData<ArrayList<String>> topics) {
        this.topics = topics;
    }
}
