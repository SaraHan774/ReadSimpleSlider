package com.gahee.rss_v1.roomDatabase;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myNewsTable")
public class NewsEntities {

    private static final String TAG = NewsEntities.class.getSimpleName();

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "topic")
    public final String topic;

    @ColumnInfo(name = "articleTitle")
    public final String articleTitle;

    @ColumnInfo(name = "articleLink")
    public final String articleLink;

    @ColumnInfo(name = "thumbnail")
    public final String thumbnail;

    @ColumnInfo(name = "pubDate")
    public final String pubDate;

    @ColumnInfo(name = "articleDescription")
    public final String articleDescription;

    public NewsEntities(@NonNull String topic, String articleTitle, String articleLink, String thumbnail, String pubDate, String articleDescription) {
        this.topic = topic;
        this.articleTitle = articleTitle;
        this.articleLink = articleLink;
        this.thumbnail = thumbnail;
        this.pubDate = pubDate;
        this.articleDescription = articleDescription;
    }

    @NonNull
    public String getTopic() {
        return topic;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getArticleDescription() {
        return articleDescription;
    }
}
