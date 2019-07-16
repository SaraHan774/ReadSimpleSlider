package com.gahee.rss_v1.news;

import com.gahee.rss_v1.news.tags.Rss;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CnnAPI {

    String BASE_URL = "http://rss.cnn.com/rss/";

    @GET("edition.rss")
    Call<Rss> getTopStories();

    @GET("edition_world.rss")
    Call<Rss> getEditionWorldFeed();

    @GET("edition_africa.rss")
    Call<Rss> getEditionAfricaFeed();

    @GET("edition_americas.rss")
    Call<Rss> getEditionAmericasFeed();

    @GET("edition_asia.rss")
    Call<Rss> getEditionAsiaFeed();

    @GET("edition_europe.rss")
    Call<Rss> getEditionEuropeFeed();

    @GET("edition_meast.rss")
    Call<Rss> getEditionMiddleEastFeed();

    @GET("edition_us.rss")
    Call<Rss> getEditionUSFeed();

    @GET("money_news_international.rss")
    Call<Rss> getMoneyNewsInternationalFeed();

    @GET("edition_technology.rss")
    Call<Rss> getEditionTechnologyFeed();

    @GET("edition_space.rss")
    Call<Rss> getEditionSpaceFeed();

    @GET("edition_entertainment.rss")
    Call<Rss> getEditionEntertainmentFeed();

    @GET("edition_sport.rss")
    Call<Rss> getEditionSportFeed();

    @GET("edition_football.rss")
    Call<Rss> getEditionFootballFeed();

    @GET("edition_golf.rss")
    Call<Rss> getEditionGolfFeed();

    @GET("edition_motorsport.rss")
    Call<Rss> getEditionMotorSportFeed();

    @GET("edition_tennis.rss")
    Call<Rss> getEditionTennisFeed();

    @GET("edition_travel.rss")
    Call<Rss> getEditionTravelFeed();

    @GET("cnn_freevideo.rss")
    Call<Rss> getFreeVideoFeed();

    @GET("cnn_latest.rss")
    Call<Rss> getCnnLatestFeed();


}
