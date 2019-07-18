package com.gahee.rss_v1.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class CheckIfNew {

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    private static final String PREF_NAME = "welcome";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    public CheckIfNew(Context context){
        Context context1 = context;
        int PRIVATE_MODE = 0;
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
