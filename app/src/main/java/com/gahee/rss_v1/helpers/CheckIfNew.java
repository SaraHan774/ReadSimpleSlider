package com.gahee.rss_v1.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class CheckIfNew {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    public CheckIfNew(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setIsFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean getIsFirstTimeLaunch(){
        return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
