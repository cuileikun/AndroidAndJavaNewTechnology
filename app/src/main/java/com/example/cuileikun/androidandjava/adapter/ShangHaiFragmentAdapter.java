package com.example.cuileikun.androidandjava.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.cuileikun.androidandjava.R;

/**
 * 作者：popular cui
 * 时间：2017/1/16 17:13
 * 功能:
 */
public class ShangHaiFragmentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Activity activity;

    public ShangHaiFragmentAdapter(FragmentActivity activity) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mInflater.inflate(R.layout.fragment_sorta_full_room_item, null);
    }
}
