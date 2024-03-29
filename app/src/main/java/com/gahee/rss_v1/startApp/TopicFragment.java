package com.gahee.rss_v1.startApp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gahee.rss_v1.R;

import org.jetbrains.annotations.NotNull;


public class TopicFragment extends Fragment{

    private static final String TAG = TopicFragment.class.getSimpleName();

    private static final String PARAM1 = "param1";
    private static final String PARAM2 = "param2";


    private ViewPager viewPager;
    private String [] topics;
    private int [] photos;

    private ImageView imageView;

    public TopicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.topics_view_pager);
        TopicsSliderAdapter topicsSliderAdapter = new TopicsSliderAdapter(topics, photos, view.getContext());
        viewPager.setAdapter(topicsSliderAdapter);
        viewPager.setPageMargin(24);
        transformViewPager();
        Log.d(TAG, "page margin" + viewPager.getPageMargin());
    }

    private void transformViewPager(){
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                int pageWidth = viewPager.getMeasuredWidth() - viewPager.getPaddingLeft() - viewPager.getPaddingRight();
                int pageHeight = viewPager.getHeight();
                int paddingLeft = viewPager.getPaddingLeft();
                float transformPos = (float) (page.getLeft() - (viewPager.getScrollX() + paddingLeft)) / pageWidth;

                final float normalizedposition = Math.abs(Math.abs(transformPos) - 1);
                page.setAlpha(normalizedposition + 0.5f);

                int max = -pageHeight / 10;

                if (transformPos < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setTranslationY(0);
                } else if (transformPos <= 1) { // [-1,1]
                    page.setTranslationY(max * (1-Math.abs(transformPos)));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    page.setTranslationY(0);
                }
            }
        });
    }

    public static TopicFragment newInstance(String [] topics, int [] photos) {
        TopicFragment fragment = new TopicFragment();
        Log.d(TAG, "newInstance");

        Bundle args = new Bundle();
        args.putStringArray(PARAM1, topics);
        args.putIntArray(PARAM2, photos);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           topics = getArguments().getStringArray(PARAM1);
           photos = getArguments().getIntArray(PARAM2);
        }
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
