package com.gahee.rss_v1.mainTab;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gahee.rss_v1.CNN.model.Article;
import com.gahee.rss_v1.mainTab.mainFragments.MyFavoritesFragment;
import com.gahee.rss_v1.mainTab.mainFragments.MyNewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = MainFragmentPagerAdapter.class.getSimpleName();
    //MyNews tab returns MyNewsFragment.java and MyFavorites tab returns MyFavoritesFragment.java
    private final int PAGE_NUM = 2;
    private Context context;
    private String[] tabTitles = {"News", "Favorites"};
    private MyNewsFragment myNewsFragment = new MyNewsFragment();
    private List<ArrayList<Article>> arrayLists = new ArrayList<>();
    //need to get the data to display from MainTabActivity.java - 생성자를 통해서 전달해주어야 한다
    // 생성자 안에서 받아와서 Fragment 의 생성자로 다시 전달 (?)
    // fragment 의 recycler view 안에다가 전달해야. (뉴스 토픽)
    // + 버튼에다가 onClickListener 부터 만들어야.

    public MainFragmentPagerAdapter(FragmentManager fm, Context context, List<ArrayList<Article>> arrayLists) {
        super(fm);
        this.context = context;
        this.arrayLists = arrayLists;
        Log.d(TAG, "articles : ");
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                myNewsFragment.setData(arrayLists);
                return myNewsFragment;
            case 1:
                return new MyFavoritesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}