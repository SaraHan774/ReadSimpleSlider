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

import com.gahee.rss_v1.helpers.CheckIfNew;
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
    private int[] startSliderLayouts;
    private CheckIfNew checkIfNew;

    //sending topics and photos to TopicsFragment.java -> TopicsSliderAdapter.java
    private String [] topics;
    private int [] photos;

    //fetching this list from the database
    private List<TopicStrings> topicStrings = new ArrayList<>();

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
                R.layout.read_simple_slider,
                R.layout.start_slider_name,
                R.layout.start_slider_interest,
                R.layout.start_slider_wait
        };

        StartSliderAdapter startSliderAdapter = new StartSliderAdapter(this, startSliderLayouts);
        viewPager.setAdapter(startSliderAdapter);
        viewPager.addOnPageChangeListener(listener);
        pageSwitcher(2);

        PhotoUtils photoUtils = new PhotoUtils();
        topics = photoUtils.getTopicsOfPhotos();
        photos = photoUtils.getPhotos();

        ViewModelRoom viewModelRoom = ViewModelProviders.of(this).get(ViewModelRoom.class);
        viewModelRoom.getTopicStrings().observe(this, new Observer<List<TopicStrings>>() {
            @Override
            public void onChanged(List<TopicStrings> topicStrings) {
                StartActivity.this.topicStrings = topicStrings;
                Log.d(TAG, "topic strings observed : " + topicStrings);
            }
        });

        checkIfNew = new CheckIfNew(this);
    }



    final ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int currentPosition) {

                addSliderDots(currentPosition - 1);
                switch (currentPosition){
                    case 1:
                        confirmUserName();
                        break;
                    case 2:
                        showTopicsViewPager();
                        break;
                    case 3:
                        navigateToMainTabActivity();
                        break;
                }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /*
    * adds dots to the bottom of the sliding page, if page >= 1
    * */
    private void addSliderDots(int currentPage){
        TextView[] dots = new TextView[startSliderLayouts.length - 1];
        int activeColor = getResources().getColor(R.color.colorBlack);
        int inactiveColor = getResources().getColor(R.color.colorLightGrey);

        //clearing all dots
        linearLayout.removeAllViews();
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml(getString(R.string.slider_dots_code)));
            dots[i].setTextSize(32);
            if(i == currentPage){
                dots[i].setTextColor(activeColor);
            }else{
                dots[i].setTextColor(inactiveColor);
            }
            linearLayout.addView(dots[i]);

        }
    }

    /*
    * let the user to type in their name, and saves it using shared preference
    * */
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

    /*
    * initializes the view pager letting the user to select topics
    * */
    private void showTopicsViewPager(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.topics_fragment_placeholder, TopicFragment.newInstance(topics, photos));
        fragmentTransaction.commit();
    }

    /*
    * when the user clicks start button, call to the remote data source will be made.
    * This call will fetch RSS urls based on user selected topics.
    * after fetching, the user will be navigated to MainTabActivity.
    * */
    private void navigateToMainTabActivity(){
        final Button button = findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText(getResources().getString(R.string.ready));
                if(RepositoryRemote.getInstance().getList() != null){
                    RepositoryRemote.getInstance().getList().clear();
                }
                for(int i = 0; i < topicStrings.size(); i++){
                    RepositoryRemote.getInstance().requestDataAsync(topicStrings.get(i).getTopicString());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //set first time launch to false only if the user selected any topics
                checkIfNew.setIsFirstTimeLaunch(false);
                Intent intent = new Intent(StartActivity.this, MainTabActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    //variables for the timer
    private Timer timer;
    private int page = 0;

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000);
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {
                    if (page > 1) {
                        timer.cancel();
                    } else {
                        viewPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }
}

