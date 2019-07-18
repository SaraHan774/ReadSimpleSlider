package com.gahee.rss_v1.mainTab.pagerAdapters;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gahee.rss_v1.mainTab.mainFragments.MyFavoritesFragment;
import com.gahee.rss_v1.mainTab.mainFragments.MyNewsFragment;


public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = MainFragmentPagerAdapter.class.getSimpleName();
    private final String[] tabTitles = {"News", "Favorites"};
    private final Fragment [] fragments = {new MyNewsFragment(), new MyFavoritesFragment()};

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.d(TAG, "instantiate item, position : " + position);
        Fragment fragment  = (Fragment) super.instantiateItem(container, position);
        fragments[position] = fragment;
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Log.d(TAG, "setting data to my news fragment");
                return fragments[position];
            case 1:
                Log.d(TAG, "fav fragment position : " + position);
                return fragments[position];
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}