package com.gahee.rss_v1.startApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.CNN.TopicSelection;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.roomDatabase.NewsEntities;

import java.util.HashMap;

public class TopicsSliderAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private String [] topics;
    private int [] photos;
    private TopicSelection topicSelection;

    public TopicsSliderAdapter(String [] topics, int [] photos, Context context){
        this.topics = topics;
        this.photos = photos;
        this.context = context;
        topicSelection = new TopicSelection(this.context);
    }


    @Override
    public int getCount() {
        return topics.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.topics_slider, container, false);

        TextView textView = view.findViewById(R.id.tv_topics_name);
        textView.setText(topics[position]);

        ImageView imageView = view.findViewById(R.id.image_view_topics);
        Glide.with(view).load(photos[position]).placeholder(R.drawable.android).into(imageView);

        ImageButton imageButton = view.findViewById(R.id.img_btn_topic_confirm);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save topic information to the database
                topicSelection.fetchDataBasedOnUserSelection(topics[position]);//isSelecting true;
                //make the ArrayList<Article> to be a live data - so that the database will be updated
                Toast.makeText(context, "Added to user selections", Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }




}
