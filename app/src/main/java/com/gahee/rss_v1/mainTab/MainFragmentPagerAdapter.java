package com.gahee.rss_v1.mainTab;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gahee.rss_v1.R;
import com.gahee.rss_v1.mainFragments.MyFavoritesFragment;
import com.gahee.rss_v1.mainFragments.MyNewsFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    //MyNews tab returns MyNewsFragment.java and MyFavorites tab returns MyFavoritesFragment.java
    final int PAGE_NUM = 2;
    private Context context;
    private String[] tabTitles = {"News", "Favorites"};
    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MyNewsFragment myNewsFragment = new MyNewsFragment();
                return myNewsFragment;
            case 1:
                MyFavoritesFragment myFavoritesFragment = new MyFavoritesFragment();
                return myFavoritesFragment;
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