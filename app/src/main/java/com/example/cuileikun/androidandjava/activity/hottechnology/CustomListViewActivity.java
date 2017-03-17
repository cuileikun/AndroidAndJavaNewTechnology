package com.example.cuileikun.androidandjava.activity.hottechnology;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.activity.Constant.QkConstant;
import com.qk.applibrary.activity.QkActivity;
import com.qk.applibrary.listener.TopbarImplListener;
import com.qk.applibrary.util.CommonUtil;
import com.qk.applibrary.util.SharedPreferencesUtil;
import com.qk.applibrary.widget.TopbarView;

public class CustomListViewActivity extends QkActivity {
    private TextView searchTv;
    private TopbarView topbarView;
    private ImageView deleteIv;
    private Context mContext;
    private PopupWindow mPopupWindow;
    private EditText searchKey;
    @Override
    public void initViews() {
        mContext=this;
        searchTv = (TextView) findViewById(R.id.search_tv);
        topbarView = (TopbarView) findViewById(R.id.top_bar_view);
        deleteIv = (ImageView) findViewById(R.id.delete_iv);
        searchKey = (EditText) findViewById(R.id.key_et);
        showPopUpWindow();
    }

    private void showPopUpWindow() {
        String searchHistory = SharedPreferencesUtil.getSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                QkConstant.History.FGYNOSETTLESEARCHHISTORY);

        if (CommonUtil.isEmpty(searchHistory)) {
            return;
        }
        View popupView = getLayoutInflater().inflate(R.layout.history_popupwindow, null);
        mPopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ListView listView = (ListView) popupView.findViewById(R.id.listview);
        TextView clears = (TextView) popupView.findViewById(R.id.clears);
        final String[] items = searchHistory.split(";");

        listView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, items));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchKey.setText(items[position]);
           //     address = items[position];
                if (!mPopupWindow.isShowing()) {
                    mPopupWindow.showAsDropDown(view);
                } else {
                    mPopupWindow.dismiss();
                }
              /*  index = 1;
                loadBeltWaitStatu = 1;
                lodNoSettleCheckOutList(index, true);*/
            }
        });
        clears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.setSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                        QkConstant.History.FGYNOSETTLESEARCHHISTORY, "");
                //点击
                mPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void addListeners() {
        searchTv.setOnClickListener(searchListener);
        searchKey.setOnFocusChangeListener(onFocusChangeListener);
        topbarView.setTopBarClickListener(topListener);
    }
    /**
     * 搜索框焦点变化监听
     */
    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //  showPopUpWindow();
            if (hasFocus) {
                //弹出popupWindow展示所有历史搜索记录
                if (null == mPopupWindow) {
                    return;
                }
                if (!mPopupWindow.isShowing()) {
                    mPopupWindow.showAsDropDown(v);
                }
            } else {
                if (null == mPopupWindow) {
                    return;
                }
                mPopupWindow.dismiss();
            }
        }
    };
    private TopbarImplListener topListener = new TopbarImplListener() {
        @Override
        public void leftButtonClick() {
            CustomListViewActivity.this.finish();
        }
    };

    //删除图标点击事件
    private View.OnClickListener clearSearchEditOnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchKey.setText("");
        }
    };

    /**
     * 点击搜索事件
     */
    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchKey.clearFocus();//失去焦点
            String search = searchKey.getText().toString();
          /*  index = 1;
            loadBeltWaitStatu = 1;
            address = search;
            lodNoSettleCheckOutList(index, true);*/
            String searchHistory = SharedPreferencesUtil.getSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                    QkConstant.History.FGYNOSETTLESEARCHHISTORY);
            if (CommonUtil.isEmpty(searchHistory)) {
                SharedPreferencesUtil.setSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                        QkConstant.History.FGYNOSETTLESEARCHHISTORY, search);
            } else {
                if (searchHistory.contains(";")) {
                    if (searchHistory.split(";").length >= 20) {
                        searchHistory = searchHistory.substring(0, searchHistory.lastIndexOf(";"));
                    }
                }
                if (!searchHistory.contains(search)) {
                    SharedPreferencesUtil.setSetting(QkConstant.SharedPreferencesKeyDef.FILE_NAME, mContext,
                            QkConstant.History.FGYNOSETTLESEARCHHISTORY, search + ";" + searchHistory);
                }
            }
         //   lodNoSettleCheckOutList(index, true);
         //   mPopupWindow.dismiss();
          //  adapter.notifyDataSetChanged();
        }


    };
    @Override
    public void initData() {
        topbarView.setTopbarTitle("自定义listView+历史记录");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_list_view;
    }


}
