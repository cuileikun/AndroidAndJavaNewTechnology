package com.example.cuileikun.androidandjava.fragment;

import android.content.Context;
import android.view.View;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.adapter.SearchFragmentAdapter;
import com.example.cuileikun.androidandjava.fragment.base.AddressFragment;
import com.qk.applibrary.widget.pullrefresh.PullToRefreshListView;

/**
 * 作者：popular cui
 * 时间：2017/2/17 14:18
 * 功能:约球fragment
 */
public class SearchFragment extends AddressFragment {
    private Context mContext;
    private PullToRefreshListView listView;
    SearchFragmentAdapter adapter;

    @Override
    public void initViews(View view) {
        mContext = getActivity();
        listView = (PullToRefreshListView) view.findViewById(R.id.with_look_user_lv);
    }

    @Override
    public void initData() {
        adapter = new SearchFragmentAdapter(getActivity());
        listView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
}
