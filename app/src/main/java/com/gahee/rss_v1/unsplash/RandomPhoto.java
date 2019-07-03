package com.gahee.rss_v1.unsplash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RandomPhoto {

    @SerializedName("urls")
    @Expose
    private URLs urLs;

    public URLs getUrLs() {
        return urLs;
    }


}
