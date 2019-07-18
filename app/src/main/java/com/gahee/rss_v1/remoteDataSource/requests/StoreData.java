package com.gahee.rss_v1.remoteDataSource.requests;

import android.text.Html;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gahee.rss_v1.news.tags.Item;
import com.gahee.rss_v1.news.tags.Rss;
import com.gahee.rss_v1.news.model.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class StoreData {

    private static final String TAG = "StoreData";

    private final MutableLiveData<ArrayList<ArrayList<Article>>> listMutableLiveData = new MutableLiveData<>();
    private final ArrayList<ArrayList<Article>> listOfArticlesSortedByTopics = new ArrayList<>();

    private static StoreData instance;

    public static StoreData getInstance() {
        if(instance == null){
            instance = new StoreData();
        }
        return instance;
    }

    public synchronized void storeArticlesIntoArrayList(Call<Rss> call, Response<Rss> response){
        ArrayList<Article> articleArrayList = new ArrayList<>();

        String topicTitle;
        String articleTitle = null;
        String articleLink = null;
        String pubDate = null;
        String media = null;
        String articleDescription;
        String cleanArticleDescription = null;

        if(articleArrayList != null){
            articleArrayList.clear();
        }

        assert response.body() != null;
        for(int i = 0; i< response.body().getChannel().getItem().size(); i++){
            Item item = response.body().getChannel().getItem().get(i);
            topicTitle = response.body().getChannel().getTitle();

            if(item != null) {
                articleTitle = item.getTitle();
                articleLink = item.getLink();
                pubDate = item.getPubDate();

                if (item.getThumbnail() != null) {
                    media = response.body().getChannel().getItem().get(i).getThumbnail().getUrl();
                } else if(item.getGroup() != null){
                    media = response.body().getChannel().getItem().get(i).getGroup().getContent().get(0).getUrl();
                }else{
                    media = "";
                }
                //remove html tag from articles description content
                if (item.getDescription() != null) {
                    articleDescription = response.body().getChannel().getItem().get(i).getDescription();
                    cleanArticleDescription = Html.fromHtml(articleDescription).toString().replace((char) 65532, (char) 32).trim();
                } else {
                    cleanArticleDescription = " ";
                }

//                Log.d(TAG, "Topic title : " + topicTitle + "\n"
//                        + "articles link : " + articleLink + "\n" +
//                        "articles title : " + articleTitle + "\n"
//                        + "media: " + media + "\n"
//                        + "articles description  : " + cleanArticleDescription + "\n\n");
            }
            Article articles = new Article(topicTitle, articleTitle, articleLink, pubDate, media, cleanArticleDescription);
            articleArrayList.add(articles);
            auxiliaryListForSearch.add(articles);
        }
        Log.d(TAG, "articles : " + articleArrayList.size());

        if(!listOfArticlesSortedByTopics.contains(articleArrayList)) {
            listOfArticlesSortedByTopics.add(articleArrayList);
            //Log.d(TAG, "listOfArticlesSortedByTopics size : " + listOfArticlesSortedByTopics);
            listMutableLiveData.setValue(listOfArticlesSortedByTopics);
            //Log.d(TAG, "listOfArticlesSortedByTopics mutable live data " + listMutableLiveData.getValue().size());
        }
    }

    public MutableLiveData<ArrayList<ArrayList<Article>>> getListMutableLiveData() {
        return listMutableLiveData;
    }


    public ArrayList<ArrayList<Article>> getListOfArticlesSortedByTopics() {
        return listOfArticlesSortedByTopics;
    }


    //these are made for search.
    private final MutableLiveData<ArrayList<Article>> listForSearch = new MutableLiveData<>();
    private final ArrayList<Article> auxiliaryListForSearch = new ArrayList<>();

    public MutableLiveData<ArrayList<Article>> getListForSearch(){
        listForSearch.setValue(auxiliaryListForSearch);
        return listForSearch;
    }
}
