.class public Lcom/mcal/apkprotector/ProtectApplication;
.super Landroid/app/Application;


# static fields
.field public static final ﾠ⁪͏:Ljava/lang/String;

.field public static final ﾠ⁪⁪:Ljava/lang/String;

.field public static final ﾠ⁪⁫:Ljava/lang/String;

.field public static final ﾠ⁫:Ljava/lang/String;

.field public static final ﾠ⁫⁪:Ljava/lang/String;

.field public static final ﾠ⁬⁫:Ljava/lang/String; = "android.app.Application"


# direct methods
.method public static constructor <clinit>()V
    .registers 2

    const-string v0, "$DEX_SUFIX"

    const/4 v1, 0x2

    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:Ljava/lang/String;

    const-string v0, "$DEX_PREFIX"

    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:Ljava/lang/String;

    const-string v0, "$DEX_DIR"

    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:Ljava/lang/String;

    const-string v0, "$PROTECT_KEY"

    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/lang/String;

    const-string v0, "$DATA"

    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

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

    invoke-static {v2, v3, v4, v1, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    const-string v4, "mBoundApplication"

    invoke-static {v2, v1, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    const-string v5, "android.app.ActivityThread$AppBindData"

    const-string v6, "info"

    invoke-static {v5, v4, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    const-string v7, "android.app.LoadedApk"

    const-string v8, "mApplication"

    invoke-static {v7, v6, v8, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    const-string v8, "mInitialApplication"

    invoke-static {v2, v1, v8}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    const-string v10, "mAllApplications"

    invoke-static {v2, v1, v10}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Ljava/util/ArrayList;

    invoke-virtual {v10, v9}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const-string v9, "mApplicationInfo"

    invoke-static {v7, v6, v9}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Landroid/content/pm/ApplicationInfo;

    const-string v10, "appInfo"

    invoke-static {v5, v4, v10}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/content/pm/ApplicationInfo;

    iput-object p1, v9, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    iput-object p1, v4, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    const/4 p1, 0x2

    new-array v4, p1, [Ljava/lang/Object;

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

    invoke-static {v7, v6, v0, v4, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Application;

    invoke-static {v2, v1, v8, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    return-object p1
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

    invoke-super {p0, p1}, Landroid/app/Application;->attachBaseContext(Landroid/content/Context;)V

    invoke-static {p0}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏;->ﾠ͏(Landroid/content/Context;)V

    return-void
.end method

.method public onCreate()V
    .registers 3

    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    new-instance v0, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;

    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:Ljava/lang/String;

    invoke-direct {v0, p0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    const-string v0, "android.app.Application"

    invoke-direct {p0, v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏(Ljava/lang/String;)Landroid/app/Application;

    move-result-object v0

    if-eqz v0, :cond_15

    invoke-virtual {v0}, Landroid/app/Application;->onCreate()V

    :cond_15
    return-void
.end method
