package com.gahee.rss_v1.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Daos {

    //for my news tab
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsEntities newsEntities);

    @Query("SELECT * FROM myNewsTable WHERE topic = :topic")
    List<NewsEntities> loadNewsByTopic(String topic);

    @Query("DELETE FROM myNewsTable WHERE topic = :topic")
    void deleteByArticleTopic(String topic);

    //for my favorites tab
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMyFavorite(FavEntities favEntities);

    @Query("DELETE FROM myFavoritesTable WHERE articleTitle = :articleTitle")
    void deleteMyFavoriteByTitle(String articleTitle);

    @Query("SELECT * FROM myFavoritesTable ORDER BY pubDate")
    LiveData<List<FavEntities>> getMyFavoriteArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTopicString(TopicStrings topicStrings);

    @Query("SELECT * FROM topicStringsTable")
    LiveData<List<TopicStrings>> loadTopicStrings();

}
