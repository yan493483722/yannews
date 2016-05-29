package com.yan.news.widget.slider.model;

/**
 * 项目名称：YanNews
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/5/9 16:03
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface SliderListener {
    /**
     * This is called when the {@link android.support.v4.widget.ViewDragHelper} calls it's
     * state change callback.
     *
     * @see android.support.v4.widget.ViewDragHelper#STATE_IDLE
     * @see android.support.v4.widget.ViewDragHelper#STATE_DRAGGING
     * @see android.support.v4.widget.ViewDragHelper#STATE_SETTLING
     *
     * @param state     the {@link android.support.v4.widget.ViewDragHelper} state
     */
    void onSlideStateChanged(int state);

    void onSlideChange(float percent);

    void onSlideOpened();

    void onSlideClosed();
}
