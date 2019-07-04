package com.gahee.rss_v1.CNN;

import java.util.EnumMap;
import java.util.Iterator;

public class TopicsList{

    private String []  TopicsArray
            = {
            "Top Stories", "World", "Africa", "Americas", "Asia",
            "Europe", "Middle East", "U.S.", "Money", "Technology",
            "Science & Space", "Entertainment", "World Sport", "Football",
            "Golf", "Motorsport", "Tennis", "Travel", "Video", "Most Recent"
    };

    public String[] getTopicsArray() {
        return TopicsArray;
    }

    enum TopicsEnum{
        TOP_STORIES, WORLD, AFRICA, AMERICAS, ASIA,
        EUROPE, MIDDLE_EAST, US, MONEY, TECHNOLOGY,
        SCIENCE_AND_SPACE, ENTERTAINMENT, WORLD_SPORT, FOOTBALL,
        GOLF, MOTOR_SPORT, TENNIS, TRAVEL, VIDEO, MOST_RECENT
    }

    EnumMap<TopicsEnum, String> enumMap = new EnumMap<TopicsEnum, String>(TopicsEnum.class);

    public TopicsList(){
      TopicsEnum [] topicsEnums = TopicsEnum.values();
      for(int i = 0; i < topicsEnums.length; i++){
         enumMap.put(topicsEnums[i], TopicsArray[i]);
      }
    }

}
