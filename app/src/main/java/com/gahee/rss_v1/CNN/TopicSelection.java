package com.gahee.rss_v1.CNN;

import android.content.Context;
import android.util.Log;
import android.widget.Switch;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.helpers.LinearSearch;
import com.gahee.rss_v1.startApp.PhotoUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class TopicSelection {
    private static final String TAG = TopicSelection.class.getSimpleName();

    private String [] topics = {
            "Top Stories", "World", "Africa", "Americas", "Asia",
            "Europe", "Middle East", "U.S.", "Money", "Technology",
            "Science & Space", "Entertainment", "World Sport", "Football",
            "Golf", "Motorsport", "Tennis", "Travel", "Video News", "Most Recent"
    };

    private Context context;

    private ArrayList<Article> articles;
    private static final String BASE_URL = "http://rss.cnn.com/rss/";
    private XMLUtils xmlUtils = new XMLUtils(buildRetrofit());

    public TopicSelection(Context context){
        this.context = context;
    }

    private Retrofit buildRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        return retrofit;
    }

    //in the start activity's select interest page, when the user selects a topic,
    //put this topic into this method and query proper data
                                            //get the topics from the database. make this topic list a live data.

    //for(int i =0; i < size of the topic list; i++){ query this when user hits refresh button
    // fetchDataBasedOnUserSelection(array[i])
    // }
    public void fetchDataBasedOnUserSelection(String topic){
        int userSelectionIndex = LinearSearch.findIndex(topics, topic);
        //need to do this whenever the user refreshes the news tab.
        Log.d(TAG, "user selection : " + topic + " index : " + userSelectionIndex + " topics array " + topics);
        switch(userSelectionIndex){
            case 0:
                xmlUtils.getTopStories(context);
                break;
            case 1:
                xmlUtils.getWorldEdition(context);
                break;
            case 2:
                xmlUtils.getAfricaEdition(context);
                break;
            case 3:
                xmlUtils.getAmericasEdition(context);
                break;
            case 4:
                xmlUtils.getAsiaEdition(context);
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
        }

    }

    public ArrayList<String> getNewsTopics(){
        return xmlUtils.getNewsTopics();
    }


}
