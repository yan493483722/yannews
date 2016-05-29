# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#去除系统警告
-dontwarn android.support.**
#避免混淆内部类 如果内部类混淆报错建议关掉
-keepattributes *InnerClasses*
#避免混淆泛型 如果混淆报错建议关掉
-keepattributes *Signature*
# ==============================keep annotation start==================================
-keepattributes *Annotation*
-dontwarn *Annotation*
-keep class * extends java.lang.annotation.Annotation{*;}
# ==============================keep annotation end======================================
# ==============================keep fastjson start======================================
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** { *; }
# ==============================keep fastjson end======================================
# ==============================keep logger start======================================
-dontwarn com.orhanobut.logger.**
-keep class com.orhanobut.logger.** { *; }
# ==============================keep logger end======================================
# ==============================keep logger start======================================
-dontwarn butterknife.**
-keep class butterknife.** { *; }
# ==============================keep logger end======================================
# ==============================rx android==================================
-dontwarn rx.internal.util.unsafe.**
-keep class rx.internal.util.unsafe.**{*;}
# ==============================rx android==================================