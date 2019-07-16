package com.gahee.rss_v1.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryRoom {

    private static final String TAG = RepositoryRoom.class.getSimpleName();
    private Daos daos;
    private LiveData<List<FavEntities>> favoriteNews;
    private LiveData<List<TopicStrings>> topicStrings;
    private static FavEntities favEntities;
    private static NewsEntities newsEntities;

    public RepositoryRoom(Context context){
        NewsDatabase newsDatabase = NewsDatabase.getDatabase(context);
        daos = newsDatabase.daos();
        favoriteNews = daos.getMyFavoriteArticles();
        topicStrings = daos.loadTopicStrings();
    }

    public LiveData<List<FavEntities>> getMyFavoriteNews(){
        return favoriteNews;
    }
    public LiveData<List<TopicStrings>> getTopicStrings() {
        return topicStrings;
    }

    public void insertNews(NewsEntities newsEntities){
        new InsertAsync(daos).execute(newsEntities);
    }

    public List<NewsEntities> loadNewsByTopic(String topic){
        return new LoadAsync(daos).doInBackground();
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

    //for topic strings
    public void insertTopicString(TopicStrings topicStrings){
        Log.d(TAG, "inserting topic strings : topic strings = " + topicStrings);
        new InsertTopicStringAsync(daos).execute(topicStrings);
    }

    public LiveData<List<TopicStrings>> loadTopicStrings(){
        return new LoadTopicStringsAsync(daos).doInBackground();
    }

    //update my favorites table by comparing the count number
    public void updateMyFavTable(int count, String articleTopic){
        new UpdateCountAsync(daos, articleTopic).execute(count);
    }

    public void deleteFromTopicStringsList(String topicString){
        new DeleteFromTopicStringListAsync(daos).execute(topicString);
    }



    // AsyncTasks for database query
    public static class InsertAsync extends AsyncTask<NewsEntities, Void, Void>{
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

    public static class DeleteByTopicAsync extends AsyncTask<String, Void, Void>{
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

    public static class InsertMyFavAsync extends AsyncTask<FavEntities, Void, Void>{
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

    public static class DeleteByArticleTitleAsync extends AsyncTask<String, Void, Void>{
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

    public static class LoadAsync extends AsyncTask<String, Void, List<NewsEntities>>{
        private Daos daos;

        LoadAsync(Daos daos){
            this.daos = daos;
        }

        @Override
        protected List<NewsEntities> doInBackground(String... strings) {
            return daos.loadNewsByTopic(strings[0]);
        }
    }

    public static class InsertTopicStringAsync extends AsyncTask<TopicStrings, Void, Void>{
        private Daos daos;

        public InsertTopicStringAsync(Daos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(TopicStrings... topicStrings) {
            daos.insertTopicString(topicStrings[0]);
            return null;
        }
    }

    public static class LoadTopicStringsAsync extends AsyncTask<Void, Void, LiveData<List<TopicStrings>>>{
        private Daos daos;

        public LoadTopicStringsAsync(Daos daos){
            this.daos = daos;
        }
        @Override
        protected LiveData<List<TopicStrings>> doInBackground(Void... voids) {
            return daos.loadTopicStrings();
        }
    }


    public static class UpdateCountAsync extends AsyncTask<Integer, Void, Void>{
        private Daos daos;
        private String articleTitle;

        public UpdateCountAsync(Daos daos, String articleTopic) {
            this.daos = daos;
            this.articleTitle = articleTopic;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            daos.updateMyFavTable(integers[0], articleTitle);
            return null;
        }
    }

    public static class DeleteFromTopicStringListAsync extends AsyncTask<String, Void, Void>{
        private Daos daos;

        public DeleteFromTopicStringListAsync(Daos daos){
            this.daos = daos;
        }

        @Override
        protected Void doInBackground(String... strings) {
            daos.deleteFromTopicStringList(strings[0]);
            return null;
        }
    }
}
