package com.example.cuileikun.androidandjava.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cuileikun.androidandjava.fragment.ImageFragment;

import java.util.List;

public class BigImageViewPageAdapter extends FragmentPagerAdapter {
    private List<String> urls;

    public BigImageViewPageAdapter(FragmentManager fm, List<String> urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ImageFragment.IMAGE_URL, urls.get(position));
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public int getCount() {
        return urls == null ? 0 : urls.size();
    }
}