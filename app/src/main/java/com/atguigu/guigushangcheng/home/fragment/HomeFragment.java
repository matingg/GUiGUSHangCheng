package com.atguigu.guigushangcheng.home.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushangcheng.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/2/22.
 *
 *                 首页
 */

public class HomeFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {

        textView = new TextView(context);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;

    }


    @Override
    public void initData() {
        super.initData();
        textView.setText("首页");
        Log.e("TAG", "CommunityFragment initData()--首页");
    }
}
