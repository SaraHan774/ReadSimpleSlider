package com.gahee.rss_v1.CNN;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CnnAPI {

    String BASE_URL = "http://rss.cnn.com/rss/";

    @GET("edition.rss")
    Call<TagRss> getTopStories();

    @GET("edition_world.rss")
    Call<TagRss> getEditionWorldFeed();

    @GET("edition_africa.rss")
    Call<TagRss> getEditionAfricaFeed();

    @GET("edition_americas.rss")
    Call<TagRss> getEditionAmericasFeed();

    @GET("edition_asia.rss")
    Call<TagRss> getEditionAsiaFeed();

    @GET("edition_europe.rss")
    Call<TagRss> getEditionEuropeFeed();

    @GET("edition_meast.rss")
    Call<TagRss> getEditionMiddleEastFeed();

    @GET("edition_us.rss")
    Call<TagRss> getEditionUSFeed();

    @GET("money_news_international.rss")
    Call<TagRss> getMoneyNewsInternationalFeed();

    @GET("edition_technology.rss")
    Call<TagRss> getEditionTechnologyFeed();

    @GET("edition_space.rss")
    Call<TagRss> getEditionSpaceFeed();

    @GET("edition_entertainment.rss")
    Call<TagRss> getEditionEntertainmentFeed();

    @GET("edition_sport.rss")
    Call<TagRss> getEditionSportFeed();

    @GET("edition_football.rss")
    Call<TagRss> getEditionFootballFeed();

    @GET("edition_golf.rss")
    Call<TagRss> getEditionGolfFeed();

    @GET("edition_motorsport.rss")
    Call<TagRss> getEditionMotorSportFeed();

    @GET("edition_tennis.rss")
    Call<TagRss> getEditionTennisFeed();

    @GET("edition_travel.rss")
    Call<TagRss> getEditionTravelFeed();

    @GET("cnn_freevideo.rss")
    Call<TagRss> getFreeVideoFeed();

    @GET("cnn_latest.rss")
    Call<TagRss> getCnnLatestFeed();


}
