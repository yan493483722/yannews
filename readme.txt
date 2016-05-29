1.2016年3月28日16:58:36
    参考范例
        https://github.com/oubowu/OuNews
    阅读开发注意事项
    开始基类的编写
        toast
        dialog
        progress
    MVP模式的理解
        http://www.cnblogs.com/liuling/archive/2015/12/23/mvp-pattern-android.html
    泛型的初步理解
        http://www.cnblogs.com/lwbqqyumidi/p/3837629.html

    //==================================gradle打包相关====================================
    //==================================gradle打包相关====================================
    gradle打包的注意事项
    http://www.cnblogs.com/qianxudetianxia/p/4948499.html
    具体打包原理可见本层目录中的 gradle.txt
    http://blog.csdn.net/maosidiaoxian/article/details/41944325
	打包的几种方式
	http://blog.csdn.net/shineflowers/article/details/45042485

	必备命令：
	adb install XXX
	adb uninstall XXX
	cls 清屏
	adb devices
	gradle build 打所有的包debug release preview等
	gradle clean 清空所有的输出打包前必备
	gradle assembleXXX //没有空格 生成特定比如只要release版本

	#####还需要掌握

	配置改名  android:label="${app_label}"
    多个manifestPlaceholders = [YOU_KEY1: "YOU_VALUE1",YOU_KEY2: "YOU_VALUE2"]
	!!!如何使用android.defaultConfig.testInstrumentationRunner此类变量
    //使用jenkins自动化构建android和ios应用
	http://www.jayfeng.com/2015/10/22/%E4%BD%BF%E7%94%A8jenkins%E8%87%AA%E5%8A%A8%E5%8C%96%E6%9E%84%E5%BB%BAandroid%E5%92%8Cios%E5%BA%94%E7%94%A8/
    gradle一些技巧
    http://blog.csdn.net/skykingf/article/details/50528605
    //==================================gradle打包相关====================================
    //==================================gradle打包相关====================================

    //fastJson   https://github.com/alibaba/fastjson/wiki/Android%E7%89%88%E6%9C%AC
    //新的idea，将自己的基类封装为一个包，新开项目直接导入
    //


2.2016年5月3日14:19:18
    关于注解框架
    Butter Knife 官方文档
    http://jakewharton.github.io/butterknife/
    Butter Knife git-hub
    https://github.com/JakeWharton/butterknife
    android annotations 框架
    https://github.com/excilys/androidannotations
    Butter Knife  详细解析
    http://blog.csdn.net/itjianghuxiaoxiong/article/details/50177549
    相关资料
    //注解框架的异同
    http://my.oschina.net/jack1900/blog/296953
    //自定义注解
    http://blog.csdn.net/lmj623565791/article/details/43452969

