package com.gahee.rss_v1.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.R;


public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder> {
//this rv adapter will be set to fragment_main_news.xml

    Context context;
    //fetch necessary data from the news page

    public MyNewsAdapter(){
        //set the data to the adapter
    }

    @NonNull
    @Override
    public MyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_view_holder, parent, false);
        return new MyNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyNewsViewHolder extends RecyclerView.ViewHolder{
        TextView articleTitle;

        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.tv_main_news_topic);
        }
    }
}
