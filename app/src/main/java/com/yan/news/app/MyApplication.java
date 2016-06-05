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

    //Application-Context的生命周期是整个应用， 所以，对于它的使用必须慎重，大部分情况下都要避免使用它，因为它会导致内存泄露的问题。我们先来举个例子：
    //如果我们现在在一个Activity中引入一个Application-Context，那么，当我们这个Activity关闭的时候，这个Application-Context是不会消失的，因为它的生命周期要比我们的Activity长，
    //如果只是一些用来计算的数据还好， 但是如果这个Context与我们的Activity的创建有关，或者与我们在Activity要销毁的资源比如图片资源有关，那么，因为我们的Activity或图片就不能正常销毁，
    //因为它与Application-Context相关联， 如果不能正常的释放掉与它们相关的内存，就会出现所谓的内存泄露的问题。这种问题有时候我们根本无法察觉到，所以我们必须遵守相关的使用原则。
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
