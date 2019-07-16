package com.gahee.rss_v1.topicSelection;

import android.content.Context;
import android.opengl.GLDebugHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.helpers.PhotoUtils;
import com.gahee.rss_v1.roomDatabase.RepositoryRoom;
import com.gahee.rss_v1.roomDatabase.TopicStrings;

import java.util.ArrayList;
import java.util.List;

public class TopicSelectionAdapter extends RecyclerView.Adapter<TopicSelectionAdapter.TopicSelectionViewHolder>{

    private static final String TAG = "TopicSelectionAdapter";
    private Context context;
    private List<TopicStrings> topicStringsList;
    private PhotoUtils photoUtils;
    private String [] topics;
    private int [] photos;
    private RepositoryRoom repositoryRoom;

    public TopicSelectionAdapter(Context context, List<TopicStrings> topicStringsList) {
        this.context = context;
        this.topicStringsList = topicStringsList;
        photoUtils = new PhotoUtils();
        topics = photoUtils.getTopicsOfPhotos();
        photos = photoUtils.getPhotos();
        repositoryRoom = new RepositoryRoom(this.context);
    }

    @NonNull
    @Override
    public TopicSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topic_selection_view_holder, parent, false);
        return new TopicSelectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicSelectionViewHolder holder, final int position) {

        holder.textView.setText(topics[position]);
        Glide.with(context).load(photos[position])
                .centerCrop()
                .placeholder(R.drawable.android)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i =0; i < topicStringsList.size(); i++){
                    String alreadyInserted = topicStringsList.get(i).getTopicString();
                    Log.d(TAG, "already in ? :  " + alreadyInserted + " | " + topics[position]);
                    if(topics[position].equals(alreadyInserted)){
                        repositoryRoom.deleteFromTopicStringsList(topics[position]);
                        Toast.makeText(context, R.string.topic_deleted, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else{
                        repositoryRoom.insertTopicString(new TopicStrings(topics[position]));
                        Toast.makeText(context, R.string.topic_inserted, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return topics.length;
    }

    public static class TopicSelectionViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView; //implement touch selector - 이미 선택된 토픽들은 약간 투명한 검정색으로 뜨도록
        TextView textView;

        public TopicSelectionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_topic_selection);
            textView = itemView.findViewById(R.id.tv_topic_selection);
        }
    }
}
