package com.yan.news.base;

/**
 * 项目名称：YanNews
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/3/28 16:20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface BaseView {

    void toast(String msg);

    void showProgress();

    void hideProgress();

    void alertDialogSingle();

    void alertDialogDouble();

}
