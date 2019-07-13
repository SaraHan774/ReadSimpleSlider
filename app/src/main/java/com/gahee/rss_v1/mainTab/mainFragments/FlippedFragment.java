package com.gahee.rss_v1.mainTab.mainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.pagerAdapters.FlippedPagerAdapter;

import java.util.ArrayList;

public class FlippedFragment extends Fragment{

//open a fragment with another view pager.
    //on click -> open fragment -> send data to the fragment - and to the view pager adapter inside the fragment
    //and make a view pager that contains a web view of the link !

    private ArrayList<Article> articles;
    public FlippedFragment(){

    }

    public void setData(ArrayList<Article> articles){
        this.articles = articles;
    }

    public static FlippedFragment newInstance() {

        Bundle args = new Bundle();

        FlippedFragment fragment = new FlippedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_rv_flipped, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager = view.findViewById(R.id.flipped_main_news_view_pager);
        PagerAdapter pagerAdapter = new FlippedPagerAdapter(getContext(), articles);
        viewPager.setAdapter(pagerAdapter);
    }



}
