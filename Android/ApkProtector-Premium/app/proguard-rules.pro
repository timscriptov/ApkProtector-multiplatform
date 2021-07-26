-keepattributes SourceFile, LineNumberTable
-renamesourcefileattribute
-allowaccessmodification

#-keep class com.mcal.dexprotect.utils.Utils
#-keep class com.mcal.dexprotect.utils.security.SignatureCheck
#-keep class com.mcal.dexprotect.utils.CommonUtils
#-keep class com.mcal.dexprotect.utils.security.SecurityUtils

#-keep class android.sun.** { *; }
#-keep class bin.** { *; }
#-keep class com.android.multidex.** { *; }
-keep class com.android.apksigner.** { *; }
-keep class com.android.apksig.** { *; }
#-keep class com.android.dex.** { *; }
#-keep class com.android.dx.** { *; }
#-keep class pxb.android.** { *; }
#-keep class org.jfclone.** { *; }
#-keep class com.mcal.resguard.** { *; }
#-keep class org.jf.** { *; }

# zipsigner
-keep class org.spongycastle.** { *; }
-keep class kellinwood.** { *; }

# dexlib2
-keep class com.google.common.collect.** { *; }

-keep class com.mcal.apkprotector.zipalign.ZipAlign { *; }

-obfuscationdictionary proguard-bin.txt
-packageobfuscationdictionary proguard-bin.txt
-classobfuscationdictionary proguard-bin.txt

-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    public static void checkExpressionValueIsNotNull(java.lang.Object, java.lang.String);
    public static void checkFieldIsNotNull(java.lang.Object, java.lang.String);
    public static void checkFieldIsNotNull(java.lang.Object, java.lang.String, java.lang.String);
    public static void checkNotNull(java.lang.Object);
    public static void checkNotNull(java.lang.Object, java.lang.String);
    public static void checkNotNullExpressionValue(java.lang.Object, java.lang.String);
    public static void checkNotNullParameter(java.lang.Object, java.lang.String);
    public static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
    public static void checkReturnedValueIsNotNull(java.lang.Object, java.lang.String);
    public static void checkReturnedValueIsNotNull(java.lang.Object, java.lang.String, java.lang.String);
    public static void throwUninitializedPropertyAccessException(java.lang.String);
}