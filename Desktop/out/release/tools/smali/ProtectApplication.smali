.class public Lcom/mcal/apkprotector/ProtectApplication;
.super Landroid/app/Application;
.source ""


# static fields
.field public static ﾠ⁪͏:I = 0x0

.field public static ﾠ⁪⁪:I = 0x0

.field public static ﾠ⁪⁫:Ljava/util/List; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field public static ﾠ⁫:[I = null

.field public static ﾠ⁫⁪:I = 0x0

.field public static final ﾠ⁫⁫:Ljava/lang/String; = "android.app.Application"

.field public static final ﾠ⁬:Ljava/lang/String;

.field public static final ﾠ⁬⁪:Ljava/lang/String;

.field public static final ﾠ⁬⁫:Ljava/lang/String;

.field public static final ﾠ⁭⁫:Ljava/lang/String;


# direct methods
.method public static constructor <clinit>()V
    .registers 2

    const-string v0, "$DEX_SUFIX"

    const/4 v1, 0x2

    .line 1
    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁬⁫:Ljava/lang/String;

    const-string v0, "$DEX_PREFIX"

    .line 2
    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁭⁫:Ljava/lang/String;

    const-string v0, "$DEX_DIR"

    .line 3
    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁬⁪:Ljava/lang/String;

    const-string v0, "$PROTECT_KEY"

    .line 4
    invoke-static {v0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁪͏;->ﾠ⁪⁪(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁬:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .line 1
    invoke-direct {p0}, Landroid/app/Application;-><init>()V

    return-void
.end method

.method private ﾠ⁪͏(Ljava/lang/String;)Landroid/app/Application;
    .registers 10
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "appClassName"
        }
    .end annotation

    const/16 v0, 0x194

    .line 1
    invoke-static {v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v0

    const/16 v1, 0x19a

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {v0, v4, v1, v3, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    const/16 v1, 0x1b5

    .line 2
    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/16 v3, 0x1bb

    .line 3
    invoke-static {v3}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v3

    .line 4
    invoke-static {v1, v0, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    const/16 v3, 0x1ca

    .line 5
    invoke-static {v3}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x1d0

    .line 6
    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    .line 7
    invoke-static {v3, v1, v5}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    const/16 v5, 0x1d9

    .line 8
    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x1df

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v3, v6, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    const/16 v5, 0x1f1

    .line 9
    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x1f7

    .line 10
    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    .line 11
    invoke-static {v5, v0, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    const/16 v6, 0x211

    .line 12
    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    const/16 v7, 0x217

    .line 13
    invoke-static {v7}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v7

    .line 14
    invoke-static {v6, v0, v7}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/ArrayList;

    .line 15
    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const/16 v5, 0x232

    .line 16
    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x238

    .line 17
    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    .line 18
    invoke-static {v5, v3, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroid/content/pm/ApplicationInfo;

    const/16 v6, 0x24b

    .line 19
    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    const/16 v7, 0x251

    .line 20
    invoke-static {v7}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v7

    .line 21
    invoke-static {v6, v1, v7}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/content/pm/ApplicationInfo;

    .line 22
    iput-object p1, v5, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    .line 23
    iput-object p1, v1, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    const/16 p1, 0x276

    .line 24
    invoke-static {p1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object p1

    const/16 v1, 0x27c

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/4 v5, 0x2

    new-array v6, v5, [Ljava/lang/Object;

    .line 25
    sget-object v7, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    aput-object v7, v6, v2

    const/4 v7, 0x1

    aput-object v4, v6, v7

    new-array v4, v5, [Ljava/lang/Class;

    sget-object v5, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    aput-object v5, v4, v2

    const-class v2, Landroid/app/Instrumentation;

    aput-object v2, v4, v7

    .line 26
    invoke-static {p1, v3, v1, v6, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Application;

    const/16 v1, 0x29b

    .line 27
    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/16 v2, 0x2a1

    invoke-static {v2}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v0, v2, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁪/ﾠ⁫⁪;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    return-object p1
.end method

.method public static ﾠ⁪⁪(I)Ljava/lang/String;
    .registers 22
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "lll11"
        }
    .end annotation

    move/from16 v0, p0

    const/4 v1, -0x1

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-ne v0, v1, :cond_4ff

    const/4 v1, -0x2

    const v4, 0x9bc0ec1

    if-ne v0, v1, :cond_1b1

    .line 1
    :try_start_d
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_df

    .line 2
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_13
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v5, v5
    :try_end_18
    .catch Ljava/lang/Exception; {:try_start_d .. :try_end_18} :catch_feb

    if-ge v1, v5, :cond_df

    .line 3
    :try_start_1a
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v6, v6, v7

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_46
    .catch Ljava/lang/Exception; {:try_start_1a .. :try_end_46} :catch_46

    .line 4
    :catch_46
    :try_start_46
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_48
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-ge v1, v5, :cond_d8

    .line 5
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v8

    sget v9, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v8, v9

    invoke-interface {v7, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v7, v7, v4

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 6
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_80
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I
    :try_end_86
    .catch Ljava/lang/Exception; {:try_start_46 .. :try_end_86} :catch_feb

    add-int/2addr v5, v6

    if-ge v1, v5, :cond_d2

    if-nez v1, :cond_aa

    .line 7
    :try_start_8b
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_c8

    .line 8
    :cond_aa
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_c8
    .catch Ljava/lang/Exception; {:try_start_8b .. :try_end_c8} :catch_c8

    .line 9
    :catch_c8
    :goto_c8
    :try_start_c8
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v1, v1, 0x1

    .line 10
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_80

    :cond_d2
    add-int/lit8 v6, v6, 0x1

    .line 11
    sput v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_48

    .line 12
    :cond_d8
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_13

    .line 13
    :cond_df
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_4ff

    .line 14
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_e5
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v5, v5
    :try_end_ea
    .catch Ljava/lang/Exception; {:try_start_c8 .. :try_end_ea} :catch_feb

    if-ge v1, v5, :cond_4ff

    .line 15
    :try_start_ec
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v6, v6, v7

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_118
    .catch Ljava/lang/Exception; {:try_start_ec .. :try_end_118} :catch_118

    .line 16
    :catch_118
    :try_start_118
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_11a
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-ge v1, v5, :cond_1aa

    .line 17
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v8

    sget v9, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v8, v9

    invoke-interface {v7, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v7, v7, v4

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 18
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_152
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I
    :try_end_158
    .catch Ljava/lang/Exception; {:try_start_118 .. :try_end_158} :catch_feb

    add-int/2addr v5, v6

    if-ge v1, v5, :cond_1a4

    if-nez v1, :cond_17c

    .line 19
    :try_start_15d
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_19a

    .line 20
    :cond_17c
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_19a
    .catch Ljava/lang/Exception; {:try_start_15d .. :try_end_19a} :catch_19a

    .line 21
    :catch_19a
    :goto_19a
    :try_start_19a
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v1, v1, 0x1

    .line 22
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_152

    :cond_1a4
    add-int/lit8 v6, v6, 0x1

    .line 23
    sput v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_11a

    .line 24
    :cond_1aa
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_e5

    :cond_1b1
    const/4 v1, -0x3

    if-ne v0, v1, :cond_358

    .line 25
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_286

    .line 26
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_1ba
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v5, v5
    :try_end_1bf
    .catch Ljava/lang/Exception; {:try_start_19a .. :try_end_1bf} :catch_feb

    if-ge v1, v5, :cond_286

    .line 27
    :try_start_1c1
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v6, v6, v7

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_1ed
    .catch Ljava/lang/Exception; {:try_start_1c1 .. :try_end_1ed} :catch_1ed

    .line 28
    :catch_1ed
    :try_start_1ed
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_1ef
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-ge v1, v5, :cond_27f

    .line 29
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v8

    sget v9, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v8, v9

    invoke-interface {v7, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v7, v7, v4

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 30
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_227
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I
    :try_end_22d
    .catch Ljava/lang/Exception; {:try_start_1ed .. :try_end_22d} :catch_feb

    add-int/2addr v5, v6

    if-ge v1, v5, :cond_279

    if-nez v1, :cond_251

    .line 31
    :try_start_232
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_26f

    .line 32
    :cond_251
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_26f
    .catch Ljava/lang/Exception; {:try_start_232 .. :try_end_26f} :catch_26f

    .line 33
    :catch_26f
    :goto_26f
    :try_start_26f
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v1, v1, 0x1

    .line 34
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_227

    :cond_279
    add-int/lit8 v6, v6, 0x1

    .line 35
    sput v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_1ef

    .line 36
    :cond_27f
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_1ba

    .line 37
    :cond_286
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_4ff

    .line 38
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_28c
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v5, v5
    :try_end_291
    .catch Ljava/lang/Exception; {:try_start_26f .. :try_end_291} :catch_feb

    if-ge v1, v5, :cond_4ff

    .line 39
    :try_start_293
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v6, v6, v7

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_2bf
    .catch Ljava/lang/Exception; {:try_start_293 .. :try_end_2bf} :catch_2bf

    .line 40
    :catch_2bf
    :try_start_2bf
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_2c1
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-ge v1, v5, :cond_351

    .line 41
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v8

    sget v9, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v8, v9

    invoke-interface {v7, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v7, v7, v4

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 42
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_2f9
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I
    :try_end_2ff
    .catch Ljava/lang/Exception; {:try_start_2bf .. :try_end_2ff} :catch_feb

    add-int/2addr v5, v6

    if-ge v1, v5, :cond_34b

    if-nez v1, :cond_323

    .line 43
    :try_start_304
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_341

    .line 44
    :cond_323
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_341
    .catch Ljava/lang/Exception; {:try_start_304 .. :try_end_341} :catch_341

    .line 45
    :catch_341
    :goto_341
    :try_start_341
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v1, v1, 0x1

    .line 46
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_2f9

    :cond_34b
    add-int/lit8 v6, v6, 0x1

    .line 47
    sput v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_2c1

    .line 48
    :cond_351
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_28c

    :cond_358
    const/4 v1, -0x4

    if-ne v0, v1, :cond_4ff

    .line 49
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_42d

    .line 50
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_361
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v5, v5
    :try_end_366
    .catch Ljava/lang/Exception; {:try_start_341 .. :try_end_366} :catch_feb

    if-ge v1, v5, :cond_42d

    .line 51
    :try_start_368
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v6, v6, v7

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_394
    .catch Ljava/lang/Exception; {:try_start_368 .. :try_end_394} :catch_394

    .line 52
    :catch_394
    :try_start_394
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_396
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-ge v1, v5, :cond_426

    .line 53
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v8

    sget v9, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v8, v9

    invoke-interface {v7, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v7, v7, v4

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 54
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_3ce
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I
    :try_end_3d4
    .catch Ljava/lang/Exception; {:try_start_394 .. :try_end_3d4} :catch_feb

    add-int/2addr v5, v6

    if-ge v1, v5, :cond_420

    if-nez v1, :cond_3f8

    .line 55
    :try_start_3d9
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_416

    .line 56
    :cond_3f8
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_416
    .catch Ljava/lang/Exception; {:try_start_3d9 .. :try_end_416} :catch_416

    .line 57
    :catch_416
    :goto_416
    :try_start_416
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v1, v1, 0x1

    .line 58
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_3ce

    :cond_420
    add-int/lit8 v6, v6, 0x1

    .line 59
    sput v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_396

    .line 60
    :cond_426
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_361

    .line 61
    :cond_42d
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_4ff

    .line 62
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_433
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v5, v5
    :try_end_438
    .catch Ljava/lang/Exception; {:try_start_416 .. :try_end_438} :catch_feb

    if-ge v1, v5, :cond_4ff

    .line 63
    :try_start_43a
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v6, v6, v7

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_466
    .catch Ljava/lang/Exception; {:try_start_43a .. :try_end_466} :catch_466

    .line 64
    :catch_466
    :try_start_466
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_468
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v5

    if-ge v1, v5, :cond_4f8

    .line 65
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7}, Ljava/util/List;->size()I

    move-result v8

    sget v9, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v8, v9

    invoke-interface {v7, v8}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v7, v7, v4

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v5, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 66
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_4a0
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I
    :try_end_4a6
    .catch Ljava/lang/Exception; {:try_start_466 .. :try_end_4a6} :catch_feb

    add-int/2addr v5, v6

    if-ge v1, v5, :cond_4f2

    if-nez v1, :cond_4ca

    .line 67
    :try_start_4ab
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_4e8

    .line 68
    :cond_4ca
    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-interface {v5, v1, v6}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_4e8
    .catch Ljava/lang/Exception; {:try_start_4ab .. :try_end_4e8} :catch_4e8

    .line 69
    :catch_4e8
    :goto_4e8
    :try_start_4e8
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v1, v1, 0x1

    .line 70
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_4a0

    :cond_4f2
    add-int/lit8 v6, v6, 0x1

    .line 71
    sput v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_468

    .line 72
    :cond_4f8
    sget v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/2addr v1, v2

    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_433

    :cond_4ff
    const/16 v1, 0x84

    const/16 v4, 0x9

    const/4 v5, 0x7

    const/4 v6, 0x5

    const/16 v7, 0x8

    const/16 v8, 0xa

    const/4 v9, 0x3

    const/4 v10, 0x2

    const/4 v11, 0x4

    const/4 v12, 0x6

    if-ne v0, v1, :cond_54c

    new-array v1, v8, [B

    const/16 v13, -0x60

    aput-byte v13, v1, v3

    const/16 v13, -0x40

    aput-byte v13, v1, v2

    const/16 v2, -0x3f

    aput-byte v2, v1, v10

    const/16 v2, -0x24

    aput-byte v2, v1, v9

    const/16 v2, -0x25

    aput-byte v2, v1, v11

    const/16 v2, -0x29

    aput-byte v2, v1, v6

    const/16 v2, -0x2f

    aput-byte v2, v1, v12

    const/16 v2, -0x3e

    aput-byte v2, v1, v5

    const/16 v2, -0x33

    aput-byte v2, v1, v7

    const/16 v2, -0x24

    aput-byte v2, v1, v4

    :goto_539
    if-ge v3, v8, :cond_544

    .line 73
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_539

    .line 74
    :cond_544
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_54c
    const/16 v1, 0x9b

    const/16 v13, 0xb

    if-ne v0, v1, :cond_593

    new-array v1, v13, [B

    const/16 v14, -0x41

    aput-byte v14, v1, v3

    const/16 v14, -0x21

    aput-byte v14, v1, v2

    const/16 v2, -0x22

    aput-byte v2, v1, v10

    const/16 v2, -0x3d

    aput-byte v2, v1, v9

    const/16 v2, -0x3c

    aput-byte v2, v1, v11

    const/16 v2, -0x35

    aput-byte v2, v1, v6

    const/16 v2, -0x37

    aput-byte v2, v1, v12

    const/16 v2, -0x22

    aput-byte v2, v1, v5

    const/16 v2, -0x23

    aput-byte v2, v1, v7

    const/16 v2, -0x2e

    aput-byte v2, v1, v4

    const/16 v2, -0x3d

    aput-byte v2, v1, v8

    :goto_580
    if-ge v3, v13, :cond_58b

    .line 75
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_580

    .line 76
    :cond_58b
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_593
    const/16 v1, 0xb2

    if-ne v0, v1, :cond_5cb

    new-array v1, v7, [B

    const/16 v4, -0x6a

    aput-byte v4, v1, v3

    const/16 v4, -0xa

    aput-byte v4, v1, v2

    const/16 v2, -0x9

    aput-byte v2, v1, v10

    const/16 v2, -0x16

    aput-byte v2, v1, v9

    const/16 v2, -0x13

    aput-byte v2, v1, v11

    const/16 v2, -0xa

    aput-byte v2, v1, v6

    const/4 v2, -0x5

    aput-byte v2, v1, v12

    const/16 v2, -0x20

    aput-byte v2, v1, v5

    :goto_5b8
    if-ge v3, v7, :cond_5c3

    .line 77
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_5b8

    .line 78
    :cond_5c3
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_5cb
    const/16 v1, 0xc9

    const/16 v14, 0xc

    if-ne v0, v1, :cond_616

    new-array v1, v14, [B

    const/16 v15, -0x13

    aput-byte v15, v1, v3

    const/16 v15, -0x67

    aput-byte v15, v1, v2

    const/16 v2, -0x65

    aput-byte v2, v1, v10

    const/16 v2, -0x7a

    aput-byte v2, v1, v9

    const/16 v2, -0x63

    aput-byte v2, v1, v11

    const/16 v2, -0x74

    aput-byte v2, v1, v6

    const/16 v2, -0x76

    aput-byte v2, v1, v12

    const/16 v2, -0x63

    aput-byte v2, v1, v5

    const/16 v2, -0x6a

    aput-byte v2, v1, v7

    const/16 v2, -0x7e

    aput-byte v2, v1, v4

    const/16 v2, -0x74

    aput-byte v2, v1, v8

    const/16 v2, -0x70

    aput-byte v2, v1, v13

    :goto_603
    if-ge v3, v14, :cond_60e

    .line 79
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_603

    .line 80
    :cond_60e
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_616
    const/16 v1, 0xdc

    const/16 v15, 0x11

    const/16 v16, 0xe

    const/16 v17, 0xd

    const/16 v14, 0xf

    if-ne v0, v1, :cond_67f

    new-array v1, v15, [B

    const/16 v18, -0x8

    aput-byte v18, v1, v3

    const/16 v18, -0x72

    aput-byte v18, v1, v2

    const/16 v2, -0x67

    aput-byte v2, v1, v10

    const/16 v2, -0x63

    aput-byte v2, v1, v9

    const/16 v2, -0x70

    aput-byte v2, v1, v11

    const/16 v2, -0x7d

    aput-byte v2, v1, v6

    const/16 v2, -0x63

    aput-byte v2, v1, v12

    const/16 v2, -0x74

    aput-byte v2, v1, v5

    const/16 v2, -0x74

    aput-byte v2, v1, v7

    const/16 v2, -0x70

    aput-byte v2, v1, v4

    const/16 v2, -0x6b

    aput-byte v2, v1, v8

    const/16 v2, -0x61

    aput-byte v2, v1, v13

    const/16 v2, -0x63

    const/16 v4, 0xc

    aput-byte v2, v1, v4

    const/16 v2, -0x78

    aput-byte v2, v1, v17

    const/16 v2, -0x6b

    aput-byte v2, v1, v16

    const/16 v2, -0x6d

    aput-byte v2, v1, v14

    const/16 v2, -0x6e

    const/16 v4, 0x10

    aput-byte v2, v1, v4

    :goto_66c
    if-ge v3, v15, :cond_677

    .line 81
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_66c

    .line 82
    :cond_677
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_67f
    const/16 v1, 0x194

    const/16 v19, 0x12

    if-ne v0, v1, :cond_713

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0xb

    aput-byte v20, v15, v3

    const/16 v20, -0x6

    aput-byte v20, v15, v2

    const/16 v2, -0x10

    aput-byte v2, v15, v10

    const/16 v2, -0x1a

    aput-byte v2, v15, v9

    const/4 v2, -0x5

    aput-byte v2, v15, v11

    const/4 v2, -0x3

    aput-byte v2, v15, v6

    const/16 v2, -0x10

    aput-byte v2, v15, v12

    const/16 v2, -0x46

    aput-byte v2, v15, v5

    const/16 v2, -0xb

    aput-byte v2, v15, v7

    const/16 v2, -0x1c

    aput-byte v2, v15, v4

    const/16 v2, -0x1c

    aput-byte v2, v15, v8

    const/16 v2, -0x46

    aput-byte v2, v15, v13

    const/16 v2, -0x2b

    const/16 v4, 0xc

    aput-byte v2, v15, v4

    const/16 v2, -0x9

    aput-byte v2, v15, v17

    const/16 v2, -0x20

    aput-byte v2, v15, v16

    const/4 v2, -0x3

    aput-byte v2, v15, v14

    const/16 v2, -0x1e

    const/16 v4, 0x10

    aput-byte v2, v15, v4

    const/4 v2, -0x3

    const/16 v4, 0x11

    aput-byte v2, v15, v4

    const/16 v2, -0x20

    aput-byte v2, v15, v19

    const/16 v2, -0x13

    const/16 v4, 0x13

    aput-byte v2, v15, v4

    const/16 v2, 0x14

    const/16 v4, -0x40

    aput-byte v4, v15, v2

    const/4 v2, -0x4

    const/16 v4, 0x15

    aput-byte v2, v15, v4

    const/16 v2, 0x16

    const/16 v4, -0x1a

    aput-byte v4, v15, v2

    const/16 v2, 0x17

    const/16 v4, -0xf

    aput-byte v4, v15, v2

    const/16 v2, 0x18

    const/16 v4, -0xb

    aput-byte v4, v15, v2

    const/16 v2, 0x19

    const/16 v4, -0x10

    aput-byte v4, v15, v2

    :goto_700
    if-ge v3, v1, :cond_70b

    .line 83
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_700

    .line 84
    :cond_70b
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_713
    const/16 v1, 0x19a

    if-ne v0, v1, :cond_788

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/4 v1, -0x7

    aput-byte v1, v15, v3

    const/16 v1, -0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x18

    aput-byte v1, v15, v10

    const/16 v1, -0x18

    aput-byte v1, v15, v9

    const/4 v1, -0x1

    aput-byte v1, v15, v11

    const/16 v1, -0xc

    aput-byte v1, v15, v6

    const/16 v1, -0x12

    aput-byte v1, v15, v12

    const/16 v1, -0x25

    aput-byte v1, v15, v5

    const/4 v1, -0x7

    aput-byte v1, v15, v7

    const/16 v1, -0x12

    aput-byte v1, v15, v4

    const/16 v1, -0xd

    aput-byte v1, v15, v8

    const/16 v1, -0x14

    aput-byte v1, v15, v13

    const/16 v1, -0xd

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x12

    aput-byte v1, v15, v17

    const/16 v1, -0x1d

    aput-byte v1, v15, v16

    const/16 v1, -0x32

    aput-byte v1, v15, v14

    const/16 v1, -0xe

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x18

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/4 v1, -0x1

    aput-byte v1, v15, v19

    const/4 v1, -0x5

    const/16 v2, 0x13

    aput-byte v1, v15, v2

    const/16 v1, 0x14

    const/4 v2, -0x2

    aput-byte v2, v15, v1

    :goto_773
    const/16 v1, 0x15

    if-ge v3, v1, :cond_780

    .line 85
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_773

    .line 86
    :cond_780
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_788
    const/16 v1, 0x1b5

    if-ne v0, v1, :cond_81f

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x2c

    aput-byte v20, v15, v3

    const/16 v20, -0x25

    aput-byte v20, v15, v2

    const/16 v2, -0x2f

    aput-byte v2, v15, v10

    const/16 v2, -0x39

    aput-byte v2, v15, v9

    const/16 v2, -0x26

    aput-byte v2, v15, v11

    const/16 v2, -0x24

    aput-byte v2, v15, v6

    const/16 v2, -0x2f

    aput-byte v2, v15, v12

    const/16 v2, -0x65

    aput-byte v2, v15, v5

    const/16 v2, -0x2c

    aput-byte v2, v15, v7

    const/16 v2, -0x3b

    aput-byte v2, v15, v4

    const/16 v2, -0x3b

    aput-byte v2, v15, v8

    const/16 v2, -0x65

    aput-byte v2, v15, v13

    const/16 v2, -0xc

    const/16 v4, 0xc

    aput-byte v2, v15, v4

    const/16 v2, -0x2a

    aput-byte v2, v15, v17

    const/16 v2, -0x3f

    aput-byte v2, v15, v16

    const/16 v2, -0x24

    aput-byte v2, v15, v14

    const/16 v2, -0x3d

    const/16 v4, 0x10

    aput-byte v2, v15, v4

    const/16 v2, -0x24

    const/16 v4, 0x11

    aput-byte v2, v15, v4

    const/16 v2, -0x3f

    aput-byte v2, v15, v19

    const/16 v2, -0x34

    const/16 v4, 0x13

    aput-byte v2, v15, v4

    const/16 v2, 0x14

    const/16 v4, -0x1f

    aput-byte v4, v15, v2

    const/16 v2, -0x23

    const/16 v4, 0x15

    aput-byte v2, v15, v4

    const/16 v2, 0x16

    const/16 v4, -0x39

    aput-byte v4, v15, v2

    const/16 v2, 0x17

    const/16 v4, -0x30

    aput-byte v4, v15, v2

    const/16 v2, 0x18

    const/16 v4, -0x2c

    aput-byte v4, v15, v2

    const/16 v2, 0x19

    const/16 v4, -0x2f

    aput-byte v4, v15, v2

    :goto_80c
    if-ge v3, v1, :cond_817

    .line 87
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_80c

    .line 88
    :cond_817
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_81f
    const/16 v1, 0x1bb

    if-ne v0, v1, :cond_882

    const/16 v1, 0x11

    new-array v15, v1, [B

    const/16 v1, -0x2a

    aput-byte v1, v15, v3

    const/4 v1, -0x7

    aput-byte v1, v15, v2

    const/16 v1, -0x2c

    aput-byte v1, v15, v10

    const/16 v1, -0x32

    aput-byte v1, v15, v9

    const/16 v1, -0x2b

    aput-byte v1, v15, v11

    const/16 v1, -0x21

    aput-byte v1, v15, v6

    const/4 v1, -0x6

    aput-byte v1, v15, v12

    const/16 v1, -0x35

    aput-byte v1, v15, v5

    const/16 v1, -0x35

    aput-byte v1, v15, v7

    const/16 v1, -0x29

    aput-byte v1, v15, v4

    const/16 v1, -0x2e

    aput-byte v1, v15, v8

    const/16 v1, -0x28

    aput-byte v1, v15, v13

    const/16 v1, -0x26

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x31

    aput-byte v1, v15, v17

    const/16 v1, -0x2e

    aput-byte v1, v15, v16

    const/16 v1, -0x2c

    aput-byte v1, v15, v14

    const/16 v1, -0x2b

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    :goto_86d
    const/16 v1, 0x11

    if-ge v3, v1, :cond_87a

    .line 89
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_86d

    .line 90
    :cond_87a
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_882
    const/16 v1, 0x1ca

    if-ne v0, v1, :cond_961

    const/16 v1, 0x26

    new-array v15, v1, [B

    const/16 v20, -0x55

    aput-byte v20, v15, v3

    const/16 v20, -0x5c

    aput-byte v20, v15, v2

    const/16 v2, -0x52

    aput-byte v2, v15, v10

    const/16 v2, -0x48

    aput-byte v2, v15, v9

    const/16 v2, -0x5b

    aput-byte v2, v15, v11

    const/16 v2, -0x5d

    aput-byte v2, v15, v6

    const/16 v2, -0x52

    aput-byte v2, v15, v12

    const/16 v2, -0x1c

    aput-byte v2, v15, v5

    const/16 v2, -0x55

    aput-byte v2, v15, v7

    const/16 v2, -0x46

    aput-byte v2, v15, v4

    const/16 v2, -0x46

    aput-byte v2, v15, v8

    const/16 v2, -0x1c

    aput-byte v2, v15, v13

    const/16 v2, -0x75

    const/16 v4, 0xc

    aput-byte v2, v15, v4

    const/16 v2, -0x57

    aput-byte v2, v15, v17

    const/16 v2, -0x42

    aput-byte v2, v15, v16

    const/16 v2, -0x5d

    aput-byte v2, v15, v14

    const/16 v2, -0x44

    const/16 v4, 0x10

    aput-byte v2, v15, v4

    const/16 v2, -0x5d

    const/16 v4, 0x11

    aput-byte v2, v15, v4

    const/16 v2, -0x42

    aput-byte v2, v15, v19

    const/16 v2, -0x4d

    const/16 v4, 0x13

    aput-byte v2, v15, v4

    const/16 v2, 0x14

    const/16 v4, -0x62

    aput-byte v4, v15, v2

    const/16 v2, -0x5e

    const/16 v4, 0x15

    aput-byte v2, v15, v4

    const/16 v2, 0x16

    const/16 v4, -0x48

    aput-byte v4, v15, v2

    const/16 v2, 0x17

    const/16 v4, -0x51

    aput-byte v4, v15, v2

    const/16 v2, 0x18

    const/16 v4, -0x55

    aput-byte v4, v15, v2

    const/16 v2, 0x19

    const/16 v4, -0x52

    aput-byte v4, v15, v2

    const/16 v2, 0x1a

    const/16 v4, -0x12

    aput-byte v4, v15, v2

    const/16 v2, 0x1b

    const/16 v4, -0x75

    aput-byte v4, v15, v2

    const/16 v2, 0x1c

    const/16 v4, -0x46

    aput-byte v4, v15, v2

    const/16 v2, 0x1d

    const/16 v4, -0x46

    aput-byte v4, v15, v2

    const/16 v2, 0x1e

    const/16 v4, -0x78

    aput-byte v4, v15, v2

    const/16 v2, 0x1f

    const/16 v4, -0x5d

    aput-byte v4, v15, v2

    const/16 v2, 0x20

    const/16 v4, -0x5c

    aput-byte v4, v15, v2

    const/16 v2, 0x21

    const/16 v4, -0x52

    aput-byte v4, v15, v2

    const/16 v2, 0x22

    const/16 v4, -0x72

    aput-byte v4, v15, v2

    const/16 v2, 0x23

    const/16 v4, -0x55

    aput-byte v4, v15, v2

    const/16 v2, 0x24

    const/16 v4, -0x42

    aput-byte v4, v15, v2

    const/16 v2, 0x25

    const/16 v4, -0x55

    aput-byte v4, v15, v2

    :goto_94e
    if-ge v3, v1, :cond_959

    .line 91
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_94e

    .line 92
    :cond_959
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_961
    const/16 v1, 0x1d0

    if-ne v0, v1, :cond_98a

    new-array v1, v11, [B

    const/16 v4, -0x47

    aput-byte v4, v1, v3

    const/16 v4, -0x42

    aput-byte v4, v1, v2

    const/16 v2, -0x4a

    aput-byte v2, v1, v10

    const/16 v2, -0x41

    aput-byte v2, v1, v9

    :goto_977
    if-ge v3, v11, :cond_982

    .line 93
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_977

    .line 94
    :cond_982
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_98a
    const/16 v1, 0x1d9

    if-ne v0, v1, :cond_a05

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/16 v1, -0x48

    aput-byte v1, v15, v3

    const/16 v1, -0x49

    aput-byte v1, v15, v2

    const/16 v1, -0x43

    aput-byte v1, v15, v10

    const/16 v1, -0x55

    aput-byte v1, v15, v9

    const/16 v1, -0x4a

    aput-byte v1, v15, v11

    const/16 v1, -0x50

    aput-byte v1, v15, v6

    const/16 v1, -0x43

    aput-byte v1, v15, v12

    const/16 v1, -0x9

    aput-byte v1, v15, v5

    const/16 v1, -0x48

    aput-byte v1, v15, v7

    const/16 v1, -0x57

    aput-byte v1, v15, v4

    const/16 v1, -0x57

    aput-byte v1, v15, v8

    const/16 v1, -0x9

    aput-byte v1, v15, v13

    const/16 v1, -0x6b

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x4a

    aput-byte v1, v15, v17

    const/16 v1, -0x48

    aput-byte v1, v15, v16

    const/16 v1, -0x43

    aput-byte v1, v15, v14

    const/16 v1, -0x44

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x43

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x68

    aput-byte v1, v15, v19

    const/16 v1, -0x57

    const/16 v2, 0x13

    aput-byte v1, v15, v2

    const/16 v1, 0x14

    const/16 v2, -0x4e

    aput-byte v2, v15, v1

    :goto_9f0
    const/16 v1, 0x15

    if-ge v3, v1, :cond_9fd

    .line 95
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_9f0

    .line 96
    :cond_9fd
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_a05
    const/16 v1, 0x1df

    if-ne v0, v1, :cond_a52

    const/16 v1, 0xc

    new-array v14, v1, [B

    const/16 v1, -0x4e

    aput-byte v1, v14, v3

    const/16 v1, -0x62

    aput-byte v1, v14, v2

    const/16 v1, -0x51

    aput-byte v1, v14, v10

    const/16 v1, -0x51

    aput-byte v1, v14, v9

    const/16 v1, -0x4d

    aput-byte v1, v14, v11

    const/16 v1, -0x4a

    aput-byte v1, v14, v6

    const/16 v1, -0x44

    aput-byte v1, v14, v12

    const/16 v1, -0x42

    aput-byte v1, v14, v5

    const/16 v1, -0x55

    aput-byte v1, v14, v7

    const/16 v1, -0x4a

    aput-byte v1, v14, v4

    const/16 v1, -0x50

    aput-byte v1, v14, v8

    const/16 v1, -0x4f

    aput-byte v1, v14, v13

    :goto_a3d
    const/16 v1, 0xc

    if-ge v3, v1, :cond_a4a

    .line 97
    aget-byte v1, v14, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v14, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_a3d

    .line 98
    :cond_a4a
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v14, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_a52
    const/16 v1, 0x1f1

    if-ne v0, v1, :cond_ae9

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x70

    aput-byte v20, v15, v3

    const/16 v20, -0x61

    aput-byte v20, v15, v2

    const/16 v2, -0x6b

    aput-byte v2, v15, v10

    const/16 v2, -0x7d

    aput-byte v2, v15, v9

    const/16 v2, -0x62

    aput-byte v2, v15, v11

    const/16 v2, -0x68

    aput-byte v2, v15, v6

    const/16 v2, -0x6b

    aput-byte v2, v15, v12

    const/16 v2, -0x21

    aput-byte v2, v15, v5

    const/16 v2, -0x70

    aput-byte v2, v15, v7

    const/16 v2, -0x7f

    aput-byte v2, v15, v4

    const/16 v2, -0x7f

    aput-byte v2, v15, v8

    const/16 v2, -0x21

    aput-byte v2, v15, v13

    const/16 v2, -0x50

    const/16 v4, 0xc

    aput-byte v2, v15, v4

    const/16 v2, -0x6e

    aput-byte v2, v15, v17

    const/16 v2, -0x7b

    aput-byte v2, v15, v16

    const/16 v2, -0x68

    aput-byte v2, v15, v14

    const/16 v2, -0x79

    const/16 v4, 0x10

    aput-byte v2, v15, v4

    const/16 v2, -0x68

    const/16 v4, 0x11

    aput-byte v2, v15, v4

    const/16 v2, -0x7b

    aput-byte v2, v15, v19

    const/16 v2, -0x78

    const/16 v4, 0x13

    aput-byte v2, v15, v4

    const/16 v2, 0x14

    const/16 v4, -0x5b

    aput-byte v4, v15, v2

    const/16 v2, -0x67

    const/16 v4, 0x15

    aput-byte v2, v15, v4

    const/16 v2, 0x16

    const/16 v4, -0x7d

    aput-byte v4, v15, v2

    const/16 v2, 0x17

    const/16 v4, -0x6c

    aput-byte v4, v15, v2

    const/16 v2, 0x18

    const/16 v4, -0x70

    aput-byte v4, v15, v2

    const/16 v2, 0x19

    const/16 v4, -0x6b

    aput-byte v4, v15, v2

    :goto_ad6
    if-ge v3, v1, :cond_ae1

    .line 99
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_ad6

    .line 100
    :cond_ae1
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_ae9
    const/16 v1, 0x1f7

    if-ne v0, v1, :cond_b58

    const/16 v1, 0x13

    new-array v15, v1, [B

    const/16 v1, -0x66

    aput-byte v1, v15, v3

    const/16 v1, -0x42

    aput-byte v1, v15, v2

    const/16 v1, -0x67

    aput-byte v1, v15, v10

    const/16 v1, -0x62

    aput-byte v1, v15, v9

    const/16 v1, -0x7d

    aput-byte v1, v15, v11

    const/16 v1, -0x62

    aput-byte v1, v15, v6

    const/16 v1, -0x6a

    aput-byte v1, v15, v12

    const/16 v1, -0x65

    aput-byte v1, v15, v5

    const/16 v1, -0x4a

    aput-byte v1, v15, v7

    const/16 v1, -0x79

    aput-byte v1, v15, v4

    const/16 v1, -0x79

    aput-byte v1, v15, v8

    const/16 v1, -0x65

    aput-byte v1, v15, v13

    const/16 v1, -0x62

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x6c

    aput-byte v1, v15, v17

    const/16 v1, -0x6a

    aput-byte v1, v15, v16

    const/16 v1, -0x7d

    aput-byte v1, v15, v14

    const/16 v1, -0x62

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x68

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x67

    aput-byte v1, v15, v19

    :goto_b43
    const/16 v1, 0x13

    if-ge v3, v1, :cond_b50

    .line 101
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_b43

    .line 102
    :cond_b50
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_b58
    const/16 v1, 0x211

    if-ne v0, v1, :cond_bef

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, 0x70

    aput-byte v20, v15, v3

    const/16 v20, 0x7f

    aput-byte v20, v15, v2

    const/16 v2, 0x75

    aput-byte v2, v15, v10

    const/16 v2, 0x63

    aput-byte v2, v15, v9

    const/16 v2, 0x7e

    aput-byte v2, v15, v11

    const/16 v2, 0x78

    aput-byte v2, v15, v6

    const/16 v2, 0x75

    aput-byte v2, v15, v12

    const/16 v2, 0x3f

    aput-byte v2, v15, v5

    const/16 v2, 0x70

    aput-byte v2, v15, v7

    const/16 v2, 0x61

    aput-byte v2, v15, v4

    const/16 v2, 0x61

    aput-byte v2, v15, v8

    const/16 v2, 0x3f

    aput-byte v2, v15, v13

    const/16 v2, 0x50

    const/16 v4, 0xc

    aput-byte v2, v15, v4

    const/16 v2, 0x72

    aput-byte v2, v15, v17

    const/16 v2, 0x65

    aput-byte v2, v15, v16

    const/16 v2, 0x78

    aput-byte v2, v15, v14

    const/16 v2, 0x67

    const/16 v4, 0x10

    aput-byte v2, v15, v4

    const/16 v2, 0x78

    const/16 v4, 0x11

    aput-byte v2, v15, v4

    const/16 v2, 0x65

    aput-byte v2, v15, v19

    const/16 v2, 0x68

    const/16 v4, 0x13

    aput-byte v2, v15, v4

    const/16 v2, 0x14

    const/16 v4, 0x45

    aput-byte v4, v15, v2

    const/16 v2, 0x79

    const/16 v4, 0x15

    aput-byte v2, v15, v4

    const/16 v2, 0x16

    const/16 v4, 0x63

    aput-byte v4, v15, v2

    const/16 v2, 0x17

    const/16 v4, 0x74

    aput-byte v4, v15, v2

    const/16 v2, 0x18

    const/16 v4, 0x70

    aput-byte v4, v15, v2

    const/16 v2, 0x19

    const/16 v4, 0x75

    aput-byte v4, v15, v2

    :goto_bdc
    if-ge v3, v1, :cond_be7

    .line 103
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_bdc

    .line 104
    :cond_be7
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_bef
    const/16 v1, 0x217

    if-ne v0, v1, :cond_c4e

    const/16 v1, 0x10

    new-array v15, v1, [B

    const/16 v1, 0x7a

    aput-byte v1, v15, v3

    const/16 v1, 0x56

    aput-byte v1, v15, v2

    const/16 v1, 0x7b

    aput-byte v1, v15, v10

    const/16 v1, 0x7b

    aput-byte v1, v15, v9

    const/16 v1, 0x56

    aput-byte v1, v15, v11

    const/16 v1, 0x67

    aput-byte v1, v15, v6

    const/16 v1, 0x67

    aput-byte v1, v15, v12

    const/16 v1, 0x7b

    aput-byte v1, v15, v5

    const/16 v1, 0x7e

    aput-byte v1, v15, v7

    const/16 v1, 0x74

    aput-byte v1, v15, v4

    const/16 v1, 0x76

    aput-byte v1, v15, v8

    const/16 v1, 0x63

    aput-byte v1, v15, v13

    const/16 v1, 0x7e

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x78

    aput-byte v1, v15, v17

    const/16 v1, 0x79

    aput-byte v1, v15, v16

    const/16 v1, 0x64

    aput-byte v1, v15, v14

    :goto_c39
    const/16 v1, 0x10

    if-ge v3, v1, :cond_c46

    .line 105
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_c39

    .line 106
    :cond_c46
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_c4e
    const/16 v1, 0x232

    if-ne v0, v1, :cond_cc9

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/16 v1, 0x53

    aput-byte v1, v15, v3

    const/16 v1, 0x5c

    aput-byte v1, v15, v2

    const/16 v1, 0x56

    aput-byte v1, v15, v10

    const/16 v1, 0x40

    aput-byte v1, v15, v9

    const/16 v1, 0x5d

    aput-byte v1, v15, v11

    const/16 v1, 0x5b

    aput-byte v1, v15, v6

    const/16 v1, 0x56

    aput-byte v1, v15, v12

    const/16 v1, 0x1c

    aput-byte v1, v15, v5

    const/16 v1, 0x53

    aput-byte v1, v15, v7

    const/16 v1, 0x42

    aput-byte v1, v15, v4

    const/16 v1, 0x42

    aput-byte v1, v15, v8

    const/16 v1, 0x1c

    aput-byte v1, v15, v13

    const/16 v1, 0x7e

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x5d

    aput-byte v1, v15, v17

    const/16 v1, 0x53

    aput-byte v1, v15, v16

    const/16 v1, 0x56

    aput-byte v1, v15, v14

    const/16 v1, 0x57

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, 0x56

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, 0x73

    aput-byte v1, v15, v19

    const/16 v1, 0x42

    const/16 v2, 0x13

    aput-byte v1, v15, v2

    const/16 v1, 0x14

    const/16 v2, 0x59

    aput-byte v2, v15, v1

    :goto_cb4
    const/16 v1, 0x15

    if-ge v3, v1, :cond_cc1

    .line 107
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_cb4

    .line 108
    :cond_cc1
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_cc9
    const/16 v1, 0x238

    if-ne v0, v1, :cond_d28

    const/16 v1, 0x10

    new-array v15, v1, [B

    const/16 v1, 0x55

    aput-byte v1, v15, v3

    const/16 v1, 0x79

    aput-byte v1, v15, v2

    const/16 v1, 0x48

    aput-byte v1, v15, v10

    const/16 v1, 0x48

    aput-byte v1, v15, v9

    const/16 v1, 0x54

    aput-byte v1, v15, v11

    const/16 v1, 0x51

    aput-byte v1, v15, v6

    const/16 v1, 0x5b

    aput-byte v1, v15, v12

    const/16 v1, 0x59

    aput-byte v1, v15, v5

    const/16 v1, 0x4c

    aput-byte v1, v15, v7

    const/16 v1, 0x51

    aput-byte v1, v15, v4

    const/16 v1, 0x57

    aput-byte v1, v15, v8

    const/16 v1, 0x56

    aput-byte v1, v15, v13

    const/16 v1, 0x71

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x56

    aput-byte v1, v15, v17

    const/16 v1, 0x5e

    aput-byte v1, v15, v16

    const/16 v1, 0x57

    aput-byte v1, v15, v14

    :goto_d13
    const/16 v1, 0x10

    if-ge v3, v1, :cond_d20

    .line 109
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_d13

    .line 110
    :cond_d20
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_d28
    const/16 v1, 0x24b

    if-ne v0, v1, :cond_dff

    const/16 v1, 0x26

    new-array v15, v1, [B

    const/16 v20, 0x2a

    aput-byte v20, v15, v3

    const/16 v20, 0x25

    aput-byte v20, v15, v2

    const/16 v2, 0x2f

    aput-byte v2, v15, v10

    const/16 v2, 0x39

    aput-byte v2, v15, v9

    const/16 v2, 0x24

    aput-byte v2, v15, v11

    const/16 v2, 0x22

    aput-byte v2, v15, v6

    const/16 v2, 0x2f

    aput-byte v2, v15, v12

    const/16 v2, 0x65

    aput-byte v2, v15, v5

    const/16 v2, 0x2a

    aput-byte v2, v15, v7

    const/16 v2, 0x3b

    aput-byte v2, v15, v4

    const/16 v2, 0x3b

    aput-byte v2, v15, v8

    const/16 v2, 0x65

    aput-byte v2, v15, v13

    const/16 v2, 0xc

    aput-byte v8, v15, v2

    const/16 v2, 0x28

    aput-byte v2, v15, v17

    const/16 v2, 0x3f

    aput-byte v2, v15, v16

    const/16 v2, 0x22

    aput-byte v2, v15, v14

    const/16 v2, 0x3d

    const/16 v5, 0x10

    aput-byte v2, v15, v5

    const/16 v2, 0x22

    const/16 v5, 0x11

    aput-byte v2, v15, v5

    const/16 v2, 0x3f

    aput-byte v2, v15, v19

    const/16 v2, 0x32

    const/16 v5, 0x13

    aput-byte v2, v15, v5

    const/16 v2, 0x14

    const/16 v5, 0x1f

    aput-byte v5, v15, v2

    const/16 v2, 0x23

    const/16 v5, 0x15

    aput-byte v2, v15, v5

    const/16 v2, 0x16

    const/16 v5, 0x39

    aput-byte v5, v15, v2

    const/16 v2, 0x17

    const/16 v5, 0x2e

    aput-byte v5, v15, v2

    const/16 v2, 0x18

    const/16 v5, 0x2a

    aput-byte v5, v15, v2

    const/16 v2, 0x19

    const/16 v5, 0x2f

    aput-byte v5, v15, v2

    const/16 v2, 0x1a

    const/16 v5, 0x6f

    aput-byte v5, v15, v2

    const/16 v2, 0x1b

    aput-byte v8, v15, v2

    const/16 v2, 0x1c

    const/16 v5, 0x3b

    aput-byte v5, v15, v2

    const/16 v2, 0x1d

    const/16 v5, 0x3b

    aput-byte v5, v15, v2

    const/16 v2, 0x1e

    aput-byte v4, v15, v2

    const/16 v2, 0x1f

    const/16 v4, 0x22

    aput-byte v4, v15, v2

    const/16 v2, 0x20

    const/16 v4, 0x25

    aput-byte v4, v15, v2

    const/16 v2, 0x21

    const/16 v4, 0x2f

    aput-byte v4, v15, v2

    const/16 v2, 0x22

    aput-byte v14, v15, v2

    const/16 v2, 0x23

    const/16 v4, 0x2a

    aput-byte v4, v15, v2

    const/16 v2, 0x24

    const/16 v4, 0x3f

    aput-byte v4, v15, v2

    const/16 v2, 0x25

    const/16 v4, 0x2a

    aput-byte v4, v15, v2

    :goto_dec
    if-ge v3, v1, :cond_df7

    .line 111
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_dec

    .line 112
    :cond_df7
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_dff
    const/16 v1, 0x251

    if-ne v0, v1, :cond_e34

    new-array v1, v5, [B

    const/16 v4, 0x30

    aput-byte v4, v1, v3

    const/16 v4, 0x21

    aput-byte v4, v1, v2

    const/16 v2, 0x21

    aput-byte v2, v1, v10

    const/16 v2, 0x18

    aput-byte v2, v1, v9

    const/16 v2, 0x3f

    aput-byte v2, v1, v11

    const/16 v2, 0x37

    aput-byte v2, v1, v6

    const/16 v2, 0x3e

    aput-byte v2, v1, v12

    :goto_e21
    if-ge v3, v5, :cond_e2c

    .line 113
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_e21

    .line 114
    :cond_e2c
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_e34
    const/16 v1, 0x276

    if-ne v0, v1, :cond_e9d

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/16 v1, 0x17

    aput-byte v1, v15, v3

    const/16 v1, 0x18

    aput-byte v1, v15, v2

    aput-byte v19, v15, v10

    aput-byte v11, v15, v9

    const/16 v1, 0x19

    aput-byte v1, v15, v11

    const/16 v1, 0x1f

    aput-byte v1, v15, v6

    aput-byte v19, v15, v12

    const/16 v1, 0x58

    aput-byte v1, v15, v5

    const/16 v1, 0x17

    aput-byte v1, v15, v7

    aput-byte v12, v15, v4

    aput-byte v12, v15, v8

    const/16 v1, 0x58

    aput-byte v1, v15, v13

    const/16 v1, 0x3a

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x19

    aput-byte v1, v15, v17

    const/16 v1, 0x17

    aput-byte v1, v15, v16

    aput-byte v19, v15, v14

    const/16 v1, 0x13

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v2, 0x11

    aput-byte v19, v15, v2

    const/16 v2, 0x37

    aput-byte v2, v15, v19

    aput-byte v12, v15, v1

    const/16 v1, 0x14

    const/16 v2, 0x1d

    aput-byte v2, v15, v1

    :goto_e88
    const/16 v1, 0x15

    if-ge v3, v1, :cond_e95

    .line 115
    aget-byte v1, v15, v3

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_e88

    .line 116
    :cond_e95
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_e9d
    const/16 v1, 0x27c

    if-ne v0, v1, :cond_eec

    new-array v1, v14, [B

    const/16 v15, 0x11

    aput-byte v15, v1, v3

    const/16 v15, 0x1d

    aput-byte v15, v1, v2

    const/16 v2, 0x17

    aput-byte v2, v1, v10

    const/16 v2, 0x19

    aput-byte v2, v1, v9

    const/16 v2, 0x3d

    aput-byte v2, v1, v11

    const/16 v2, 0xc

    aput-byte v2, v1, v6

    aput-byte v2, v1, v12

    const/16 v2, 0x10

    aput-byte v2, v1, v5

    const/16 v2, 0x15

    aput-byte v2, v1, v7

    const/16 v5, 0x1f

    aput-byte v5, v1, v4

    const/16 v4, 0x1d

    aput-byte v4, v1, v8

    aput-byte v7, v1, v13

    const/16 v4, 0xc

    aput-byte v2, v1, v4

    const/16 v2, 0x13

    aput-byte v2, v1, v17

    aput-byte v19, v1, v16

    :goto_ed9
    if-ge v3, v14, :cond_ee4

    .line 117
    aget-byte v2, v1, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_ed9

    .line 118
    :cond_ee4
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_eec
    const/16 v1, 0x29b

    if-ne v0, v1, :cond_f7c

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x6

    aput-byte v20, v15, v3

    const/16 v20, -0xb

    aput-byte v20, v15, v2

    const/4 v2, -0x1

    aput-byte v2, v15, v10

    const/16 v2, -0x17

    aput-byte v2, v15, v9

    const/16 v2, -0xc

    aput-byte v2, v15, v11

    const/16 v2, -0xe

    aput-byte v2, v15, v6

    const/4 v2, -0x1

    aput-byte v2, v15, v12

    const/16 v2, -0x4b

    aput-byte v2, v15, v5

    const/4 v2, -0x6

    aput-byte v2, v15, v7

    const/16 v2, -0x15

    aput-byte v2, v15, v4

    const/16 v2, -0x15

    aput-byte v2, v15, v8

    const/16 v2, -0x4b

    aput-byte v2, v15, v13

    const/16 v2, -0x26

    const/16 v4, 0xc

    aput-byte v2, v15, v4

    const/4 v2, -0x8

    aput-byte v2, v15, v17

    const/16 v2, -0x11

    aput-byte v2, v15, v16

    const/16 v2, -0xe

    aput-byte v2, v15, v14

    const/16 v2, -0x13

    const/16 v4, 0x10

    aput-byte v2, v15, v4

    const/16 v2, -0xe

    const/16 v4, 0x11

    aput-byte v2, v15, v4

    const/16 v2, -0x11

    aput-byte v2, v15, v19

    const/16 v2, -0x1e

    const/16 v4, 0x13

    aput-byte v2, v15, v4

    const/16 v2, 0x14

    const/16 v4, -0x31

    aput-byte v4, v15, v2

    const/16 v2, -0xd

    const/16 v4, 0x15

    aput-byte v2, v15, v4

    const/16 v2, 0x16

    const/16 v4, -0x17

    aput-byte v4, v15, v2

    const/16 v2, 0x17

    const/4 v4, -0x2

    aput-byte v4, v15, v2

    const/16 v2, 0x18

    const/4 v4, -0x6

    aput-byte v4, v15, v2

    const/16 v2, 0x19

    const/4 v4, -0x1

    aput-byte v4, v15, v2

    :goto_f69
    if-ge v3, v1, :cond_f74

    .line 119
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_f69

    .line 120
    :cond_f74
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_f7c
    const/16 v1, 0x2a1

    if-ne v0, v1, :cond_fed

    const/16 v1, 0x13

    new-array v15, v1, [B

    const/16 v1, -0x34

    aput-byte v1, v15, v3

    const/16 v1, -0x18

    aput-byte v1, v15, v2

    const/16 v1, -0x31

    aput-byte v1, v15, v10

    const/16 v1, -0x38

    aput-byte v1, v15, v9

    const/16 v1, -0x2b

    aput-byte v1, v15, v11

    const/16 v1, -0x38

    aput-byte v1, v15, v6

    const/16 v1, -0x40

    aput-byte v1, v15, v12

    const/16 v1, -0x33

    aput-byte v1, v15, v5

    const/16 v1, -0x20

    aput-byte v1, v15, v7

    const/16 v1, -0x2f

    aput-byte v1, v15, v4

    const/16 v1, -0x2f

    aput-byte v1, v15, v8

    const/16 v1, -0x33

    aput-byte v1, v15, v13

    const/16 v1, -0x38

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x3e

    aput-byte v1, v15, v17

    const/16 v1, -0x40

    aput-byte v1, v15, v16

    const/16 v1, -0x2b

    aput-byte v1, v15, v14

    const/16 v1, -0x38

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x32

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x31

    aput-byte v1, v15, v19

    :goto_fd6
    const/16 v1, 0x13

    if-ge v3, v1, :cond_fe3

    .line 121
    aget-byte v2, v15, v3

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_fd6

    .line 122
    :cond_fe3
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v15, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V
    :try_end_fea
    .catch Ljava/lang/Exception; {:try_start_4e8 .. :try_end_fea} :catch_feb

    return-object v0

    :catch_feb
    const/4 v0, 0x0

    return-object v0

    :cond_fed
    const/4 v0, 0x0

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
    invoke-static {p0}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏;->ﾠ͏(Landroid/content/Context;)V

    return-void
.end method

.method public onCreate()V
    .registers 10

    .line 1
    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    const-string v0, "android.app.Application"

    .line 2
    invoke-direct {p0, v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏(Ljava/lang/String;)Landroid/app/Application;

    move-result-object v0

    .line 3
    sget-object v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    if-eqz v1, :cond_e1

    const/4 v1, 0x0

    .line 4
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    :goto_10
    sget v2, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget-object v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    array-length v3, v3

    if-ge v2, v3, :cond_e1

    const v2, 0x54157fbd

    .line 5
    :try_start_1a
    sget-object v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v6

    sget v7, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sub-int/2addr v6, v7

    invoke-interface {v5, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    aget v5, v5, v6

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v3, v2, v4}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_46
    .catch Ljava/lang/Exception; {:try_start_1a .. :try_end_46} :catch_46

    .line 6
    :catch_46
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    :goto_48
    sget v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sget-object v4, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v4

    if-ge v3, v4, :cond_d9

    .line 7
    sget-object v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    sget v4, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v7

    sget v8, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    sub-int/2addr v7, v8

    invoke-interface {v6, v7}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫:[I

    aget v6, v6, v2

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v3, v4, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 8
    sput v1, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    :goto_80
    sget v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    sget v4, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    sget v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    add-int/2addr v4, v5

    if-ge v3, v4, :cond_d3

    if-nez v3, :cond_aa

    .line 9
    :try_start_8b
    sget-object v4, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v4, v3, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    goto :goto_c8

    .line 10
    :cond_aa
    sget-object v4, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁫:Ljava/util/List;

    invoke-interface {v6, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget v6, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v4, v3, v5}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;
    :try_end_c8
    .catch Ljava/lang/Exception; {:try_start_8b .. :try_end_c8} :catch_c8

    .line 11
    :catch_c8
    :goto_c8
    sget v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v3, v3, 0x1

    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    add-int/lit8 v3, v3, 0x1

    .line 12
    sput v3, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁫⁪:I

    goto :goto_80

    :cond_d3
    add-int/lit8 v5, v5, 0x1

    .line 13
    sput v5, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪:I

    goto/16 :goto_48

    .line 14
    :cond_d9
    sget v2, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    add-int/lit8 v2, v2, 0x1

    sput v2, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏:I

    goto/16 :goto_10

    :cond_e1
    if-eqz v0, :cond_e6

    .line 15
    invoke-virtual {v0}, Landroid/app/Application;->onCreate()V

    :cond_e6
    return-void
.end method
