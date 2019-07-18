package com.gahee.rss_v1.roomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myFavoritesTable")
public class FavEntities {


    @ColumnInfo(name = "count")
    public final int count;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "articleTitle")
    private final String articleTitle;

    @ColumnInfo(name = "articleLink")
    public final String articleLink;

    @ColumnInfo(name = "thumbnail")
    public final String thumbnail;

    @ColumnInfo(name = "pubDate")
    public final String pubDate;

    @ColumnInfo(name = "articleDescription")
    public final String articleDescription;

    public FavEntities(@NonNull int count, String articleTitle, String articleLink, String thumbnail, String pubDate, String articleDescription) {
        this.count = count;
        this.articleTitle = articleTitle;
        this.articleLink = articleLink;
        this.thumbnail = thumbnail;
        this.pubDate = pubDate;
        this.articleDescription = articleDescription;
    }

    public int getCount() {
        return count;
    }

    @NonNull
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
