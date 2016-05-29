package com.yan.news.base;

import android.content.Context;
import android.content.res.Resources;
import android.database.Observable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yan.news.BuildConfig;
import com.yan.news.R;
import com.yan.news.annotation.ActivityFragmentInject;
import com.yan.news.module.news.ui.NewsActivity;
import com.yan.news.module.setting.ui.SettingsAty;
import com.yan.news.utils.SpUtil;
import com.yan.news.widget.slider.model.SliderInterface;
import com.yan.news.widget.slider.utils.SliderUtil;
import com.zhy.changeskin.SkinManager;

import butterknife.ButterKnife;

/**
 * 项目名称：YanNews
 * 类描述：
 * 创建人：yanzi
 * 创建时间：2016/3/28 16:19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseAty<T extends BasePresenter> extends AppCompatActivity implements BaseView, View.OnClickListener {

    /**
     * 代理类的通用行为<处理数据，绑定视图>
     */
    private T mPresenter;

    /**
     * 标识该activity是否可滑动退出,默认false
     */
    protected boolean mEnableSlider;

    /**
     * 布局的视图 不包含标题toolbar
     */
    private int mContentViewId;

    /**
     * 是有导航栏
     */
    private boolean mHasNavigationView;

    /**
     * 侧滑布局
     */
    private DrawerLayout mDrawerLayout;

    /**
     * 菜单栏
     */
    private int mMenuId;

    /**
     * Toolbr的标题
     */
    private int mToolbarTitle;


    /**
     * 默认选中的item
     */
    private int mMenuDefaultCheckedItem;

    /**
     * toolbar 选中样式
     */
    private int mToolbarIndicator;

    /**
     * 侧滑监听
     */
    private SliderInterface mSliderInterface;

    /**
     * 当前页面是否关闭的观察者
     */
    private Observable<Boolean> mFinishObservable;

    /**
     * 资源的引用
     */
    protected Resources mResources;

    /**
     * 资源的引用
     */
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //自定义注解
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {
            ActivityFragmentInject annotation = getClass().getAnnotation(ActivityFragmentInject.class);
            mContentViewId = annotation.contentViewId();
            mEnableSlider = annotation.enableSlider();
            mMenuId = annotation.menuId();
            mHasNavigationView = annotation.hasNativeView();
            mToolbarTitle = annotation.toolbarTitleView();
            mToolbarIndicator = annotation.toolbarIndicator();
            mMenuDefaultCheckedItem = annotation.MenuDefaultCheckedItem();
        } else {
            //抛出异常 打印日志
            throw new RuntimeException("The Class must add own annotations of ActivityFragmentInitParams.class");
        }
        //设置界面
        if (this instanceof SettingsAty) {
            SkinManager.getInstance().register(this);
        }
        initTheme();


        setContentView(mContentViewId);
        ButterKnife.bind(this);
        //注解 必须在setContent()后面

        if (mEnableSlider && !SpUtil.readBoolean("disableSlide")) {//用户存了不开启侧滑
            // 默认开启侧滑，默认是整个页码侧滑
            mSliderInterface = SliderUtil
                    .initSlidrDefaultConfig(this, SpUtil.readBoolean("enableSlideEdge"));
        }
        //实例化相关的资源方便调用
        mContext = this;
        mResources = mContext.getResources();
        if(mHasNavigationView){
            initNavigationView();
        }

        //给子类调用
        initView();


    }

    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {//代理行为的类
            mPresenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //设置界面
        if(this instanceof SettingsAty){
            SkinManager.getInstance().unregister(this);
        }
        if (mPresenter != null) {//代理行为的类
            mPresenter.onResume();
        }
        //当前页面是否关闭
       if(mFinishObservable!=null){

       }

    }

    @Override
    public void toast(String msg) {
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void alertDialogSingle() {

    }

    @Override
    public void alertDialogDouble() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化主题
     */
    private void initTheme() {
        if (this instanceof NewsActivity) {
            setTheme(SpUtil.readBoolean(
                    "enableNightMode") ? R.style.BaseAppThemeNight_LauncherAppTheme : R.style.BaseAppTheme_LauncherAppTheme);
        } else if (!mEnableSlider && mHasNavigationView) {
            setTheme(SpUtil.readBoolean(
                    "enableNightMode") ? R.style.BaseAppThemeNight_AppTheme : R.style.BaseAppTheme_AppTheme);
        } else {
            setTheme(SpUtil.readBoolean(
                    "enableNightMode") ? R.style.BaseAppThemeNight_SlidrTheme : R.style.BaseAppTheme_SlidrTheme);
        }
    }

    private void initNavigationView(){
        mDrawerLayout=findViewById(R.id.)
//        mDrawerLayout=findViewById(R.id.)
    }

}