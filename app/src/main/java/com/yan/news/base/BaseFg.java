package com.yan.news.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 项目名称：YanNews
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/4/5 18:55
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseFg extends Fragment{

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
