package com.gahee.rss_v1.repository;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.gahee.rss_v1.CNN.tags.Rss;
import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.requests.FetchArticles;
import com.gahee.rss_v1.requests.StoreData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Repository {

    private MutableLiveData<List<ArrayList<Article>>> listMutableLiveData = new MutableLiveData<>();
    private List<ArrayList<Article>> list = new ArrayList<>();
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    private FetchArticles fetchArticles = new FetchArticles();

    private static Repository instance;

    public static Repository getInstance() {
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public void requestDataAsync(String topic){
        new FetchArticleAsync().execute(topic);
    }


    public MutableLiveData<List<ArrayList<Article>>> getListMutableLiveData() {
        return StoreData.getInstance().getMutableLiveData();
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
