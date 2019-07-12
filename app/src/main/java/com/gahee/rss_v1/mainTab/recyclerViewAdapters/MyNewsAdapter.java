package com.gahee.rss_v1.mainTab.recyclerViewAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.R;

import java.util.ArrayList;


public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder> {
//this rv adapter will be set to fragment_main_news.xml
    private static final String TAG = "MyNewsAdapter";

    private Context context;
    private ArrayList<String> topics;
    //fetch necessary data from the news page

    public MyNewsAdapter(Context context, ArrayList<String> topics){
        //set the data to the adapter
        this.context = context;
        this.topics = topics;
    }

    @NonNull
    @Override
    public MyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_view_holder, parent, false);
        return new MyNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsViewHolder holder, int position) {
            holder.topicTitle.setText(topics.get(position));
        Log.d(TAG, "on bind view holder ... topics : " + topics.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "topics size : " + topics.size());
        return topics.size();
    }

    public class MyNewsViewHolder extends RecyclerView.ViewHolder{
        TextView topicTitle;
//        CardView cardView;

        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            topicTitle = itemView.findViewById(R.id.tv_main_news_topic);
//            cardView = itemView.findViewById(R.id.card_view_news_tab);
        }
    }
}
