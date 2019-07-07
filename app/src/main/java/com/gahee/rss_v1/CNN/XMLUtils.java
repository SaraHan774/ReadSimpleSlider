package com.gahee.rss_v1.CNN;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.roomDatabase.NewsEntities;
import com.gahee.rss_v1.roomDatabase.NewsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class XMLUtils {

    private static final String TAG = XMLUtils.class.getSimpleName();
    public static final  String ARTICLE_DATA = "ARTICLE_DATA";
    public static final String GET_ARTICLE_DATA = "GET_ARTICLE_DATA";
    private Retrofit retrofit;

    private String userTopic;

    //database
    private NewsRepository newsRepository;

    public XMLUtils(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public void getTopStories(final Context context){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getTopStories(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                storeDataIntoArticle(call, response, context);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    public synchronized void getWorldEdition(final Context context){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getEditionWorldFeed(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                //put data into Article object
                storeDataIntoArticle(call, response, context);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    public synchronized void getAfricaEdition(final Context context){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getEditionAfricaFeed(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                //put data into Article object
                storeDataIntoArticle(call, response, context);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    public synchronized void getAmericasEdition(final Context context){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getEditionAmericasFeed(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                //put data into Article object
                storeDataIntoArticle(call, response, context);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    public synchronized void getAsiaEdition(final Context context){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getEditionAsiaFeed(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                //put data into Article object
                storeDataIntoArticle(call, response, context);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }


    private ArrayList<String> newsTopics = new ArrayList<>();

    private synchronized void storeDataIntoArticle(Call<TagRss> call, Response<TagRss> response, Context context){
        String topicTitle = null;
        String articleTitle;
        String articleLink;
        String pubDate;
        String media;
        String articleDescription;
        String cleanArticleDescription;

        for(int i = 0; i< response.body().getChannel().getItem().size(); i++){
            TagItem item = response.body().getChannel().getItem().get(i);
            topicTitle = response.body().getChannel().getTitle();

            if(item != null){
                articleTitle = item.getTitle();
                articleLink = item.getLink();
                pubDate = item.getPubdate();

                if(item.getThumbnail() != null){
                    media = response.body().getChannel().getItem().get(i).getThumbnail().getUrl();
                }else {
                    media = "";
                }
                //remove html tag from article description content
                if(item.getDescription() != null){
                    articleDescription = response.body().getChannel().getItem().get(i).getDescription();
                    cleanArticleDescription = Html.fromHtml(articleDescription).toString();
                }else{
                    cleanArticleDescription = "";
                }


                newsRepository = new NewsRepository(context);
                newsRepository.insertNews(new NewsEntities(topicTitle, articleTitle, articleLink, pubDate, media, cleanArticleDescription));

                Log.d(TAG, "Topic title : " + topicTitle  + "\n"
                        +"article link : " +  articleLink + "\n" +
                        "article title : " + articleTitle + "\n"
                        + "media: " + media + "\n"
                        + "article description  : " + cleanArticleDescription + "\n\n");
            }

            //insert this data into database;

       }
        if(topicTitle != null){newsTopics.add(topicTitle);}
        //instead of storing in a map, store it directly in the database
        //in the onCreate method of main tab activity, refresh the rss data to fetch the latest articles
    }

 //this method sets the topic that the user selects

    public ArrayList<String> getNewsTopics() {
        return newsTopics;
    }
}
