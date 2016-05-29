package com.yan.news.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.yan.news.BuildConfig;
import com.yan.news.R;

/**
 * 项目名称：YanNews
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/4/6 15:03
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends Application {

    private static Context mContext;


    public static volatile MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (BuildConfig.DEBUG) {//debug打开，preview 也是打开的 debuggable 为true release 关闭
            //StrictMode 解析
            // http://blog.csdn.net/brokge/article/details/8543145
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
            Logger.init("yan")                                  // default PRETTYLOGGER or use just init()
                    .methodCount(2)                             // default 2
                    .logLevel(LogLevel.FULL)                    // default LogLevel.FULL
                    .methodOffset(0)                            // default 0
                    .logTool(new AndroidLogTool());             // custom log tool, optional
        }else{
            Logger.init("yan").logLevel(LogLevel.NONE);
        }
    }

    // 获取Application的Context
    public static Context getContext() {
        if (mContext == null) {
            getMyApplication();
        }
        return mContext;
    }


    /**
     * @return Application 返回类型
     * @Title: getInstance
     * @Description: 初始化
     * @author yan
     * 此类型单例详细解析
     * http://www.race604.com/java-double-checked-singleton/
     */
    public static MyApplication getMyApplication() {
        //减少使用带volatile 修饰的mInstance 如果没有它 则需要访问两次mInstance，一次判断非空，一次return mInstance
        MyApplication instance = mInstance;
        if (null == instance) {
            synchronized (MyApplication.class) {
                if (null == instance) {
                    instance = new MyApplication();
                    //加修饰词的原因是new的时候是想开辟空间，然后再去调用构造方法，线程同步访问的时候回导致错误
                    mInstance = instance;
                }
            }
        }
        return instance;//最后返回减少调用包含关键字volatile 的mInstance 优化性能
    }
}
