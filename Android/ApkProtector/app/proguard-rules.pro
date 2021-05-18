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

# StringFog
-keep class com.mcal.stringfog.xor.StringFogImpl { *; }

-keep class com.mcal.apkprotector.zipalign.ZipAlign { *; }

# Logger
-keep class org.slf4j.LoggerFactory { *; }

-obfuscationdictionary proguard-bin.txt
-packageobfuscationdictionary proguard-bin.txt
-classobfuscationdictionary proguard-bin.txt