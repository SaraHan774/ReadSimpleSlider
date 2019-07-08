package com.gahee.rss_v1.mainTab;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gahee.rss_v1.R;
import com.gahee.rss_v1.roomDatabase.NewsEntities;
import com.gahee.rss_v1.roomDatabase.NewsRepository;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainTabActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainTabActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ArrayList<String> topics = new ArrayList<>();
    ArrayList<List<NewsEntities>> newsEntities;
    NewsRepository newsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_and_navigate_activity);
//query the topics list to the database and call fetchDataBasedOnUserSelection(topicString) method.
        //and hand over the map data? or something that gets returned from the query
        //into the Fragment Pager Adapter - to display it on the my news tab
        //FragmentPagerAdapter -> recycler view adapter -> view holder handling the data
                        //inside the cardview -> another ViewPager -> Adapter -> handling the data

       //instead of fetching data from the intent, fetch data from the database.

        this.topics = getIntent().getStringArrayListExtra("topics");
        Log.d(TAG, "Topics : " + topics);
        getData(this.topics);

        //connect adapter to the view pager
        viewPager = findViewById(R.id.main_news_view_pager);
        tabLayout = findViewById(R.id.tabs);
        PagerAdapter adapter = new MainFragmentPagerAdapter(
                getSupportFragmentManager(),
                this,
                newsEntities
        );
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        //set up navigation drawer
        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void getData(ArrayList<String> topics){
        newsRepository = new NewsRepository(this);
        for(int i = 0; i < topics.size(); i++){
            newsEntities.add(newsRepository.loadNewsByTopic(topics.get(i)));
        }
        Log.d(TAG, "news entities : " + newsEntities);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onresume");
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    } //deal with navigation using recycler view


}