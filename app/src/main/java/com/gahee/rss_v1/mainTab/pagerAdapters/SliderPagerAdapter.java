package com.gahee.rss_v1.mainTab.pagerAdapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.TestActivity;
import com.gahee.rss_v1.mainTab.ArticleDetailActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static com.gahee.rss_v1.helpers.Constants.ADAPTER_POSITION;
import static com.gahee.rss_v1.helpers.Constants.ARTICLES;

public class SliderPagerAdapter extends PagerAdapter {


    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Article> arrayList;


    public SliderPagerAdapter(Context context, ArrayList<Article> arrayLists){
        this.context = context;
        //get the article list from the adapter's constructor.
        this.arrayList = arrayLists;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }
                        //return number of the articles
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_news_slider, container, false);

        //set the page number
        TextView tv_article_page_current = view.findViewById(R.id.tv_article_page_number_current);
        tv_article_page_current.setText(String.valueOf(position + 1));
        TextView tv_article_page_total = view.findViewById(R.id.tv_article_page_number_total);
        tv_article_page_total.setText(String.valueOf(arrayList.size()));

        //set the title of the article
        TextView tv_article_title = view.findViewById(R.id.tv_article_title_inner_slider);
        tv_article_title.setText(arrayList.get(position).getArticleTitle());

        //set the description of the article
        TextView tv_article_description = view.findViewById(R.id.tv_article_desc_inner_slider);
        tv_article_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ArticleDetailActivity.class);
                intent.putParcelableArrayListExtra(ARTICLES, arrayList);
                intent.putExtra(ADAPTER_POSITION, position);
                context.startActivity(intent);
            }
        });
        tv_article_description.setText(arrayList.get(position).getArticleDescription());

        //set pub date
        TextView tv_pub_date = view.findViewById(R.id.tv_article_pub_date_inner_slider);
        tv_pub_date.setText(arrayList.get(position).getPubDate());
        Log.d("pubdate", "article pub date : " + arrayList.get(position).getPubDate());


        //set the thumbnail of the article
        ImageView imageView = view.findViewById(R.id.img_inner_slider);
        Glide.with(context).load(arrayList.get(position).getMedia())
                .placeholder(R.drawable.default_pic)
                .into(imageView);

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
