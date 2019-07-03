package com.gahee.rss_v1.CNN;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedAPIcnn {

    String BASE_URL = "http://rss.cnn.com/rss/";

    @GET("edition_technology.rss")
    Call<FeedCnn> getEditionTechnologyFeed();

    @GET("edition_space.rss")
    Call<FeedCnn> getEditionSpaceFeed();

    @GET("edition_world.rss")
    Call<FeedCnn> getEditionWorldFeed();

    @GET("edition_africa.rss")
    Call<FeedCnn> getEditionAfricaFeed();

    @GET("edition_americas.rss")
    Call<FeedCnn> getEditionAmericasFeed();

    @GET("edition_asia.rss")
    Call<FeedCnn> getEditionAsiaFeed();

    @GET("edition_europe.rss")
    Call<FeedCnn> getEditionEuropeFeed();

    @GET("edition_meast.rss")
    Call<FeedCnn> getEditionMiddleEastFeed();

    @GET("edition_us.rss")
    Call<FeedCnn> getEditionUSFeed();

    @GET("money_news_international.rss")
    Call<FeedCnn> getMoneyNewsInternationalFeed();

    @GET("edition_entertainment.rss")
    Call<FeedCnn> getEditionEntertainmentFeed();

    @GET("edition_sport.rss")
    Call<FeedCnn> getEditionSportFeed();

    @GET("edition_football.rss")
    Call<FeedCnn> getEditionFootballFeed();

    @GET("edition_golf.rss")
    Call<FeedCnn> getEditionGolfFeed();

    @GET("edition_motorsport.rss")
    Call<FeedCnn> getEditionMotorSportFeed();

    @GET("edition_tennis.rss")
    Call<FeedCnn> getEditionTennisFeed();

    @GET("edition_travel.rss")
    Call<FeedCnn> getEditionTravelFeed();

    @GET("cnn_freevideo.rss")
    Call<FeedCnn> getFreeVideoFeed();

    @GET("cnn_latest.rss")
    Call<FeedCnn> getCnnLatestFeed();


}
