package com.yan.news.common;

import android.content.Context;
import android.view.CollapsibleActionView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by z7 on 2016/6/4.
 */
public class StatusBarFactory {

    public static View createStatusBar(Context context, int color) {
        int resource = context.getResources().getIdentifier("status_bar_height", "dimen", "android");//部分系统资源无法直接得到，通过这种方法得到
        int statusBarHeight = context.getResources().getDimensionPixelOffset(resource);
        //绘制一个和状态栏一样高的矩形
        View statusView = new View(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        statusView.setLayoutParams(layoutParams);
        statusView.setBackgroundColor(color);
        return statusView;
    }
}
