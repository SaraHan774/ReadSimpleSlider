package com.gahee.rss_v1.CNN;

import android.text.Html;
import android.util.Log;

import com.gahee.rss_v1.CNN.model.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class XMLUtils {

    private static final String TAG = XMLUtils.class.getSimpleName();
    public static final  String ARTICLE_DATA = "ARTICLE_DATA";
    public static final String GET_ARTICLE_DATA = "GET_ARTICLE_DATA";
    private Retrofit retrofit;
    private ArrayList<Article> articles= new ArrayList<>();


    public XMLUtils(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public void getScienceEdition(){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getEditionSpaceFeed(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                storeDataIntoArticle(call, response);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    public synchronized void getMoneyEdition(){
        CnnAPI cnnAPI = retrofit.create(CnnAPI.class);
        Call<TagRss> call = cnnAPI.getMoneyNewsInternationalFeed(); //make this reusable
        call.enqueue(new Callback<TagRss>() {

            @Override
            public void onResponse(Call<TagRss> call, Response<TagRss> response) {
                Log.d(TAG, "channel - title : " + response.body().getChannel().getTitle());
                //put data into Article object
                storeDataIntoArticle(call, response);
            }

            @Override
            public void onFailure(Call<TagRss> call, Throwable t) {
                Log.e(TAG, "onFailure : Unable to retrieve RSS : " + t.getMessage());
            }
        });
    }

    private synchronized void storeDataIntoArticle(Call<TagRss> call, Response<TagRss> response){

        for(int i = 0; i< response.body().getChannel().getItem().size(); i++){
            String topicTitle = response.body().getChannel().getTitle();

            String articleTitle = response.body().getChannel().getItem().get(i).getTitle();
            String articleLink = response.body().getChannel().getItem().get(i).getLink();
            String pubDate = response.body().getChannel().getItem().get(i).getPubdate();
            String media = response.body().getChannel().getItem().get(i).getThumbnail().getUrl();
            //remove html tag from article description content
            String articleDescription = response.body().getChannel().getItem().get(i).getDescription();
            String cleanArticleDescription = Html.fromHtml(articleDescription).toString();

            articles.add(new Article(topicTitle,
                    articleTitle, articleLink,
                    pubDate, media, cleanArticleDescription));

            Log.d(TAG, "Topic title : " + topicTitle  + "\n"
                    +"article link : " +  articleLink + "\n" +
                    "article title : " + articleTitle + "\n"
                    + "media: " + media + "\n"
            + "article description  : " + cleanArticleDescription + "\n\n");
        }
    }

    public ArrayList<Article> getArticle(){
        return this.articles;
    }

}
