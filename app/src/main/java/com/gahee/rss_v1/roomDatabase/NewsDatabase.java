package com.gahee.rss_v1.roomDatabase;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavEntities.class, NewsEntities.class, TopicStrings.class}, version = 4, exportSchema =  false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract Daos daos();
    private static final String TAG = NewsDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "myNewsDatabase";
    private static volatile NewsDatabase INSTANCE;

    public static NewsDatabase getDatabase(Context context){
        Log.d(TAG, "get database running ");

        if(INSTANCE == null){
            synchronized (NewsDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }

        }
        return INSTANCE;
    }

}
