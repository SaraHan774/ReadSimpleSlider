package com.gahee.rss_v1.startApp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class StartSliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int [] startSliderLayouts;


    public StartSliderAdapter(Context context, int[] layouts) {
        this.context = context;
        this.startSliderLayouts = layouts;
    }

    @Override
    public int getCount() {
        return startSliderLayouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(startSliderLayouts[position], container, false);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         View view = (View) object;
        container.removeView(view);
    }
}
