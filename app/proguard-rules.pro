####################################################################################################
####################################################################################################
####################################################################################################
######################################### PROGUARD #################################################
####################################################################################################
####################################################################################################
####################################################################################################
 
# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-dump class_files.txt
-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-allowaccessmodification
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
-repackageclasses ''
-keepattributes *Annotation*
-keepattributes Signature
#-dontpreverify
 
# If you want to enable optimization, you should include the
# following:
#-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
#-optimizationpasses 5
#-allowaccessmodification
#
# Note that you cannot just include these flags in your own
# configuration file; if you are including this file, optimization
# will be turned off. You'll need to either edit this file, or
# duplicate the contents of this file and remove the include of this
# file from your project's proguard.config path property.
 
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.app.Fragment

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
native <methods>;
}
 
-keep public class * extends android.view.View {
public <init>(android.content.Context);
public <init>(android.content.Context, android.util.AttributeSet);
public <init>(android.content.Context, android.util.AttributeSet, int);
public void set*(...);
}
 
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet);
}
 
-keepclasseswithmembers class * {
public <init>(android.content.Context, android.util.AttributeSet, int);
}
 
-keepclassmembers class * extends android.app.Activity {
public void *(android.view.View);
}
 
# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
public static **[] values();
public static ** valueOf(java.lang.String);
}
 -keepclassmembers enum * {
 public static **[] values();
 public static ** valueOf(java.lang.Integer);
 }
-keep class * implements android.os.Parcelable {
public static final android.os.Parcelable$Creator *;
}
 
-keepclassmembers class **.R$* {
public static <fields>;
}

-keep class org.joda.** {
  public protected private *;
}

# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version. We know about them, and they are safe.
-injars libs
-keep class android.support.v4.** { *; }
-keep class android.support.v7.** { *; }
-keep interface android.support.v4.** { *; }

-dontwarn org.joda.convert.**
-keepclassmembers class ** {
    public void onEvent*(**);
}

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }

-keep class com.android.volley.** { *; }
-keep interface com.android.volley.** { *; }
-keep class com.facebook.** { *; }

-keep class org.apache.commons.logging.**
-dontwarn org.apache.**

-keepclassmembers class com.sefford.circularprogressdrawable.CircularProgressDrawable { *;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-dontwarn okio.**
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-dontwarn com.squareup.**
-keep class com.squareup.** { *; }
-keep class okio.** { *; }
-dontwarn com.parse.**
-keep class com.parse.** { *; }
-keep class bolts.** {*;}
-keep class com.fasterxml.jackson.databind.ObjectMapper {
    public <methods>;
    protected <methods>;
}
-keep class com.fasterxml.jackson.databind.ObjectWriter {
    public ** writeValueAsString(**);
}

-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.google.android.gms.**

-dontwarn com.viewpagerindicator.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

   -keep class in.ashwanik.** {
      public protected private *;
    }

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }