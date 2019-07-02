package com.gahee.rss_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = StartActivity.class.getSimpleName();

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[] dots;
    private int[] startSliderLayouts;
    private Button buttonSkip, buttonNext;
    private CheckIfNew checkIfNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        viewPager = findViewById(R.id.slide_view_pager);
        linearLayout = findViewById(R.id.linear_layout_start);
//        buttonSkip = findViewById(R.id.btn_skip);
//        buttonNext = findViewById(R.id.btn_next);

        startSliderLayouts = new int[]{
                R.layout.start_slider_name,
                R.layout.start_slider_interest,
                R.layout.start_slider_wait
        };

        StartSliderAdapter startSliderAdapter = new StartSliderAdapter(this, startSliderLayouts);
        viewPager.setAdapter(startSliderAdapter);
        viewPager.addOnPageChangeListener(listener);
        addSliderDots(viewPager.getCurrentItem());


    }

    private void addSliderDots(int currentPage){
        dots = new TextView[startSliderLayouts.length];
        int activeColor = getResources().getColor(R.color.colorBlack);
        int inactiveColor = getResources().getColor(R.color.colorLightGrey);

        //clearing all dots
        linearLayout.removeAllViews();
        for(int i =0; i < dots.length; i++){
          dots[i] = new TextView(this);
          dots[i].setText(Html.fromHtml("&#8226"));
          dots[i].setTextSize(32);
            if(i == currentPage){
                dots[i].setTextColor(activeColor);
            }else{
                dots[i].setTextColor(inactiveColor);
            }
          linearLayout.addView(dots[i]);

        }
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int currentPosition) {

            addSliderDots(currentPosition);

            //on the second page (current position 1)
            //inflate fragment to display topics items
            //send the topics list to view pager adapter class
            //set the adapter to the view pager in the fragment

            if(currentPosition == 1){
                addTopicsToViewPagers();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.topics_fragment_placeholder, TopicFragment.newInstance(topicsItemList));
                fragmentTransaction.commit();
                Log.d(TAG, "fragment transaction committed");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //test list
    ArrayList<TopicsItem> topicsItemList = new ArrayList<>();

    private void addTopicsToViewPagers(){

        for(int i = 0; i < 10 ; i++){
         topicsItemList.add(new TopicsItem("Sample Name" + i));
        }

    }
}

