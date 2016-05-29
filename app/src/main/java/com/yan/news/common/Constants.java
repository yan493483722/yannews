package com.yan.news.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.yan.news.BuildConfig;
import com.yan.news.app.MyApplication;

/**
 * 项目名称：YanNews
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/4/6 15:02
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Constants {


    //    1 静态变量，静态代码块
//    2 实例变量，实例代码块
//    3 构造函数
    public static final String HOST_NAME = BuildConfig.HOST_NAME + "";

    /**
     * 只会运行一次
     */
    public static final String APP_NAME = getApplicationName(MyApplication.getContext());


    /**
     * 获得当前的应用名称
     *
     * @param context
     * @return
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo ;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }
}
