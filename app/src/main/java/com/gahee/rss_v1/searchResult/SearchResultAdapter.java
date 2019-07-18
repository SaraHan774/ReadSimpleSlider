package com.gahee.rss_v1.searchResult;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.news.model.Article;
import com.gahee.rss_v1.R;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {


    private final Context context;
    private final ArrayList<Article> searchResultsList;

    public SearchResultAdapter(Context context, ArrayList<Article> searchResultsList){
        this.context = context;
        this.searchResultsList = searchResultsList;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_rv_view_holder, parent, false);
        return new SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder holder, final int position) {

        holder.articleTitle.setText(searchResultsList.get(position).getArticleTitle());
        holder.articleDescription.setText(searchResultsList.get(position).getArticleDescription());

        if(searchResultsList.get(position).getArticleDescription() != null){
            holder.articleDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openUrlInBrowser(position);
                }
            });
        }else if(searchResultsList.get(position).getArticleTitle() != null){
            holder.articleTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openUrlInBrowser(position);
                }
            });
        }

        Glide.with(context).load(searchResultsList.get(position)
                .getMedia())
                .centerCrop()
                .placeholder(R.drawable.android)
                .into(holder.articlePhoto);

    }

    private void openUrlInBrowser(int position){
        String url = searchResultsList.get(position).getArticleLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        if(intent.resolveActivity(context.getPackageManager()) != null){
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return searchResultsList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        final TextView articleTitle;
        final TextView articleDescription;
        final ImageView articlePhoto;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.search_result_article_title);
            articleDescription = itemView.findViewById(R.id.search_result_article_desc);
            articlePhoto = itemView.findViewById(R.id.search_result_image_view);
        }
    }
}
