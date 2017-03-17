package com.example.cuileikun.androidandjava.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.adapter.ShangHaiFragmentAdapter;
import com.example.cuileikun.androidandjava.fragment.base.AddressFragment;
import com.qk.applibrary.widget.pullrefresh.PullToRefreshBase;
import com.qk.applibrary.widget.pullrefresh.PullToRefreshListView;

/**
 * 作者：popular cui
 * 时间：2017/1/16 16:13
 * 功能:
 */
public class ShangHaiFragment extends AddressFragment {

    private PullToRefreshListView listView;
    private ShangHaiFragmentAdapter adapter;

    @Override
    public void initViews(View view) {
        listView = (PullToRefreshListView) view.findViewById(R.id.shang_hai_lv);
    }

    @Override
    public void initData() {
        adapter = new ShangHaiFragmentAdapter(getActivity());
        listView.setAdapter(adapter);
    }

    @Override
    public void addListeners() {
        listView.setOnRefreshListener(loadMaturityVisitRoomListener);
    }

    private PullToRefreshBase.OnRefreshListener2 loadMaturityVisitRoomListener = new PullToRefreshBase.OnRefreshListener2<ListView>() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            /**
             * 处理下拉刷新
             */
         /*   loadBeltWaitStatu = 2;
            startIndex = 0;
            lodFullRoomList(address, false);*/
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            /**
             * 处理分页加载
             */
          /*  loadBeltWaitStatu = 3;
            startIndex++;
            lodFullRoomList(address, false);*/
        }

    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shang_hai;
    }
}
