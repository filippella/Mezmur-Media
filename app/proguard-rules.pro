# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Filippo\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
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


-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn okio.**

-keep class com.google.inject.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }

# gson
-keep class com.google.gson.** { *; }
# okhttp, mockwebserver
-keep class okhttp3.** { *; }

# Butterknife keep config
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#-keepclassmembernames interface * {
#    @retrofit2.http.* <methods>;
#}