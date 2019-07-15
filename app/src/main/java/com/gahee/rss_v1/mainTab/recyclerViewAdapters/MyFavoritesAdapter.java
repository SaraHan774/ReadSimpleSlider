package com.gahee.rss_v1.mainTab.recyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.roomDatabase.FavEntities;

import java.util.ArrayList;
import java.util.List;

public class MyFavoritesAdapter extends RecyclerView.Adapter<MyFavoritesAdapter.MyFavoritesViewHolder> {
    //this rv adapter will be set to fragment_my_favorites.xml

    private Context context;
    private List<FavEntities> favEntities;
    //get the data from the database

    public MyFavoritesAdapter(Context context, List<FavEntities> favEntities){
        this.context = context;
        this.favEntities = favEntities;
    }

    @NonNull
    @Override
    public MyFavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_fav_view_holder, parent, false);
        return new MyFavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavoritesViewHolder holder, int position) {

        holder.articleTitle.setText(favEntities.get(position).getArticleTitle());
        holder.articleDesc.setText(favEntities.get(position).getArticleDescription());
        holder.starCounts.append(String.valueOf(favEntities.get(position).getCount()));

        Glide.with(context)
                .load(favEntities.get(position).getThumbnail())
                .centerCrop()
                .placeholder(R.drawable.android)
                .into(holder.articlePhoto);
    }

    @Override
    public int getItemCount() { //get the length of of the list from the database
        return favEntities.size();
    }

    public class MyFavoritesViewHolder extends RecyclerView.ViewHolder{
        TextView articleTitle;
        TextView articleDesc;
        TextView starCounts;
        ImageView articlePhoto;


        public MyFavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.tv_fav_article_title);
            articleDesc = itemView.findViewById(R.id.tv_fav_article_desc);
            starCounts = itemView.findViewById(R.id.tv_fav_count);
            articlePhoto = itemView.findViewById(R.id.main_fav_vh_img);
        }
    }
}
