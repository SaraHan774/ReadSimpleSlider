package com.gahee.rss_v1.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NewsRepository {

    private static final String TAG = NewsRepository.class.getSimpleName();
    private Daos daos;
    private LiveData<List<FavEntities>> favoriteNews;
    private static FavEntities favEntities;
    private static NewsEntities newsEntities;

    public NewsRepository(Context context){
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(context);
        daos = newsDatabase.daos();
        favoriteNews = daos.getMyFavoriteArticles();

    }

    public LiveData<List<FavEntities>> getMyFavoriteNews(){
        return favoriteNews;
    }

    public void insertNews(NewsEntities newsEntities){
        new InsertAsync(daos).execute(newsEntities);
    }

    public void insertMyFav(FavEntities favEntities){
        new InsertMyFavAsync(daos).execute(favEntities);
    }

    public void deleteByTopic(String topic){
        new DeleteByTopicAsync(daos).execute(topic);
    }

    public void deleteMyFavByTitle(String articleTitle){
        new DeleteByArticleTitleAsync(daos).execute(articleTitle);
    }

    public class InsertAsync extends AsyncTask<NewsEntities, Void, Void>{
        private Daos daos;

        InsertAsync(Daos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(NewsEntities... newsEntities) {
            daos.insert(newsEntities[0]);
            return null;
        }
    }

    public class DeleteByTopicAsync extends AsyncTask<String, Void, Void>{
        private Daos daos;

        DeleteByTopicAsync(Daos daos){
            this.daos =daos;
        }

        @Override
        protected Void doInBackground(String... strings) {
            daos.deleteByArticleTopic(strings[0]);
            return null;
        }
    }

    public class InsertMyFavAsync extends AsyncTask<FavEntities, Void, Void>{
        private Daos daos;

        InsertMyFavAsync(Daos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(FavEntities... favEntities) {
            daos.insertMyFavorite(favEntities[0]);
            return null;
        }
    }

    public class DeleteByArticleTitleAsync extends AsyncTask<String, Void, Void>{
        private Daos daos;

        DeleteByArticleTitleAsync(Daos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(String... strings) {
            daos.deleteMyFavoriteByTitle(strings[0]);
            return null;
        }
    }


}
