.class public Lcom/mcal/apkprotector/ProtectApplication;
.super Landroid/app/Application;
.source ""


# static fields
.field public static ﾠ⁪͏:Landroid/content/Context;
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "StaticFieldLeak"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .registers 1

    .line 1
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    return-void
.end method

.method private ﾠ⁪͏(Ljava/lang/String;)Landroid/app/Application;
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

    .line 1
    invoke-static {v2, v3, v4, v1, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    const-string v4, "mBoundApplication"

    .line 2
    invoke-static {v2, v1, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    const-string v5, "android.app.ActivityThread$AppBindData"

    const-string v6, "info"

    .line 3
    invoke-static {v5, v4, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    const-string v7, "android.app.LoadedApk"

    const-string v8, "mApplication"

    .line 4
    invoke-static {v7, v6, v8, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    const-string v8, "mInitialApplication"

    .line 5
    invoke-static {v2, v1, v8}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    const-string v10, "mAllApplications"

    .line 6
    invoke-static {v2, v1, v10}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/util/ArrayList;

    .line 7
    invoke-virtual {v10, v9}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const-string v9, "mApplicationInfo"

    .line 8
    invoke-static {v7, v6, v9}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Landroid/content/pm/ApplicationInfo;

    const-string v10, "appInfo"

    .line 9
    invoke-static {v5, v4, v10}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/content/pm/ApplicationInfo;

    .line 10
    iput-object p1, v9, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    .line 11
    iput-object p1, v4, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    const/4 p1, 0x2

    new-array v4, p1, [Ljava/lang/Object;

    .line 12
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

    .line 13
    invoke-static {v7, v6, v0, v4, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Application;

    .line 14
    invoke-static {v2, v1, v8, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    return-object p1
.end method

.method public static ﾠ⁪⁪()Landroid/content/Context;
    .registers 1

    .line 1
    sget-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:Landroid/content/Context;

    if-nez v0, :cond_b

    .line 2
    new-instance v0, Lcom/mcal/apkprotector/ProtectApplication;

    invoke-direct {v0}, Lcom/mcal/apkprotector/ProtectApplication;-><init>()V

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:Landroid/content/Context;

    .line 3
    :cond_b
    sget-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:Landroid/content/Context;

    return-object v0
.end method

.method public static ﾠ⁪⁫()Ljava/lang/String;
    .registers 1
    .annotation build Lﾠ⁪⁪/ﾠ⁪⁪/ﾠ⁪͏/ﾠ⁬⁪;
    .end annotation

    const-string v0, "$DEX_PREFIX"

    return-object v0
.end method

.method public static ﾠ⁫()Ljava/lang/String;
    .registers 1
    .annotation build Lﾠ⁪⁪/ﾠ⁪⁪/ﾠ⁪͏/ﾠ⁬⁪;
    .end annotation

    const-string v0, "$DEX_SUFIX"

    return-object v0
.end method

.method public static ﾠ⁫⁪()Ljava/lang/String;
    .registers 1
    .annotation build Lﾠ⁪⁪/ﾠ⁪⁪/ﾠ⁪͏/ﾠ⁬⁪;
    .end annotation

    const-string v0, "$DEX_DIR"

    return-object v0
.end method

.method public static ﾠ⁬⁫()Ljava/lang/String;
    .registers 1
    .annotation build Lﾠ⁪⁪/ﾠ⁪⁪/ﾠ⁪͏/ﾠ⁬⁪;
    .end annotation

    const-string v0, "$PROTECT_KEY"

    return-object v0
.end method

.method public static ﾠ⁭⁫()Ljava/lang/String;
    .registers 1
    .annotation build Lﾠ⁪⁪/ﾠ⁪⁪/ﾠ⁪͏/ﾠ⁬⁪;
    .end annotation

    const-string v0, "$REAL_APPLICATION"

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

    .line 1
    invoke-super {p0, p1}, Landroid/app/Application;->attachBaseContext(Landroid/content/Context;)V

    .line 2
    invoke-static {p0}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;->ﾠ͏(Landroid/content/Context;)V

    return-void
.end method

.method public onCreate()V
    .registers 2

    .line 1
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 2
    invoke-virtual {p0}, Landroid/app/Application;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:Landroid/content/Context;

    .line 3
    invoke-static {}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁭⁫()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏(Ljava/lang/String;)Landroid/app/Application;

    move-result-object v0

    if-eqz v0, :cond_16

    .line 4
    invoke-virtual {v0}, Landroid/app/Application;->onCreate()V

    :cond_16
    return-void
.end method
