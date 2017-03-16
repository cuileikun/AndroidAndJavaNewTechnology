package com.example.cuileikun.androidandjava.fragment;

import android.content.Context;
import android.os.Handler;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.example.cuileikun.androidandjava.fragment.base.AddressFragment;
import com.qk.applibrary.util.SharedPreferencesUtil;

/**
 * 作者：popular cui
 * 时间：2017/1/16 16:15
 * 功能:
 */
public class HaiNanFragment extends AddressFragment {
    private Context mContext;
    private Handler mHandler;

    public HaiNanFragment(Handler handler) {
        super();

    }

    public HaiNanFragment(Handler handler, Context mContext) {
        super();
        this.mContext=mContext;
        this.mHandler = handler;
    }

    @Override
    public void initData() {
        super.initData();
        SharedPreferencesUtil.setSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                QkConstant.SharedPreferencesKeyDef.CHANGLIANGZHI, "666666");
        mHandler.sendMessage(mHandler.obtainMessage(66));


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hai_nan;
    }
}
