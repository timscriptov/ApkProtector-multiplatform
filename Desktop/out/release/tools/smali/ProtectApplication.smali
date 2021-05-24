.class public Lcom/mcal/apkprotector/ProtectApplication;
.super Landroid/app/Application;
.source ""


# static fields
.field private static final appName:Ljava/lang/String;

.field private static context:Landroid/content/Context;
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "StaticFieldLeak"
        }
    .end annotation
.end field


# direct methods
.method public static constructor <clinit>()V
    .registers 1

    .line 23
    invoke-static {}, Lcom/mcal/apkprotector/ProtectApplication;->realApplication()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->appName:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 17
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    return-void
.end method

.method private changeTopApplication(Ljava/lang/String;)Landroid/app/Application;
    .registers 13
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "appClassName"
        }
    .end annotation

    const/4 v0, 0x0

    new-array v1, v0, [Ljava/lang/Object;

    const-string v2, "android.app.ActivityThread"

    const/4 v3, 0x0

    const-string v4, "currentActivityThread"

    .line 71
    invoke-static {v2, v3, v4, v1, v3}, Lcom/mcal/apkprotector/utils/Reflect;->invokeMethod(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    const-string v4, "mBoundApplication"

    .line 72
    invoke-static {v2, v1, v4}, Lcom/mcal/apkprotector/utils/Reflect;->getFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    const-string v5, "android.app.ActivityThread$AppBindData"

    const-string v6, "info"

    .line 75
    invoke-static {v5, v4, v6}, Lcom/mcal/apkprotector/utils/Reflect;->getFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    const-string v7, "android.app.LoadedApk"

    const-string v8, "mApplication"

    .line 79
    invoke-static {v7, v6, v8, v3}, Lcom/mcal/apkprotector/utils/Reflect;->setFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    const-string v8, "mInitialApplication"

    .line 80
    invoke-static {v2, v1, v8}, Lcom/mcal/apkprotector/utils/Reflect;->getFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    const-string v10, "mAllApplications"

    .line 85
    invoke-static {v2, v1, v10}, Lcom/mcal/apkprotector/utils/Reflect;->getFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/util/ArrayList;

    .line 87
    invoke-virtual {v10, v9}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const-string v9, "mApplicationInfo"

    .line 90
    invoke-static {v7, v6, v9}, Lcom/mcal/apkprotector/utils/Reflect;->getFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Landroid/content/pm/ApplicationInfo;

    const-string v10, "appInfo"

    .line 93
    invoke-static {v5, v4, v10}, Lcom/mcal/apkprotector/utils/Reflect;->getFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/content/pm/ApplicationInfo;

    .line 96
    iput-object p1, v9, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    .line 97
    iput-object p1, v4, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    const/4 p1, 0x2

    new-array v4, p1, [Ljava/lang/Object;

    .line 101
    sget-object v5, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    aput-object v5, v4, v0

    const/4 v5, 0x1

    aput-object v3, v4, v5

    new-array p1, p1, [Ljava/lang/Class;

    sget-object v3, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    aput-object v3, p1, v0

    const-class v0, Landroid/app/Instrumentation;

    aput-object v0, p1, v5

    const-string v0, "makeApplication"

    .line 99
    invoke-static {v7, v6, v0, v4, p1}, Lcom/mcal/apkprotector/utils/Reflect;->invokeMethod(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Application;

    .line 104
    invoke-static {v2, v1, v8, p1}, Lcom/mcal/apkprotector/utils/Reflect;->setFieldValue(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    return-object p1
.end method

.method public static getContext()Landroid/content/Context;
    .registers 1

    .line 63
    sget-object v0, Lcom/mcal/apkprotector/ProtectApplication;->context:Landroid/content/Context;

    if-nez v0, :cond_b

    .line 64
    new-instance v0, Lcom/mcal/apkprotector/ProtectApplication;

    invoke-direct {v0}, Lcom/mcal/apkprotector/ProtectApplication;-><init>()V

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->context:Landroid/content/Context;

    .line 66
    :cond_b
    sget-object v0, Lcom/mcal/apkprotector/ProtectApplication;->context:Landroid/content/Context;

    return-object v0
.end method

.method public static getDexDir()Ljava/lang/String;
    .registers 2
    .annotation build Lorg/jetbrains/annotations/NotNull;
    .end annotation

    const-string v0, "$DEX_DIR"

    const/4 v1, 0x2

    .line 35
    invoke-static {v0, v1}, Lcom/mcal/apkprotector/utils/CommonUtils;->encryptStrings(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static getDexPrefix()Ljava/lang/String;
    .registers 2
    .annotation build Lorg/jetbrains/annotations/NotNull;
    .end annotation

    const-string v0, "$DEX_PREFIX"

    const/4 v1, 0x2

    .line 39
    invoke-static {v0, v1}, Lcom/mcal/apkprotector/utils/CommonUtils;->encryptStrings(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static getDexSufix()Ljava/lang/String;
    .registers 2
    .annotation build Lorg/jetbrains/annotations/NotNull;
    .end annotation

    const-string v0, "$DEX_SUFIX"

    const/4 v1, 0x2

    .line 43
    invoke-static {v0, v1}, Lcom/mcal/apkprotector/utils/CommonUtils;->encryptStrings(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static protectKey()Ljava/lang/String;
    .registers 2
    .annotation build Lorg/jetbrains/annotations/NotNull;
    .end annotation

    const-string v0, "$PROTECT_KEY"

    const/4 v1, 0x2

    .line 31
    invoke-static {v0, v1}, Lcom/mcal/apkprotector/utils/CommonUtils;->encryptStrings(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static realApplication()Ljava/lang/String;
    .registers 2
    .annotation build Lorg/jetbrains/annotations/NotNull;
    .end annotation

    const-string v0, "$REAL_APPLICATION"

    const/4 v1, 0x2

    .line 27
    invoke-static {v0, v1}, Lcom/mcal/apkprotector/utils/CommonUtils;->encryptStrings(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public attachBaseContext(Landroid/content/Context;)V
    .registers 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "base"
        }
    .end annotation

    .line 48
    invoke-super {p0, p1}, Landroid/app/Application;->attachBaseContext(Landroid/content/Context;)V

    .line 49
    invoke-static {p0}, Lcom/mcal/apkprotector/multidex/MultiDex;->install(Landroid/content/Context;)V

    return-void
.end method

.method public onCreate()V
    .registers 2

    .line 54
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 55
    invoke-virtual {p0}, Landroid/app/Application;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->context:Landroid/content/Context;

    .line 56
    invoke-static {}, Lcom/mcal/apkprotector/ProtectApplication;->realApplication()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/mcal/apkprotector/ProtectApplication;->changeTopApplication(Ljava/lang/String;)Landroid/app/Application;

    move-result-object v0

    if-eqz v0, :cond_16

    .line 58
    invoke-virtual {v0}, Landroid/app/Application;->onCreate()V

    :cond_16
    return-void
.end method
