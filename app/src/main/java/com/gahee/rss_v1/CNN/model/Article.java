package com.gahee.rss_v1.CNN.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Article implements Parcelable {

    private String topicTitle;
    private String articleTitle;
    private String articleLink;
    private String pubDate;
    private String media;
    private String articleDescription;

    //plan how to use "media:group and media:content"


    public Article(String topicTitle, String articleTitle, String articleLink, String pubDate, String media, String articleDescription) {
        this.topicTitle = topicTitle;
        this.articleTitle = articleTitle;
        this.articleLink = articleLink;
        this.pubDate = pubDate;
        this.media = media;
        this.articleDescription = articleDescription;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(topicTitle);
        parcel.writeString(articleTitle);
        parcel.writeString(articleLink);
        parcel.writeString(pubDate);
        parcel.writeString(media);
        parcel.writeString(articleDescription);

    }

    protected Article(Parcel in) {

        topicTitle = in.readString();
        articleTitle = in.readString();
        articleLink = in.readString();
        pubDate = in.readString();
        media = in.readString();
        articleDescription = in.readString();

    }


    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }



        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
