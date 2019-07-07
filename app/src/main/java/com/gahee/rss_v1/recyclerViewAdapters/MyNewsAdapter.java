package com.gahee.rss_v1.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.roomDatabase.NewsEntities;

import java.util.ArrayList;


public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder> {
//this rv adapter will be set to fragment_main_news.xml

    private Context context;
    private ArrayList<NewsEntities> newsEntities;
    //fetch necessary data from the news page

    public MyNewsAdapter(Context context,ArrayList<NewsEntities> newsEntities){
        //set the data to the adapter
        this.context = context;
        this.newsEntities =  newsEntities;
    }

    @NonNull
    @Override
    public MyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_view_holder, parent, false);
        return new MyNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsViewHolder holder, int position) {
        holder.topicTitle.setText(newsEntities.get(position).getTopic());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyNewsViewHolder extends RecyclerView.ViewHolder{
        TextView topicTitle;

        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            topicTitle = itemView.findViewById(R.id.tv_main_news_topic);
        }
    }
}
