package com.gahee.rss_v1.mainTab.recyclerViewAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.pagerAdapters.SliderPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.MyNewsViewHolder> {
//this rv adapter will be set to fragment_main_news.xml
    private static final String TAG = "MyNewsAdapter";

    private Context context;
    private List<ArrayList<Article>> arrayLists;
    //fetch necessary data from the news page

    public MyNewsAdapter(Context context, List<ArrayList<Article>> arrayLists){
        //set the data to the adapter
        this.context = context;
        this.arrayLists = arrayLists;
    }

    private View newsView;

    @NonNull
    @Override
    public MyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        newsView = LayoutInflater.from(context).inflate(R.layout.main_rv_view_holder, parent, false);
        Log.d(TAG, "view group : " + parent);
        return new MyNewsViewHolder(newsView);
    }

    private String stringCleaner(String s){
        Pattern pattern = Pattern.compile("CNN.com - RSS Channel - ");
        Matcher matcher = pattern.matcher(s);
        String sub = "";
        while(matcher.find()) {
//            int start = matcher.start();
            int end = matcher.end();
            sub = s.substring(end);
        }
        return sub;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyNewsViewHolder holder, final int position) {
        String topicTitle = arrayLists.get(position).get(0).getTopicTitle();
        String cleanTitle = stringCleaner(topicTitle);
        if(cleanTitle.equals("")){
            cleanTitle = topicTitle;
        }
        holder.topicTitle.setText(cleanTitle);
        //initialize view pager -> set contents into the view pager
        //해당 position 의 기사가 들어있는 array list 하나를 넘겨준다.
        PagerAdapter pagerAdapter = new SliderPagerAdapter(context, arrayLists.get(position));
        holder.viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "get item count : arraylist.size()" + arrayLists.size());
        return arrayLists.size();
    }

    public class MyNewsViewHolder extends RecyclerView.ViewHolder{
        private TextView topicTitle;
        private ViewPager viewPager;

        public MyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            topicTitle = itemView.findViewById(R.id.tv_main_news_topic);
            viewPager = itemView.findViewById(R.id.main_news_inner_view_pager);
        }
    }

}
