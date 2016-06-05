package com.yan.news.base;

import android.content.Context;
import android.content.res.Resources;
import android.database.Observable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yan.news.BuildConfig;
import com.yan.news.R;
import com.yan.news.annotation.ActivityFragmentInject;
import com.yan.news.common.StatusBarFactory;
import com.yan.news.module.news.ui.NewsActivity;
import com.yan.news.module.setting.ui.SettingsAty;
import com.yan.news.utils.SpUtil;
import com.yan.news.utils.ThemeUtils;
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
     * 因为使用Application-Cntext会出现内存泄露的危险，所以我们一般都是使用Activity-Context。下面就是这两者的使用规则：
     * 不要让生命周期长的对象引用Activity-Context，保证引用要与Ativity本身生命周期是一样的，对于生命周期长的对象，使用Application-Context 。
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
        if (mHasNavigationView) {
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
        if (this instanceof SettingsAty) {
            SkinManager.getInstance().unregister(this);
        }
        if (mPresenter != null) {//代理行为的类
            mPresenter.onResume();
        }
        //当前页面是否关闭
        if (mFinishObservable != null) {

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

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        handleStatusView();
        handleAppBarLayoutOffset();
//        mDrawerLayout=findViewById(R.id.)
//        mDrawerLayout=findViewById(R.id.)
    }



    /**
     * android 4.4以上 将布局延伸到状态栏
     * 关于本部分内容的详细解析可以参考 https://www.zhihu.com/question/31468556
     */
    private void handleStatusView() {
        //针对4.4的状态栏和5.0的沉浸式状态栏不一样 4.4的状态栏不是透明的，5.0的状态栏是透明的
        //这里是针对4.4进行的状态栏的处理
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT && this instanceof SettingsAty) {
            //生成状态栏布局
            View statusBarViews = StatusBarFactory.createStatusBar(mContext, ThemeUtils.getColor(mContext, R.attr.colorPrimary));
            //获得具有  弹性的根布局CoordinatorLayout
            ViewGroup contentLayout = (ViewGroup) mDrawerLayout.getChildAt(0);
            //将布局添加到布局的第一个中，实现状态栏的添加
            contentLayout.addView(statusBarViews, 0);
            View view = contentLayout.getChildAt(1);
            if (!(contentLayout instanceof LinearLayout) & view != null) {//如果不是LinearLayout 则需要设置，是Linear 就不需要
                view.setPadding(0, statusBarViews.getHeight(), 0, 0);
            }
            ViewGroup drawer= (ViewGroup) mDrawerLayout.getChildAt(1);
            //使得可布局空间拓展到状态栏
            mDrawerLayout.setFitsSystemWindows(false);
            contentLayout.setFitsSystemWindows(false);
            //可以理解为padding悬浮
            // http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0317/2613.html
            contentLayout.setClipToPadding(false);
            drawer.setFitsSystemWindows(false);
            //设置界面可以直接换肤，所以要特殊处理
            if(this instanceof SettingsAty){
                statusBarViews.setTag("skin:primary:background");
                view.setTag("skin:primary:background");
            }
        }else if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
            mDrawerLayout.setStatusBarBackgroundColor(ThemeUtils.getColor(mContext, R.attr.colorPrimary));
        }
    }

    /**
     *
     */
    private void handleAppBarLayoutOffset() {
    }
}
