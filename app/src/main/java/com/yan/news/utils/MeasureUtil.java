package com.yan.news.utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.yan.news.R;

/**
 * Created by YanZi on 2016/6/5.
 * Description（描述）：绘制前的测量工具类
 * Modify(修改) :
 * Modify Description (修改描述):
 */
public class MeasureUtil {

    public static int getStatusBarHeight(Context context) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{R.attr.actionBarSize});
        // getDimension()方法的解析
        // http://www.th7.cn/Program/Android/201501/362463.shtml
        int toolbarHeight = (int) ta.getDimension(0, 0);
        ta.recycle();
        return toolbarHeight;
    }
}
