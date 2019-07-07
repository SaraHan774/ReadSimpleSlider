package com.gahee.rss_v1.startApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gahee.rss_v1.CNN.XMLUtils;
import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.CheckIfNew;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainTab.MainTabActivity;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class StartActivity extends AppCompatActivity implements
        TopicFragment.OnFragmentInteractionListener{

    private static final String TAG = StartActivity.class.getSimpleName();

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[] dots;
    private int[] startSliderLayouts;
    private CheckIfNew checkIfNew;

    //sending topics and photos to TopicsFragment.java -> TopicsSliderAdapter.java
    private String [] topics;
    private int [] photos;

    private ArrayList<Article> articles;
    private static final String BASE_URL = "http://rss.cnn.com/rss/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        viewPager = findViewById(R.id.slide_view_pager);
        linearLayout = findViewById(R.id.linear_layout_start);

        startSliderLayouts = new int[]{
                R.layout.start_slider_name,
                R.layout.start_slider_interest,
                R.layout.start_slider_wait
        };

        StartSliderAdapter startSliderAdapter = new StartSliderAdapter(this, startSliderLayouts);
        viewPager.setAdapter(startSliderAdapter);
        viewPager.addOnPageChangeListener(listener);
        addSliderDots(viewPager.getCurrentItem());


        PhotoUtils photoUtils = new PhotoUtils();
        topics = photoUtils.getTopicsOfPhotos();
        photos = photoUtils.getPhotos();

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

            switch (currentPosition){
                case 0:
                    //on the first page
                    //initialize EditText obj -> save user name in shared preference
                    //let user select the country / region (maybe)
                    //initialize check button - on Click save user name in SP
                    //make a Toast that the user name has been saved.
                    break;
                case 1:
                    //on the second page (current position 1)
                    //inflate fragment to display topics items
                    //send the topics list to view pager adapter class
                    //set the adapter to the view pager in the fragment
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.topics_fragment_placeholder, TopicFragment.newInstance(topics, photos));
                    fragmentTransaction.commit();
                    Button doneButton = findViewById(R.id.btn_done);
                    doneButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //set static boolean isSelecting to false;
                        }
                    });
                    break;
                case 2:
                    //loading page
                    //initialize animations on the dots
                    //do background work -> parsing xml & creating cards
                    //when it's ready to display contents, switch to Main Activity
                    Button button = findViewById(R.id.btn_next);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Log.d(TAG, "articles : " + articles);
                            Intent intent = new Intent(StartActivity.this, MainTabActivity.class);
                            intent.putParcelableArrayListExtra("DATA", articles);
                            startActivity(intent);
                        }
                    });

                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

