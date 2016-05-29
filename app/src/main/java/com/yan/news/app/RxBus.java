package com.yan.news.app;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;


/**
 * 项目名称：YanNews
 * 类描述：自定义实现RxAndroid的事件订阅器
 * 需要了解的基本知识：双重锁单例加优化 http://www.race604.com/java-double-checked-singleton/
 * 创建人：yanzi
 * 创建时间：2016/5/25 15:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RxBus {

    // 关于ConcurrentMap ConcurrentHashMap是HashMap的线程安全版本  http://blog.csdn.net/fg2006/article/details/6404226
    private ConcurrentMap<Object, List<Subject>> mSubjectMapper = new ConcurrentHashMap<>();
    /**
     * 单例实例
     */
    private static volatile RxBus mInstance;

    private RxBus() {
    }

    public static RxBus getRxbus() {
        RxBus rxBus = mInstance;
        if (rxBus == null) {
            synchronized (RxBus.class) {
                rxBus = mInstance;
                if (rxBus == null) {
                    rxBus = new RxBus();
                    mInstance = rxBus;
                }
            }
        }
        return rxBus;
    }


    /**
     * 注册观察事件
     *
     * @param tag   标签
     * @param clazz 类
     * @param <T>   类型
     * @return 被观察者
     */
    public <T> Observable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        //得到订阅者
        List<Subject> subjectList = mSubjectMapper.get(tag);
        if (null == subjectList) {//如果这个标签没有，就加入新的
            subjectList = new ArrayList<>();
            mSubjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject;
        //PublishSubject 在订阅发生后的将这个时间后的原始的Observable的数据发送给订阅者
        subjectList.add(subject = PublishSubject.create());
        Logger.d("RxBus register" + mSubjectMapper);//看看是否把新的订阅者放进去了
        return subject;
    }

    /**
     * 取消注册
     *
     * @param tag        标签
     * @param observable 被观察者
     */
    public void unRegister(@NonNull Object tag, @NonNull Observable observable) {
        List<Subject> subjectList = mSubjectMapper.get(tag);
        if (null != subjectList) {
            subjectList.remove(observable);
            if (mSubjectMapper.isEmpty()) {
                mSubjectMapper.remove(tag);
            }
        }
        Logger.d("RxBus unregister" + mSubjectMapper);
    }

    /**
     * 发送事件
     *
     * @param tag     标签
     * @param content 内容
     */
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = mSubjectMapper.get(tag);
        if (null != subjectList && !mSubjectMapper.isEmpty()) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
    }
}
