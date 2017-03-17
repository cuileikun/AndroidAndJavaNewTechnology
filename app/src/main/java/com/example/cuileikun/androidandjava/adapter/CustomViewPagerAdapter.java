package com.example.cuileikun.androidandjava.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by april on 15/12/23.
 */
public class CustomViewPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> mFragments;
    public CustomViewPagerAdapter(FragmentManager fm, List<T> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public T getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
