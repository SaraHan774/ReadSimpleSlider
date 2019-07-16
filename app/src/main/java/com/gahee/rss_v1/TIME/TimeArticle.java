package com.gahee.rss_v1.TIME;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeArticle implements Parcelable {

    private String timeTopicTitle;
    private String timeArticleTitle;
    private String timeArticleLink;
    private String timeArticlePubDate;
    private String timeMediaThumbnail;
    private String timeArticleDesc;

    public TimeArticle(String timeTopicTitle, String timeArticleTitle, String timeArticleLink, String timeArticlePubDate, String timeMediaThumbnail, String timeArticleDesc) {
        this.timeTopicTitle = timeTopicTitle;
        this.timeArticleTitle = timeArticleTitle;
        this.timeArticleLink = timeArticleLink;
        this.timeArticlePubDate = timeArticlePubDate;
        this.timeMediaThumbnail = timeMediaThumbnail;
        this.timeArticleDesc = timeArticleDesc;
    }

    public String getTimeTopicTitle() {
        return timeTopicTitle;
    }

    public String getTimeArticleTitle() {
        return timeArticleTitle;
    }

    public String getTimeArticleLink() {
        return timeArticleLink;
    }

    public String getTimeArticlePubDate() {
        return timeArticlePubDate;
    }

    public String getTimeMediaThumbnail() {
        return timeMediaThumbnail;
    }

    public String getTimeArticleDesc() {
        return timeArticleDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(timeTopicTitle);
        parcel.writeString(timeArticleTitle);
        parcel.writeString(timeArticleLink);
        parcel.writeString(timeArticlePubDate);
        parcel.writeString(timeMediaThumbnail);
        parcel.writeString(timeArticleDesc);

    }

    protected TimeArticle(Parcel in) {

        timeTopicTitle = in.readString();
        timeArticleTitle = in.readString();
        timeArticleLink = in.readString();
        timeArticlePubDate= in.readString();
        timeMediaThumbnail = in.readString();
        timeArticleDesc = in.readString();

    }

    public static final Creator<TimeArticle> CREATOR = new Creator<TimeArticle>() {
        @Override
        public TimeArticle createFromParcel(Parcel in) {
            return new TimeArticle(in);
        }



        @Override
        public TimeArticle[] newArray(int size) {
            return new TimeArticle[size];
        }
    };
}
