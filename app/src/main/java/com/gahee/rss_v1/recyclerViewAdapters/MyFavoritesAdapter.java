package com.gahee.rss_v1.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gahee.rss_v1.R;

public class MyFavoritesAdapter extends RecyclerView.Adapter<MyFavoritesAdapter.MyFavoritesViewHolder> {
    //this rv adapter will be set to fragment_my_favorites.xml

    Context context;
    //get the data from the database

    public MyFavoritesAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyFavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_fav_view_holder, parent, false);
        return new MyFavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoritesViewHolder holder, int position) {

        //holder.articlePhoto - set the image from the database
        //holder.topicTitle  -  set the title from the database

    }

    @Override
    public int getItemCount() { //get the length of of the list from the database
        return 1;
    }

    public class MyFavoritesViewHolder extends RecyclerView.ViewHolder{
        TextView articleTitle;
        ImageView articlePhoto;


        public MyFavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.tv_main_fav);
            articlePhoto = itemView.findViewById(R.id.main_fav_vh_img);
        }
    }
}
