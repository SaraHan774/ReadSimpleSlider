package com.gahee.rss_v1.roomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myFavoritesTable")
public class FavEntities {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "articleTitle")
    public String articleTitle;

    @ColumnInfo(name = "articleLink")
    public String articleLink;

    @ColumnInfo(name = "thumbnail")
    public String thumbnail;

    @ColumnInfo(name = "pubDate")
    public String pubDate;

    @ColumnInfo(name = "articleDescription")
    public String articleDescription;

}
