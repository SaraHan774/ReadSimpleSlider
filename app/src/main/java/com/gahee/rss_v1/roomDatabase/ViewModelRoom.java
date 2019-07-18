package com.gahee.rss_v1.roomDatabase;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelRoom extends AndroidViewModel {

    private static final String TAG = ViewModelRoom.class.getSimpleName();
    private final RepositoryRoom repositoryRoom;

    public ViewModelRoom(@NonNull Application application) {
        super(application);
        Log.d(TAG, "view model constructor running");
        repositoryRoom = new RepositoryRoom(application);
        LiveData<List<FavEntities>> favoriteNews = repositoryRoom.getMyFavoriteNews();
    }

    public LiveData<List<FavEntities>> getFavoriteNews(){
        Log.d(TAG, "getting my favorites ");
        return repositoryRoom.getMyFavoriteNews();
    }

    public void insertMyFavorite(FavEntities favEntities, Context context){
        Log.d(TAG, "inserting my favorite article ");
        repositoryRoom.insertMyFav(favEntities, context);
    }

    public void deleteMyFavorite(String articleTitle){
        Log.d(TAG, "deleting my favorite article ");
        repositoryRoom.deleteMyFavByTitle(articleTitle);
    }

    public LiveData<List<TopicStrings>> getTopicStrings(){
        Log.d(TAG, "loading topic strings from the database");
        return repositoryRoom.getTopicStrings();
    }


    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }
}
