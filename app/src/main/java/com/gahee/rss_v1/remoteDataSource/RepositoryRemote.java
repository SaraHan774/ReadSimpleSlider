package com.gahee.rss_v1.remoteDataSource;

import android.os.AsyncTask;
import android.util.Log;

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

    private static final String TAG = "RepositoryRemote";

    private static RepositoryRemote instance;

    public static RepositoryRemote getInstance() {
        if(instance == null){
            instance = new RepositoryRemote();
        }
        return instance;
    }

    public void requestDataAsync(String topic){
        Log.d(TAG, "request data async / topic : " + topic);
        new FetchArticleAsync().execute(topic);
    }


    public MutableLiveData<ArrayList<ArrayList<Article>>> getListMutableLiveData() {
        return StoreData.getInstance().getListMutableLiveData();
    }


    public ArrayList<ArrayList<Article>> getList() {
        return StoreData.getInstance().getListOfArticlesSortedByTopics();
    }

    public MutableLiveData<ArrayList<Article>> getListForSearch(){
        return StoreData.getInstance().getListForSearch() ;
    }

    class FetchArticleAsync extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... strings) {
            Log.d(TAG, "fetch data async running ... / strings[0] : " + strings[0]);
            Call<Rss> call = FetchArticles.decideWhatToCall(strings[0]);
            fetchArticles.fetchArticleBasedOnUserSelection(call);
            return null;
        }
    }

}
