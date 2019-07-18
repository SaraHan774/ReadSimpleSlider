package com.gahee.rss_v1.remoteDataSource.requests;

import android.util.Log;

import com.gahee.rss_v1.news.CnnAPI;
import com.gahee.rss_v1.news.tags.Rss;
import com.gahee.rss_v1.helpers.LinearSearch;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gahee.rss_v1.helpers.Constants.*;


public class FetchArticles {

    private static final String TAG = FetchArticles.class.getSimpleName();
    private static final StoreData storeData = StoreData.getInstance();

    private static final String []  Topics
            = {
            "Top Stories", "World", "Africa", "Americas", "Asia",
            "Europe", "Middle East", "U.S.", "Money", "Technology",
            "Science & Space", "Entertainment", "World Sport", "Football",
            "Golf", "Motorsport", "Tennis", "Travel", "Most Recent"
    };



    public static Call<Rss> decideWhatToCall(String Topic){
        Call<Rss> call;
        int topicIndex = LinearSearch.findIndex(Topics, Topic);
        CnnAPI cnnAPI = ServiceGenerator.getCnnAPI();
        Log.d(TAG, "deciding what to call .../ topic index : " + topicIndex);
        switch(topicIndex){
            //make a constant util - helper > Constants.java
            case TOP_STORIES:
                call = cnnAPI.getTopStories();
                break;
            case WORLD:
                call = cnnAPI.getEditionWorldFeed();
                break;
            case AFRICA:
                call = cnnAPI.getEditionAfricaFeed();
                break;
            case AMERICAS:
                call = cnnAPI.getEditionAmericasFeed();
                break;
            case ASIA:
                call = cnnAPI.getEditionAsiaFeed();
                break;
            case EUROPE:
                call = cnnAPI.getEditionEuropeFeed();
                break;
            case MIDDLE_EAST:
                call = cnnAPI.getEditionMiddleEastFeed();
                break;
            case US:
                call = cnnAPI.getEditionUSFeed();
                break;
            case MONEY:
                call = cnnAPI.getMoneyNewsInternationalFeed();
                break;
            case TECH:
                call = cnnAPI.getEditionTechnologyFeed();
                break;
            case SCIENCE:
                call = cnnAPI.getEditionSpaceFeed();
                break;
            case ENTERTAINMENT:
                call = cnnAPI.getEditionEntertainmentFeed();
                break;
            case WORLD_SPORT:
                call = cnnAPI.getEditionSportFeed();
                break;
            case FOOTBALL:
                call = cnnAPI.getEditionFootballFeed();
                break;
            case GOLF:
                call = cnnAPI.getEditionGolfFeed();
                break;
            case MOTOR_SPORT:
                call = cnnAPI.getEditionMotorSportFeed();
                break;
            case TENNIS:
                call = cnnAPI.getEditionTennisFeed();
                break;
            case TRAVEL:
                call = cnnAPI.getEditionTravelFeed();
                break;
            case MOST_RECENT:
                call = cnnAPI.getCnnLatestFeed();
                break;
            default:
                call = null;
        }

        return call;
    }

    public static void fetchArticleBasedOnUserSelection(Call<Rss> call){
        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(@NotNull Call<Rss> call, @NotNull Response<Rss> response) {
                if(response.body() != null){
                    Log.d(TAG, "fetching article from the web ... onResponse : " + response.body());
                    storeData.storeArticlesIntoArrayList(call, response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Rss> call, @NotNull Throwable t) {
                    Log.d(TAG, "onFailure : failed to fetch data from the web ! " + t.getMessage());
            }
        });
    }


}
