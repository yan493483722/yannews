package com.yan.news.base;

/**
 * 项目名称：YanNews
 * 类描述：mvp模式代理者 主导器 沟通Model View(Activity) 之间的桥梁
 * 创建人：yanzi
 * 创建时间：2016/3/28 16:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface BasePresenter {

    void onResume();

    void onDestroy();

}
