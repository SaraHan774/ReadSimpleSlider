package com.gahee.rss_v1.requests;

import android.text.Html;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.gahee.rss_v1.CNN.tags.Item;
import com.gahee.rss_v1.CNN.tags.Rss;
import com.gahee.rss_v1.CNN.model.Article;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class StoreData {

    private static final String TAG = "StoreData";

    private MutableLiveData<List<ArrayList<Article>>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Article> articleArrayList = new ArrayList<>();
    private List<ArrayList<Article>> list = new ArrayList<>();

    private static StoreData instance;

    public static StoreData getInstance() {
        if(instance == null){
            instance = new StoreData();
        }
        return instance;
    }

    public void storeArticlesIntoArrayList(Call<Rss> call, Response<Rss> response){
        String topicTitle;
        String articleTitle = null;
        String articleLink = null;
        String pubDate = null;
        String media = null;
        String articleDescription;
        String cleanArticleDescription = null;

        for(int i = 0; i< response.body().getChannel().getItem().size(); i++){
            Item item = response.body().getChannel().getItem().get(i);
            topicTitle = response.body().getChannel().getTitle();

            if(item != null) {
                articleTitle = item.getTitle();
                articleLink = item.getLink();
                pubDate = item.getPubdate();

                if (item.getThumbnail() != null) {
                    media = response.body().getChannel().getItem().get(i).getThumbnail().getUrl();
                } else {
                    media = "";
                }
                //remove html tag from article description content
                if (item.getDescription() != null) {
                    articleDescription = response.body().getChannel().getItem().get(i).getDescription();
                    cleanArticleDescription = Html.fromHtml(articleDescription).toString();
                } else {
                    cleanArticleDescription = "";
                }

                Log.d(TAG, "Topic title : " + topicTitle + "\n"
                        + "article link : " + articleLink + "\n" +
                        "article title : " + articleTitle + "\n"
                        + "media: " + media + "\n"
                        + "article description  : " + cleanArticleDescription + "\n\n");
            }
            articleArrayList.add(new Article(topicTitle, articleTitle, articleLink, pubDate, media, cleanArticleDescription));
        }
        list.add(articleArrayList);
        mutableLiveData.setValue(list);
    }

    public MutableLiveData<List<ArrayList<Article>>> getMutableLiveData() {
        return mutableLiveData;
}

    public ArrayList<Article> getArticleArrayList() {
        return articleArrayList;
    }

    public List<ArrayList<Article>> getList() {
        return list;
    }
}
