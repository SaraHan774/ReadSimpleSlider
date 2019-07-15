package com.gahee.rss_v1.mainTab;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {

    private MutableLiveData<int[]> mutableLiveDataCount = new MutableLiveData<>();

    public void setCount(int[] count) {
        mutableLiveDataCount.setValue(count);
    }

    public MutableLiveData<int[]> getMutableLiveDataCount() {
        return mutableLiveDataCount;
    }
}