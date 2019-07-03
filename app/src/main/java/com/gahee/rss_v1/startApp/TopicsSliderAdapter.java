package com.gahee.rss_v1.startApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.gahee.rss_v1.R;

import java.util.ArrayList;

public class TopicsSliderAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<TopicsItem> topicsItemArrayList;

    public TopicsSliderAdapter(ArrayList<TopicsItem> topicsItemArrayList, Context context){
        this.topicsItemArrayList = topicsItemArrayList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.topics_slider, container, false);
        TextView textView = view.findViewById(R.id.tv_topics_name);
        textView.setText(topicsItemArrayList.get(position).getmTopicName());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
