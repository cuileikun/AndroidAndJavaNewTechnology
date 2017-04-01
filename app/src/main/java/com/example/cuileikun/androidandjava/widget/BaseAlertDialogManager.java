package com.example.cuileikun.androidandjava.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cuileikun.androidandjava.R;
import com.example.cuileikun.androidandjava.listener.BaseDoubleBtnClickListener;
import com.example.cuileikun.androidandjava.listener.BaseSingleBtnDialogClickListener;
import com.qk.applibrary.util.CommonUtil;

/**
 * Created by YanZi on 2016/12/30.
 * describe：
 * modify:
 * modify date:
 */
public class BaseAlertDialogManager {


    /**
     * 布局加载器
     */
    LayoutInflater mLayoutInflater;

    Context mContext;

    public BaseAlertDialogManager(Context context) {
        mContext = context;
        mLayoutInflater =LayoutInflater.from(context);
    }


    /**
     * @param title   对话框标题 没有传null
     * @param content 内容
     * @param btnText 按钮的文字 传null 默认显示确认
     * @return void 返回类型
     * @Title: showAlertDialog
     * @Description: 显示提示对话框，带一个确认按钮
     * @author yanzi
     */
    public void showAlertDialogSingle(String title, String content, String btnText, final BaseSingleBtnDialogClickListener listener) {

        final Dialog dialog = new Dialog(mContext, R.style.base_activity_dialog);
        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialog.setCancelable(false);
        View contentView = mLayoutInflater.inflate(R.layout.dg_base_activty, null);
        // 初始化控件
        TextView tv_title = (TextView) contentView
                .findViewById(R.id.tv_dg_double_title);
        TextView tv_content = (TextView) contentView
                .findViewById(R.id.tv_dg_double_content);
        View v_dg_blue_divider_20 = contentView
                .findViewById(R.id.v_dg_blue_divider_20);

        // 设置标题
        if (CommonUtil.isEmpty(title) && !CommonUtil.isEmpty(content)) {
            tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv_content.setVisibility(View.GONE);
            v_dg_blue_divider_20.setVisibility(View.VISIBLE);
            tv_title.setText(content);
        } else {
            tv_title.setText(title);
        }

        // 设置内容
        if (CommonUtil.isEmpty(content)) {
            tv_content.setVisibility(View.GONE);
            v_dg_blue_divider_20.setVisibility(View.VISIBLE);
        } else {
            if (!CommonUtil.isEmpty(title)) {
                tv_content.setText(content);
            }
        }

        // 设置按钮
        TextView tv_dg_blue_single = (TextView) contentView
                .findViewById(R.id.tv_dg_blue_single);

        if (!CommonUtil.isEmpty(btnText)) {
            tv_dg_blue_single.setText(btnText);
        }
        tv_dg_blue_single.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickBtn();
                }
                dialog.dismiss();
            }
        });
        dialog.setContentView(contentView);
        dialog.show();
    }

    /**
     * @param title     标题，字体大，为null时，title显示content内容且字体大
     * @param content   内容，字体小，为null时不显示内容
     * @param textLeft  左边按钮显示的文字 传null 显示为取消
     * @param textRight 右边按钮显示的文字 传null 显示为确定
     * @return void 返回类型
     * @Title: showAlertDialogDouble
     * @Description: 显示提示对话框，带"确认按钮" + “取消按钮”
     * @author yanzi
     */
    public void showAlertDialogDouble(String title, String content,
                                      String textLeft, String textRight, final BaseDoubleBtnClickListener listener) {

        final Dialog dialog = new Dialog(mContext, R.style.base_activity_dialog);
        dialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialog.setCancelable(true);

        View contentView = mLayoutInflater.inflate(R.layout.dg_base_activty, null);

        // 初始化控件
        TextView tv_title = (TextView) contentView
                .findViewById(R.id.tv_dg_double_title);
        TextView tv_content = (TextView) contentView
                .findViewById(R.id.tv_dg_double_content);
        View v_dg_blue_divider_20 = contentView
                .findViewById(R.id.v_dg_blue_divider_20);

        // 设置标题
        if (CommonUtil.isEmpty(title) && !CommonUtil.isEmpty(content)) {
            tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv_content.setVisibility(View.GONE);
            v_dg_blue_divider_20.setVisibility(View.VISIBLE);
            tv_title.setText(content);
        } else {
            tv_title.setText(title);
        }

        // 设置内容
        if (CommonUtil.isEmpty(content)) {
            tv_content.setVisibility(View.GONE);
            v_dg_blue_divider_20.setVisibility(View.VISIBLE);
        } else {
            if (!CommonUtil.isEmpty(title)) {
                tv_content.setText(content);
            }
        }
        // 设置按钮
        LinearLayout layout = (LinearLayout) contentView
                .findViewById(R.id.ll_dg_blue_double);
        layout.setVisibility(View.VISIBLE);
        TextView tv_dg_blue_single = (TextView) contentView
                .findViewById(R.id.tv_dg_blue_single);
        tv_dg_blue_single.setVisibility(View.GONE);
        TextView tv_dg_blue_cancel = (TextView) contentView
                .findViewById(R.id.tv_dg_blue_cancel);
        TextView tv_dg_blue_confirm = (TextView) contentView
                .findViewById(R.id.tv_dg_blue_confirm);
        if (!CommonUtil.isEmpty(textLeft)) {
            tv_dg_blue_cancel.setText(textLeft);
        }
        if (!CommonUtil.isEmpty(textRight)) {
            tv_dg_blue_confirm.setText(textRight);
        }
        tv_dg_blue_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickLeftBtn();
                }
                dialog.dismiss();
            }
        });
        tv_dg_blue_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.clickRightBtn();
                }
                dialog.dismiss();
            }
        });
        dialog.setContentView(contentView);
        dialog.show();

    }
}
