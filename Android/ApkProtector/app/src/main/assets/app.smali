.class public Lcom/mcal/apkprotector/ProxyApplication;
.super Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪;
.source ""


# direct methods
.method public constructor <init>()V
    .registers 1

    .line 1
    invoke-direct {p0}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪;-><init>()V

    return-void
.end method


# virtual methods
.method public attachBaseContext(Landroid/content/Context;)V
    .registers 3
    .param p1  # Landroid/content/Context;
        .annotation build Lﾠ⁫⁪/ﾠ⁪⁪/ﾠ⁪͏/ﾠ⁬⁪;
        .end annotation
    .end param

    .line 1
    invoke-super {p0, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪;->attachBaseContext(Landroid/content/Context;)V

    .line 2
    :try_start_3
    new-instance v0, Lﾠ⁪⁪/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;

    invoke-direct {v0, p1}, Lﾠ⁪⁪/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, p1}, Lﾠ⁪⁪/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Landroid/content/Context;)V
    :try_end_b
    .catchall {:try_start_3 .. :try_end_b} :catchall_c

    return-void

    :catchall_c
    move-exception p1

    .line 3
    new-instance v0, Ljava/lang/RuntimeException;

    invoke-direct {v0, p1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v0
.end method

.method public onCreate()V
    .registers 2

    .line 1
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    .line 2
    invoke-static {p0}, Lﾠ⁪⁪/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁬⁫;->ﾠ⁬⁫(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1a

    .line 3
    invoke-static {p0}, Lﾠ⁪⁪/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁬⁫;->ﾠ⁬⁫(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lﾠ⁪⁪/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁫⁪;->ﾠ⁫(Ljava/lang/String;)Landroid/app/Application;

    move-result-object v0

    if-eqz v0, :cond_1a

    .line 4
    invoke-virtual {v0}, Landroid/app/Application;->onCreate()V

    :cond_1a
    return-void
.end method
