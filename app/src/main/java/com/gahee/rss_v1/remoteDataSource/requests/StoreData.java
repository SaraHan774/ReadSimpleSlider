package com.gahee.rss_v1.remoteDataSource.requests;

import android.text.Html;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gahee.rss_v1.CNN.tags.Item;
import com.gahee.rss_v1.CNN.tags.Rss;
import com.gahee.rss_v1.CNN.model.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class StoreData {

    private static final String TAG = "StoreData";

    private MutableLiveData<ArrayList<ArrayList<Article>>> listMutableLiveData = new MutableLiveData<>();
    private ArrayList<ArrayList<Article>> listOfArticlesSortedByTopics = new ArrayList<>();
    private Article articles;

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
                    cleanArticleDescription = "check out the site for content";
                }

//                Log.d(TAG, "Topic title : " + topicTitle + "\n"
//                        + "articles link : " + articleLink + "\n" +
//                        "articles title : " + articleTitle + "\n"
//                        + "media: " + media + "\n"
//                        + "articles description  : " + cleanArticleDescription + "\n\n");
            }
            articles = new Article(topicTitle, articleTitle, articleLink, pubDate, media, cleanArticleDescription);
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
    private MutableLiveData<ArrayList<Article>> listForSearch = new MutableLiveData<>();
    private ArrayList<Article> auxiliaryListForSearch = new ArrayList<>();

    public MutableLiveData<ArrayList<Article>> getListForSearch(){
        listForSearch.setValue(auxiliaryListForSearch);
        return listForSearch;
    }
}
