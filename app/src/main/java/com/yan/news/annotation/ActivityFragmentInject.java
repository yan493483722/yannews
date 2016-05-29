package com.yan.news.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称：YanNews
 * 类描述：activity和fragment的注解
 * 创建人：yanzi
 * 创建时间：2016/4/5 11:05
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@Target({ElementType.TYPE})//作用域 类，方法，变量等，这里是类
@Retention(RetentionPolicy.RUNTIME)//生命周期 运行时
//http://www.importnew.com/10294.html 注解的理解
public @interface ActivityFragmentInject {

    /**
     * 顶部局的id
     *
     * @return
     */
    int contentViewId() default -1;

    /**
     * 菜单id
     *
     * @return
     */
    int menuId() default -1;

    /**
     * 是否可以侧滑关闭页面
     *
     * @return
     */
    boolean enableSlider() default false;

    /**
     * 是否有NativeView
     *
     * @return
     */
    boolean hasNativeView() default false;

    /**
     * toolbar标题id
     *
     * @return
     */
    int toolbarTitleView() default -1;

    /**
     * toolbar的选中按钮
     *
     * @return
     */
    int toolbarIndicator() default -1;

    /**
     * toolbar默认选中项
     *
     * @return
     */
    int MenuDefaultCheckedItem() default -1;
}
