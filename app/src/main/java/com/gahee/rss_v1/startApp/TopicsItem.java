package com.gahee.rss_v1.startApp;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class TopicsItem implements Parcelable {

    private String mTopicName;
    private ImageView mTopicImageView;

    public TopicsItem(String mTopicName) {
        this.mTopicName = mTopicName;
    }

    protected TopicsItem(Parcel in) {
        mTopicName = in.readString();
    }

    public static final Creator<TopicsItem> CREATOR = new Creator<TopicsItem>() {
        @Override
        public TopicsItem createFromParcel(Parcel in) {
            return new TopicsItem(in);
        }

        @Override
        public TopicsItem[] newArray(int size) {
            return new TopicsItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTopicName);
    }

    public String getmTopicName() {
        return mTopicName;
    }
}
