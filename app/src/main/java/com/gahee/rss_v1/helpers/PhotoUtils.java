package com.gahee.rss_v1.helpers;

import com.gahee.rss_v1.R;

public class PhotoUtils{

    private final String [] Topics
            = {
            "Top Stories", "World", "Africa", "Americas", "Asia",
            "Europe", "Middle East", "U.S.", "Money", "Technology",
            "Science & Space", "Entertainment", "World Sport", "Football",
            "Golf", "Motorsport", "Tennis", "Travel", "Most Recent"
    };

    private final int [] Photos = {
            R.drawable.top_stories, R.drawable.world, R.drawable.africa,
            R.drawable.americas, R.drawable.asia, R.drawable.europe, R.drawable.middle_east,
            R.drawable.us, R.drawable.money, R.drawable.technology, R.drawable.science,
            R.drawable.entertainment, R.drawable.world_sport, R.drawable.football,
            R.drawable.golf, R.drawable.motorsport, R.drawable.tennis,
            R.drawable.travel, R.drawable.latest_news
    };

    public String [] getTopicsOfPhotos(){
        return Topics;
    }

    public int [] getPhotos(){return Photos; }

}
