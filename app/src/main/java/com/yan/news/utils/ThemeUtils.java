package com.yan.news.utils;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by z7 on 2016/6/5.
 */
public class ThemeUtils {

    /**
     * 提取当前主题的颜色属性
     *
     * @param context   上下文
     * @param attrColor 属性的id
     * @return 颜色
     */
    public static int getColor(Context context, int attrColor) {
        int[] attrs = {attrColor};
        //获得主题的颜色 这样做的意义：在不同版本中由于主题的颜色很有可能会不一样，则根据一个id无法返回确定的需要的颜色
        //通过获得主题的颜色，再去主题的颜色中找到基本的颜色，从而得到基本色到底是谁
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs);
        int color = ta.getColor(0, 0);
        ta.recycle();
        return color;
    }



}
