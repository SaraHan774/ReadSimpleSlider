package com.gahee.rss_v1.startApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gahee.rss_v1.CheckIfNew;
import com.gahee.rss_v1.R;
import com.gahee.rss_v1.helpers.PhotoUtils;
import com.gahee.rss_v1.mainTab.MainTabActivity;
import com.gahee.rss_v1.remoteDataSource.RepositoryRemote;
import com.gahee.rss_v1.roomDatabase.ViewModelRoom;
import com.gahee.rss_v1.roomDatabase.TopicStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.gahee.rss_v1.helpers.Constants.SHARED_PREF_USER_NAME;
import static com.gahee.rss_v1.helpers.Constants.USER_NAME_KEY;

public class StartActivity extends AppCompatActivity{

    private static final String TAG = StartActivity.class.getSimpleName();

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private TextView[] dots;
    private int[] startSliderLayouts;
    private CheckIfNew checkIfNew;

    //sending topics and photos to TopicsFragment.java -> TopicsSliderAdapter.java
    private String [] topics;
    private int [] photos;

    //fetching this list from the database
    private List<TopicStrings> topicStrings = new ArrayList<>();
    private ViewModelRoom viewModelRoom;

    //first page views
    EditText editText;
    ImageButton userNameConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Log.d(TAG, "onCreate of the Start Activity");

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

        viewModelRoom = ViewModelProviders.of(this).get(ViewModelRoom.class);
        viewModelRoom.getTopicStrings().observe(this, new Observer<List<TopicStrings>>() {
            @Override
            public void onChanged(List<TopicStrings> topicStrings) {
                StartActivity.this.topicStrings = topicStrings;
                Log.d(TAG, "topic strings observed : " + topicStrings);
            }
        });

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
                    Log.d(TAG, "on page selected 0 ");
                    // how to get rid of NPE ?
                    //confirmUserName();
                    break;
                case 1:
                    Log.d(TAG, "on page selected 1 ");
                    showTopicsViewPager();
                    break;
                case 2:
                    Log.d(TAG, "on page selected 2 ");
                    navigateToMainTabActivity();
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




    private void confirmUserName(){
        editText = findViewById(R.id.et_type_name);
        userNameConfirm = findViewById(R.id.img_btn_name_confirm);

        userNameConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().isEmpty()){
                    editText.setError(getResources().getString(R.string.name_error));
                    editText.requestFocus();
                }else{
                    String userName = editText.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_USER_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USER_NAME_KEY, userName);
                    editor.apply();

                    Toast.makeText(StartActivity.this,
                            getResources().getString(R.string.name_confirmed),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void showTopicsViewPager(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.topics_fragment_placeholder, TopicFragment.newInstance(topics, photos));
        fragmentTransaction.commit();
    }

    private void navigateToMainTabActivity(){
        final Button button = findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : this seems very inefficient
                button.setText(getResources().getString(R.string.ready));
                if(RepositoryRemote.getInstance().getList() != null){
                    RepositoryRemote.getInstance().getList().clear();
                }
                for(int i = 0; i < topicStrings.size(); i++){
                    RepositoryRemote.getInstance().requestDataAsync(topicStrings.get(i).getTopicString());
                }
//                            how to transit from start activity to main tab activity ?
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(StartActivity.this, MainTabActivity.class);
                startActivity(intent);
            }
        });
    }
}

