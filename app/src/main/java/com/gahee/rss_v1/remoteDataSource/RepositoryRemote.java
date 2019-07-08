package com.gahee.rss_v1.remoteDataSource;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.gahee.rss_v1.CNN.tags.Rss;
import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.remoteDataSource.requests.FetchArticles;
import com.gahee.rss_v1.remoteDataSource.requests.StoreData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class RepositoryRemote {

    private MutableLiveData<List<ArrayList<Article>>> listMutableLiveData = new MutableLiveData<>();
    private List<ArrayList<Article>> list = new ArrayList<>();
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    private FetchArticles fetchArticles = new FetchArticles();

    private static RepositoryRemote instance;

    public static RepositoryRemote getInstance() {
        if(instance == null){
            instance = new RepositoryRemote();
        }
        return instance;
    }

    public void requestDataAsync(String topic){
        new FetchArticleAsync().execute(topic);
    }


    public MutableLiveData<List<ArrayList<Article>>> getListMutableLiveData() {
        return StoreData.getInstance().getListMutableLiveData();
    }

    public ArrayList<Article> getArticleArrayList() {
        return StoreData.getInstance().getArticleArrayList();
    }

    public List<ArrayList<Article>> getList() {
        return StoreData.getInstance().getList();
    }

    class FetchArticleAsync extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            Call<Rss> call = FetchArticles.decideWhatToCall(strings[0]);
            fetchArticles.fetchArticleBasedOnUserSelection(call);
            return null;
        }
    }

}
