package com.gahee.rss_v1.roomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "topicStringsTable")
public class TopicStrings {

    @ColumnInfo
    @PrimaryKey
    @NonNull
    public final String topicString;

    public TopicStrings(@NonNull String topicString) {
        this.topicString = topicString;
    }

    @NonNull
    public String getTopicString() {
        return topicString;
    }
}
