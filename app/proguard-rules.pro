# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zhoujian/Library/Android/sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile






#----------------------------------------------定制化区域------------------------------------

#1.实体类


#2.第三方包


#3.与js互相调用的类


#4.反射相关的类和方法




#--------------------------------------------基本不用动区域-----------------------------------

#基本指令区

#代码混淆压缩比
-optimizationpasses 5
#不使用大小写混合类名，混淆后类名为小写
-dontusemixedcaseclassnames
#混淆第三方库
-dontskipnonpubliclibraryclasses
#去除警告
-dontskipnonpubliclibraryclassmembers
#不预校验
-dontpreverify
#混淆时记录日志
-verbose
#生成原类名和混淆后的类名的映射文件
-printmapping proguardMapping.txt
#代码混淆使用算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
#不混淆Annotation
-keepattributes *Annotation*,InnerClasses
#不混淆泛型
-keepattributes Signature
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

#默认保留区
#这些类不混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}


#native方法不混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#枚举类不混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#自定义组件不混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#自定义控件和类的成员不混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#Serializable的子类不混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Parcelable的子类不混淆

-keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator*;
}


#资源类不混淆
-keep class **.R$* {
     public static <fields>;
}

-keepclassmembers class * {
    void *(**On*Event);
}

#webview
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String);
}
















