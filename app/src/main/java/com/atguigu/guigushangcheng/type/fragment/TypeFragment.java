package com.atguigu.guigushangcheng.type.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushangcheng.base.BaseFragment;

/**
 * Created by 麻少亭 on 2017/2/22.
 *
 *              分类
 *
 */

public class TypeFragment extends BaseFragment {
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
        textView.setText("分类");
        Log.e("TAG", "ShoppingFragment initData()-- 分类");
    }
}
