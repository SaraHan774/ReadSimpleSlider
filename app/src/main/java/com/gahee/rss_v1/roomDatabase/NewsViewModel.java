package com.gahee.rss_v1.roomDatabase;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private static final String TAG = NewsViewModel.class.getSimpleName();
    private NewsRepository newsRepository;
    private LiveData<List<FavEntities>> favoriteNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "view model constructor running");
        newsRepository = new NewsRepository(application);
        favoriteNews = newsRepository.getMyFavoriteNews();
    }

    public LiveData<List<FavEntities>> getFavoriteNews(){
        Log.d(TAG, "getting my favorites ");
        return newsRepository.getMyFavoriteNews();
    }

    public void insertMyFavorite(FavEntities favEntities){
        Log.d(TAG, "inserting my favorite article ");
        newsRepository.insertMyFav(favEntities);
    }

    public void deleteMyFavorite(String articleTitle){
        Log.d(TAG, "deleting my favorite article ");
        newsRepository.deleteMyFavByTitle(articleTitle);
    }


    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }
}
