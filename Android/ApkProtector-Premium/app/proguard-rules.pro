-keepattributes SourceFile, LineNumberTable
-renamesourcefileattribute
-allowaccessmodification

# zipsigner
-keep class org.spongycastle.** { *; }
#-keep class org.spongycastle.jcajce.provider.** { *; }
-keep class kellinwood.security.zipsigner.** { *; }
-keep class com.android.apksig.** { *; }
-keep class com.android.apksigner.** { *; }

# dexlib2
-keep class com.google.common.collect.** { *; }

-keep class com.mcal.dexprotect.zipalign.ZipAlign { *; }

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