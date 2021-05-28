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
    .registers 10
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "appClassName"
        }
    .end annotation

    const/16 v0, 0x180

    invoke-static {v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v0

    const/16 v1, 0x186

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {v0, v4, v1, v3, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    const/16 v1, 0x1a1

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/16 v3, 0x1a7

    invoke-static {v3}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {v1, v0, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    const/16 v3, 0x1b6

    invoke-static {v3}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x1bc

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    invoke-static {v3, v1, v5}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    const/16 v5, 0x1c5

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x1cb

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v3, v6, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    const/16 v5, 0x1dd

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x1e3

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v0, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    const/16 v6, 0x1fd

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    const/16 v7, 0x203

    invoke-static {v7}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v0, v7}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const/16 v5, 0x21e

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x224

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v3, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroid/content/pm/ApplicationInfo;

    const/16 v6, 0x237

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    const/16 v7, 0x23d

    invoke-static {v7}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v1, v7}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/content/pm/ApplicationInfo;

    iput-object p1, v5, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    iput-object p1, v1, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    const/16 p1, 0x262

    invoke-static {p1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object p1

    const/16 v1, 0x268

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/4 v5, 0x2

    new-array v6, v5, [Ljava/lang/Object;

    sget-object v7, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    aput-object v7, v6, v2

    const/4 v7, 0x1

    aput-object v4, v6, v7

    new-array v4, v5, [Ljava/lang/Class;

    sget-object v5, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    aput-object v5, v4, v2

    const-class v2, Landroid/app/Instrumentation;

    aput-object v2, v4, v7

    invoke-static {p1, v3, v1, v6, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Application;

    const/16 v1, 0x287

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/16 v2, 0x28d

    invoke-static {v2}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v0, v2, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪⁫;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    return-object p1
.end method

.method public static ﾠ⁪⁪(I)Ljava/lang/String;
    .registers 22
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "llIIlll"
        }
    .end annotation

    move/from16 v0, p0

    const/4 v1, -0x1

    if-ne v0, v1, :cond_a

    const/4 v1, -0x2

    if-ne v0, v1, :cond_9

    goto :goto_a

    :cond_9
    const/4 v1, -0x3

    :cond_a
    :goto_a
    const/16 v1, 0x91

    const/16 v2, 0xa

    const/16 v3, 0x8

    const/16 v4, 0x9

    const-string v5, "UTF-8"

    const/4 v6, 0x7

    const/4 v7, 0x5

    const/4 v8, 0x2

    const/4 v9, 0x4

    const/4 v10, 0x6

    const/4 v11, 0x3

    const/4 v12, 0x0

    const/4 v13, 0x1

    if-ne v0, v1, :cond_59

    :try_start_1e
    new-array v1, v2, [B

    const/16 v14, -0x4b

    aput-byte v14, v1, v12

    const/16 v14, -0x2b

    aput-byte v14, v1, v13

    const/16 v13, -0x2c

    aput-byte v13, v1, v8

    const/16 v8, -0x37

    aput-byte v8, v1, v11

    const/16 v8, -0x32

    aput-byte v8, v1, v9

    const/16 v8, -0x3e

    aput-byte v8, v1, v7

    const/16 v7, -0x3c

    aput-byte v7, v1, v10

    const/16 v7, -0x29

    aput-byte v7, v1, v6

    const/16 v6, -0x28

    aput-byte v6, v1, v3

    const/16 v3, -0x37

    aput-byte v3, v1, v4

    :goto_48
    if-ge v12, v2, :cond_53

    aget-byte v3, v1, v12

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_48

    :cond_53
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_59
    const/16 v1, 0xa8

    const/16 v14, 0xb

    if-ne v0, v1, :cond_9c

    new-array v1, v14, [B

    const/16 v15, -0x74

    aput-byte v15, v1, v12

    const/16 v15, -0x14

    aput-byte v15, v1, v13

    const/16 v13, -0x13

    aput-byte v13, v1, v8

    const/16 v8, -0x10

    aput-byte v8, v1, v11

    const/16 v8, -0x9

    aput-byte v8, v1, v9

    const/4 v8, -0x8

    aput-byte v8, v1, v7

    const/4 v7, -0x6

    aput-byte v7, v1, v10

    const/16 v7, -0x13

    aput-byte v7, v1, v6

    const/16 v6, -0x12

    aput-byte v6, v1, v3

    const/16 v3, -0x1f

    aput-byte v3, v1, v4

    const/16 v3, -0x10

    aput-byte v3, v1, v2

    :goto_8b
    if-ge v12, v14, :cond_96

    aget-byte v2, v1, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_8b

    :cond_96
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_9c
    const/16 v1, 0xbf

    if-ne v0, v1, :cond_d0

    new-array v1, v3, [B

    const/16 v2, -0x65

    aput-byte v2, v1, v12

    const/4 v2, -0x5

    aput-byte v2, v1, v13

    const/4 v2, -0x6

    aput-byte v2, v1, v8

    const/16 v2, -0x19

    aput-byte v2, v1, v11

    const/16 v2, -0x20

    aput-byte v2, v1, v9

    const/4 v2, -0x5

    aput-byte v2, v1, v7

    const/16 v2, -0xa

    aput-byte v2, v1, v10

    const/16 v2, -0x13

    aput-byte v2, v1, v6

    :goto_bf
    if-ge v12, v3, :cond_ca

    aget-byte v2, v1, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_bf

    :cond_ca
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_d0
    const/16 v1, 0xd6

    const/16 v15, 0xc

    if-ne v0, v1, :cond_119

    new-array v1, v15, [B

    const/16 v16, -0xe

    aput-byte v16, v1, v12

    const/16 v16, -0x7a

    aput-byte v16, v1, v13

    const/16 v13, -0x7c

    aput-byte v13, v1, v8

    const/16 v8, -0x67

    aput-byte v8, v1, v11

    const/16 v8, -0x7e

    aput-byte v8, v1, v9

    const/16 v8, -0x6d

    aput-byte v8, v1, v7

    const/16 v7, -0x6b

    aput-byte v7, v1, v10

    const/16 v7, -0x7e

    aput-byte v7, v1, v6

    const/16 v6, -0x77

    aput-byte v6, v1, v3

    const/16 v3, -0x63

    aput-byte v3, v1, v4

    const/16 v3, -0x6d

    aput-byte v3, v1, v2

    const/16 v2, -0x71

    aput-byte v2, v1, v14

    :goto_108
    if-ge v12, v15, :cond_113

    aget-byte v2, v1, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_108

    :cond_113
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_119
    const/16 v1, 0xed

    if-ne v0, v1, :cond_144

    new-array v1, v7, [B

    const/16 v2, -0x37

    aput-byte v2, v1, v12

    const/16 v2, -0x57

    aput-byte v2, v1, v13

    const/16 v2, -0x54

    aput-byte v2, v1, v8

    const/16 v2, -0x47

    aput-byte v2, v1, v11

    const/16 v2, -0x54

    aput-byte v2, v1, v9

    :goto_133
    if-ge v12, v7, :cond_13e

    aget-byte v2, v1, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_133

    :cond_13e
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_144
    const/16 v1, 0x101

    const/16 v17, 0x12

    const/16 v18, 0xe

    const/16 v19, 0xd

    if-ne v0, v1, :cond_1cf

    const/16 v1, 0x17

    new-array v15, v1, [B

    const/16 v20, 0x60

    aput-byte v20, v15, v12

    const/16 v20, 0x6f

    aput-byte v20, v15, v13

    const/16 v13, 0x65

    aput-byte v13, v15, v8

    const/16 v8, 0x73

    aput-byte v8, v15, v11

    const/16 v8, 0x6e

    aput-byte v8, v15, v9

    const/16 v8, 0x68

    aput-byte v8, v15, v7

    const/16 v7, 0x65

    aput-byte v7, v15, v10

    const/16 v7, 0x2f

    aput-byte v7, v15, v6

    const/16 v6, 0x60

    aput-byte v6, v15, v3

    const/16 v3, 0x71

    aput-byte v3, v15, v4

    const/16 v3, 0x71

    aput-byte v3, v15, v2

    const/16 v2, 0x2f

    aput-byte v2, v15, v14

    const/16 v2, 0x40

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, 0x71

    aput-byte v2, v15, v19

    const/16 v2, 0x71

    aput-byte v2, v15, v18

    const/16 v2, 0x6d

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, 0x68

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, 0x62

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, 0x60

    aput-byte v2, v15, v17

    const/16 v2, 0x75

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, 0x68

    aput-byte v3, v15, v2

    const/16 v2, 0x6e

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, 0x6f

    aput-byte v3, v15, v2

    :goto_1be
    if-ge v12, v1, :cond_1c9

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_1be

    :cond_1c9
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_1cf
    const/16 v1, 0x180

    if-ne v0, v1, :cond_265

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x1f

    aput-byte v20, v15, v12

    const/16 v20, -0x12

    aput-byte v20, v15, v13

    const/16 v13, -0x1c

    aput-byte v13, v15, v8

    const/16 v8, -0xe

    aput-byte v8, v15, v11

    const/16 v8, -0x11

    aput-byte v8, v15, v9

    const/16 v8, -0x17

    aput-byte v8, v15, v7

    const/16 v7, -0x1c

    aput-byte v7, v15, v10

    const/16 v7, -0x52

    aput-byte v7, v15, v6

    const/16 v6, -0x1f

    aput-byte v6, v15, v3

    const/16 v3, -0x10

    aput-byte v3, v15, v4

    const/16 v3, -0x10

    aput-byte v3, v15, v2

    const/16 v2, -0x52

    aput-byte v2, v15, v14

    const/16 v2, -0x3f

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, -0x1d

    aput-byte v2, v15, v19

    const/16 v2, -0xc

    aput-byte v2, v15, v18

    const/16 v2, -0x17

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, -0xa

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, -0x17

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, -0xc

    aput-byte v2, v15, v17

    const/4 v2, -0x7

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, -0x2c

    aput-byte v3, v15, v2

    const/16 v2, -0x18

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, -0xe

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, -0x1b

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, -0x1f

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, -0x1c

    aput-byte v3, v15, v2

    :goto_254
    if-ge v12, v1, :cond_25f

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_254

    :cond_25f
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_265
    const/16 v1, 0x186

    if-ne v0, v1, :cond_2df

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/16 v1, -0x1b

    aput-byte v1, v15, v12

    const/16 v1, -0xd

    aput-byte v1, v15, v13

    const/16 v1, -0xc

    aput-byte v1, v15, v8

    const/16 v1, -0xc

    aput-byte v1, v15, v11

    const/16 v1, -0x1d

    aput-byte v1, v15, v9

    const/16 v1, -0x18

    aput-byte v1, v15, v7

    const/16 v1, -0xe

    aput-byte v1, v15, v10

    const/16 v1, -0x39

    aput-byte v1, v15, v6

    const/16 v1, -0x1b

    aput-byte v1, v15, v3

    const/16 v1, -0xe

    aput-byte v1, v15, v4

    const/16 v1, -0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x10

    aput-byte v1, v15, v14

    const/16 v1, -0x11

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0xe

    aput-byte v1, v15, v19

    const/4 v1, -0x1

    aput-byte v1, v15, v18

    const/16 v1, -0x2e

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    const/16 v1, -0x12

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0xc

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x1d

    aput-byte v1, v15, v17

    const/16 v1, -0x19

    const/16 v2, 0x13

    aput-byte v1, v15, v2

    const/16 v1, 0x14

    const/16 v2, -0x1e

    aput-byte v2, v15, v1

    :goto_2cc
    const/16 v1, 0x15

    if-ge v12, v1, :cond_2d9

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_2cc

    :cond_2d9
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_2df
    const/16 v1, 0x1a1

    if-ne v0, v1, :cond_376

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x40

    aput-byte v20, v15, v12

    const/16 v20, -0x31

    aput-byte v20, v15, v13

    const/16 v13, -0x3b

    aput-byte v13, v15, v8

    const/16 v8, -0x2d

    aput-byte v8, v15, v11

    const/16 v8, -0x32

    aput-byte v8, v15, v9

    const/16 v8, -0x38

    aput-byte v8, v15, v7

    const/16 v7, -0x3b

    aput-byte v7, v15, v10

    const/16 v7, -0x71

    aput-byte v7, v15, v6

    const/16 v6, -0x40

    aput-byte v6, v15, v3

    const/16 v3, -0x2f

    aput-byte v3, v15, v4

    const/16 v3, -0x2f

    aput-byte v3, v15, v2

    const/16 v2, -0x71

    aput-byte v2, v15, v14

    const/16 v2, -0x20

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, -0x3e

    aput-byte v2, v15, v19

    const/16 v2, -0x2b

    aput-byte v2, v15, v18

    const/16 v2, -0x38

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, -0x29

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, -0x38

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, -0x2b

    aput-byte v2, v15, v17

    const/16 v2, -0x28

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, -0xb

    aput-byte v3, v15, v2

    const/16 v2, -0x37

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, -0x2d

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, -0x3c

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, -0x40

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, -0x3b

    aput-byte v3, v15, v2

    :goto_365
    if-ge v12, v1, :cond_370

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_365

    :cond_370
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_376
    const/16 v1, 0x1a7

    if-ne v0, v1, :cond_3db

    const/16 v1, 0x11

    new-array v15, v1, [B

    const/16 v1, -0x36

    aput-byte v1, v15, v12

    const/16 v1, -0x1b

    aput-byte v1, v15, v13

    const/16 v1, -0x38

    aput-byte v1, v15, v8

    const/16 v1, -0x2e

    aput-byte v1, v15, v11

    const/16 v1, -0x37

    aput-byte v1, v15, v9

    const/16 v1, -0x3d

    aput-byte v1, v15, v7

    const/16 v1, -0x1a

    aput-byte v1, v15, v10

    const/16 v1, -0x29

    aput-byte v1, v15, v6

    const/16 v1, -0x29

    aput-byte v1, v15, v3

    const/16 v1, -0x35

    aput-byte v1, v15, v4

    const/16 v1, -0x32

    aput-byte v1, v15, v2

    const/16 v1, -0x3c

    aput-byte v1, v15, v14

    const/16 v1, -0x3a

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x2d

    aput-byte v1, v15, v19

    const/16 v1, -0x32

    aput-byte v1, v15, v18

    const/16 v1, -0x38

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    const/16 v1, -0x37

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    :goto_3c8
    const/16 v1, 0x11

    if-ge v12, v1, :cond_3d5

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_3c8

    :cond_3d5
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_3db
    const/16 v1, 0x1b6

    if-ne v0, v1, :cond_4ba

    const/16 v1, 0x26

    new-array v15, v1, [B

    const/16 v20, -0x29

    aput-byte v20, v15, v12

    const/16 v20, -0x28

    aput-byte v20, v15, v13

    const/16 v13, -0x2e

    aput-byte v13, v15, v8

    const/16 v8, -0x3c

    aput-byte v8, v15, v11

    const/16 v8, -0x27

    aput-byte v8, v15, v9

    const/16 v8, -0x21

    aput-byte v8, v15, v7

    const/16 v7, -0x2e

    aput-byte v7, v15, v10

    const/16 v7, -0x68

    aput-byte v7, v15, v6

    const/16 v6, -0x29

    aput-byte v6, v15, v3

    const/16 v3, -0x3a

    aput-byte v3, v15, v4

    const/16 v3, -0x3a

    aput-byte v3, v15, v2

    const/16 v2, -0x68

    aput-byte v2, v15, v14

    const/16 v2, -0x9

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, -0x2b

    aput-byte v2, v15, v19

    const/16 v2, -0x3e

    aput-byte v2, v15, v18

    const/16 v2, -0x21

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, -0x40

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, -0x21

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, -0x3e

    aput-byte v2, v15, v17

    const/16 v2, -0x31

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, -0x1e

    aput-byte v3, v15, v2

    const/16 v2, -0x22

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, -0x3c

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, -0x2d

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, -0x29

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, -0x2e

    aput-byte v3, v15, v2

    const/16 v2, 0x1a

    const/16 v3, -0x6e

    aput-byte v3, v15, v2

    const/16 v2, 0x1b

    const/16 v3, -0x9

    aput-byte v3, v15, v2

    const/16 v2, 0x1c

    const/16 v3, -0x3a

    aput-byte v3, v15, v2

    const/16 v2, 0x1d

    const/16 v3, -0x3a

    aput-byte v3, v15, v2

    const/16 v2, 0x1e

    const/16 v3, -0xc

    aput-byte v3, v15, v2

    const/16 v2, 0x1f

    const/16 v3, -0x21

    aput-byte v3, v15, v2

    const/16 v2, 0x20

    const/16 v3, -0x28

    aput-byte v3, v15, v2

    const/16 v2, 0x21

    const/16 v3, -0x2e

    aput-byte v3, v15, v2

    const/16 v2, 0x22

    const/16 v3, -0xe

    aput-byte v3, v15, v2

    const/16 v2, 0x23

    const/16 v3, -0x29

    aput-byte v3, v15, v2

    const/16 v2, 0x24

    const/16 v3, -0x3e

    aput-byte v3, v15, v2

    const/16 v2, 0x25

    const/16 v3, -0x29

    aput-byte v3, v15, v2

    :goto_4a9
    if-ge v12, v1, :cond_4b4

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_4a9

    :cond_4b4
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_4ba
    const/16 v1, 0x1bc

    if-ne v0, v1, :cond_4e1

    new-array v1, v9, [B

    const/16 v2, -0x2b

    aput-byte v2, v1, v12

    const/16 v2, -0x2e

    aput-byte v2, v1, v13

    const/16 v2, -0x26

    aput-byte v2, v1, v8

    const/16 v2, -0x2d

    aput-byte v2, v1, v11

    :goto_4d0
    if-ge v12, v9, :cond_4db

    aget-byte v2, v1, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_4d0

    :cond_4db
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_4e1
    const/16 v1, 0x1c5

    if-ne v0, v1, :cond_55c

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/16 v1, -0x5c

    aput-byte v1, v15, v12

    const/16 v1, -0x55

    aput-byte v1, v15, v13

    const/16 v1, -0x5f

    aput-byte v1, v15, v8

    const/16 v1, -0x49

    aput-byte v1, v15, v11

    const/16 v1, -0x56

    aput-byte v1, v15, v9

    const/16 v1, -0x54

    aput-byte v1, v15, v7

    const/16 v1, -0x5f

    aput-byte v1, v15, v10

    const/16 v1, -0x15

    aput-byte v1, v15, v6

    const/16 v1, -0x5c

    aput-byte v1, v15, v3

    const/16 v1, -0x4b

    aput-byte v1, v15, v4

    const/16 v1, -0x4b

    aput-byte v1, v15, v2

    const/16 v1, -0x15

    aput-byte v1, v15, v14

    const/16 v1, -0x77

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x56

    aput-byte v1, v15, v19

    const/16 v1, -0x5c

    aput-byte v1, v15, v18

    const/16 v1, -0x5f

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    const/16 v1, -0x60

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x5f

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x7c

    aput-byte v1, v15, v17

    const/16 v1, -0x4b

    const/16 v2, 0x13

    aput-byte v1, v15, v2

    const/16 v1, 0x14

    const/16 v2, -0x52

    aput-byte v2, v15, v1

    :goto_549
    const/16 v1, 0x15

    if-ge v12, v1, :cond_556

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_549

    :cond_556
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_55c
    const/16 v1, 0x1cb

    if-ne v0, v1, :cond_5a7

    const/16 v1, 0xc

    new-array v15, v1, [B

    const/16 v1, -0x5a

    aput-byte v1, v15, v12

    const/16 v1, -0x76

    aput-byte v1, v15, v13

    const/16 v1, -0x45

    aput-byte v1, v15, v8

    const/16 v1, -0x45

    aput-byte v1, v15, v11

    const/16 v1, -0x59

    aput-byte v1, v15, v9

    const/16 v1, -0x5e

    aput-byte v1, v15, v7

    const/16 v1, -0x58

    aput-byte v1, v15, v10

    const/16 v1, -0x56

    aput-byte v1, v15, v6

    const/16 v1, -0x41

    aput-byte v1, v15, v3

    const/16 v1, -0x5e

    aput-byte v1, v15, v4

    const/16 v1, -0x5c

    aput-byte v1, v15, v2

    const/16 v1, -0x5b

    aput-byte v1, v15, v14

    :goto_594
    const/16 v1, 0xc

    if-ge v12, v1, :cond_5a1

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_594

    :cond_5a1
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_5a7
    const/16 v1, 0x1dd

    if-ne v0, v1, :cond_63e

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x44

    aput-byte v20, v15, v12

    const/16 v20, -0x4d

    aput-byte v20, v15, v13

    const/16 v13, -0x47

    aput-byte v13, v15, v8

    const/16 v8, -0x51

    aput-byte v8, v15, v11

    const/16 v8, -0x4e

    aput-byte v8, v15, v9

    const/16 v8, -0x4c

    aput-byte v8, v15, v7

    const/16 v7, -0x47

    aput-byte v7, v15, v10

    const/16 v7, -0xd

    aput-byte v7, v15, v6

    const/16 v6, -0x44

    aput-byte v6, v15, v3

    const/16 v3, -0x53

    aput-byte v3, v15, v4

    const/16 v3, -0x53

    aput-byte v3, v15, v2

    const/16 v2, -0xd

    aput-byte v2, v15, v14

    const/16 v2, -0x64

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, -0x42

    aput-byte v2, v15, v19

    const/16 v2, -0x57

    aput-byte v2, v15, v18

    const/16 v2, -0x4c

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, -0x55

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, -0x4c

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, -0x57

    aput-byte v2, v15, v17

    const/16 v2, -0x5c

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, -0x77

    aput-byte v3, v15, v2

    const/16 v2, -0x4b

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, -0x51

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, -0x48

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, -0x44

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, -0x47

    aput-byte v3, v15, v2

    :goto_62d
    if-ge v12, v1, :cond_638

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_62d

    :cond_638
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_63e
    const/16 v1, 0x1e3

    if-ne v0, v1, :cond_6ad

    const/16 v1, 0x13

    new-array v15, v1, [B

    const/16 v1, -0x72

    aput-byte v1, v15, v12

    const/16 v1, -0x56

    aput-byte v1, v15, v13

    const/16 v1, -0x73

    aput-byte v1, v15, v8

    const/16 v1, -0x76

    aput-byte v1, v15, v11

    const/16 v1, -0x69

    aput-byte v1, v15, v9

    const/16 v1, -0x76

    aput-byte v1, v15, v7

    const/16 v1, -0x7e

    aput-byte v1, v15, v10

    const/16 v1, -0x71

    aput-byte v1, v15, v6

    const/16 v1, -0x5e

    aput-byte v1, v15, v3

    const/16 v1, -0x6d

    aput-byte v1, v15, v4

    const/16 v1, -0x6d

    aput-byte v1, v15, v2

    const/16 v1, -0x71

    aput-byte v1, v15, v14

    const/16 v1, -0x76

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x80

    aput-byte v1, v15, v19

    const/16 v1, -0x7e

    aput-byte v1, v15, v18

    const/16 v1, -0x69

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    const/16 v1, -0x76

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x74

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x73

    aput-byte v1, v15, v17

    :goto_69a
    const/16 v1, 0x13

    if-ge v12, v1, :cond_6a7

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_69a

    :cond_6a7
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_6ad
    const/16 v1, 0x1fd

    if-ne v0, v1, :cond_744

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x64

    aput-byte v20, v15, v12

    const/16 v20, -0x6d

    aput-byte v20, v15, v13

    const/16 v13, -0x67

    aput-byte v13, v15, v8

    const/16 v8, -0x71

    aput-byte v8, v15, v11

    const/16 v8, -0x6e

    aput-byte v8, v15, v9

    const/16 v8, -0x6c

    aput-byte v8, v15, v7

    const/16 v7, -0x67

    aput-byte v7, v15, v10

    const/16 v7, -0x2d

    aput-byte v7, v15, v6

    const/16 v6, -0x64

    aput-byte v6, v15, v3

    const/16 v3, -0x73

    aput-byte v3, v15, v4

    const/16 v3, -0x73

    aput-byte v3, v15, v2

    const/16 v2, -0x2d

    aput-byte v2, v15, v14

    const/16 v2, -0x44

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, -0x62

    aput-byte v2, v15, v19

    const/16 v2, -0x77

    aput-byte v2, v15, v18

    const/16 v2, -0x6c

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, -0x75

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, -0x6c

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, -0x77

    aput-byte v2, v15, v17

    const/16 v2, -0x7c

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, -0x57

    aput-byte v3, v15, v2

    const/16 v2, -0x6b

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, -0x71

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, -0x68

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, -0x64

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, -0x67

    aput-byte v3, v15, v2

    :goto_733
    if-ge v12, v1, :cond_73e

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_733

    :cond_73e
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_744
    const/16 v1, 0x203

    if-ne v0, v1, :cond_7a3

    const/16 v1, 0x10

    new-array v15, v1, [B

    const/16 v1, 0x6e

    aput-byte v1, v15, v12

    const/16 v1, 0x42

    aput-byte v1, v15, v13

    const/16 v1, 0x6f

    aput-byte v1, v15, v8

    const/16 v1, 0x6f

    aput-byte v1, v15, v11

    const/16 v1, 0x42

    aput-byte v1, v15, v9

    const/16 v1, 0x73

    aput-byte v1, v15, v7

    const/16 v1, 0x73

    aput-byte v1, v15, v10

    const/16 v1, 0x6f

    aput-byte v1, v15, v6

    const/16 v1, 0x6a

    aput-byte v1, v15, v3

    const/16 v1, 0x60

    aput-byte v1, v15, v4

    const/16 v1, 0x62

    aput-byte v1, v15, v2

    const/16 v1, 0x77

    aput-byte v1, v15, v14

    const/16 v1, 0x6a

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x6c

    aput-byte v1, v15, v19

    const/16 v1, 0x6d

    aput-byte v1, v15, v18

    const/16 v1, 0x70

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    :goto_790
    const/16 v1, 0x10

    if-ge v12, v1, :cond_79d

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_790

    :cond_79d
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_7a3
    const/16 v1, 0x21e

    if-ne v0, v1, :cond_81e

    const/16 v1, 0x15

    new-array v15, v1, [B

    const/16 v1, 0x7f

    aput-byte v1, v15, v12

    const/16 v1, 0x70

    aput-byte v1, v15, v13

    const/16 v1, 0x7a

    aput-byte v1, v15, v8

    const/16 v1, 0x6c

    aput-byte v1, v15, v11

    const/16 v1, 0x71

    aput-byte v1, v15, v9

    const/16 v1, 0x77

    aput-byte v1, v15, v7

    const/16 v1, 0x7a

    aput-byte v1, v15, v10

    const/16 v1, 0x30

    aput-byte v1, v15, v6

    const/16 v1, 0x7f

    aput-byte v1, v15, v3

    const/16 v1, 0x6e

    aput-byte v1, v15, v4

    const/16 v1, 0x6e

    aput-byte v1, v15, v2

    const/16 v1, 0x30

    aput-byte v1, v15, v14

    const/16 v1, 0x52

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x71

    aput-byte v1, v15, v19

    const/16 v1, 0x7f

    aput-byte v1, v15, v18

    const/16 v1, 0x7a

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    const/16 v1, 0x7b

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, 0x7a

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, 0x5f

    aput-byte v1, v15, v17

    const/16 v1, 0x6e

    const/16 v2, 0x13

    aput-byte v1, v15, v2

    const/16 v1, 0x14

    const/16 v2, 0x75

    aput-byte v2, v15, v1

    :goto_80b
    const/16 v1, 0x15

    if-ge v12, v1, :cond_818

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_80b

    :cond_818
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_81e
    const/16 v1, 0x224

    if-ne v0, v1, :cond_87d

    const/16 v1, 0x10

    new-array v15, v1, [B

    const/16 v1, 0x49

    aput-byte v1, v15, v12

    const/16 v1, 0x65

    aput-byte v1, v15, v13

    const/16 v1, 0x54

    aput-byte v1, v15, v8

    const/16 v1, 0x54

    aput-byte v1, v15, v11

    const/16 v1, 0x48

    aput-byte v1, v15, v9

    const/16 v1, 0x4d

    aput-byte v1, v15, v7

    const/16 v1, 0x47

    aput-byte v1, v15, v10

    const/16 v1, 0x45

    aput-byte v1, v15, v6

    const/16 v1, 0x50

    aput-byte v1, v15, v3

    const/16 v1, 0x4d

    aput-byte v1, v15, v4

    const/16 v1, 0x4b

    aput-byte v1, v15, v2

    const/16 v1, 0x4a

    aput-byte v1, v15, v14

    const/16 v1, 0x6d

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, 0x4a

    aput-byte v1, v15, v19

    const/16 v1, 0x42

    aput-byte v1, v15, v18

    const/16 v1, 0x4b

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    :goto_86a
    const/16 v1, 0x10

    if-ge v12, v1, :cond_877

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_86a

    :cond_877
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_87d
    const/16 v1, 0x237

    if-ne v0, v1, :cond_95c

    const/16 v1, 0x26

    new-array v15, v1, [B

    const/16 v20, 0x56

    aput-byte v20, v15, v12

    const/16 v20, 0x59

    aput-byte v20, v15, v13

    const/16 v13, 0x53

    aput-byte v13, v15, v8

    const/16 v8, 0x45

    aput-byte v8, v15, v11

    const/16 v8, 0x58

    aput-byte v8, v15, v9

    const/16 v8, 0x5e

    aput-byte v8, v15, v7

    const/16 v7, 0x53

    aput-byte v7, v15, v10

    const/16 v7, 0x19

    aput-byte v7, v15, v6

    const/16 v6, 0x56

    aput-byte v6, v15, v3

    const/16 v3, 0x47

    aput-byte v3, v15, v4

    const/16 v3, 0x47

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    aput-byte v2, v15, v14

    const/16 v2, 0x76

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, 0x54

    aput-byte v2, v15, v19

    const/16 v2, 0x43

    aput-byte v2, v15, v18

    const/16 v2, 0x5e

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, 0x41

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, 0x5e

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, 0x43

    aput-byte v2, v15, v17

    const/16 v2, 0x4e

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, 0x63

    aput-byte v3, v15, v2

    const/16 v2, 0x5f

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, 0x45

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, 0x52

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, 0x56

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, 0x53

    aput-byte v3, v15, v2

    const/16 v2, 0x1a

    const/16 v3, 0x13

    aput-byte v3, v15, v2

    const/16 v2, 0x1b

    const/16 v3, 0x76

    aput-byte v3, v15, v2

    const/16 v2, 0x1c

    const/16 v3, 0x47

    aput-byte v3, v15, v2

    const/16 v2, 0x1d

    const/16 v3, 0x47

    aput-byte v3, v15, v2

    const/16 v2, 0x1e

    const/16 v3, 0x75

    aput-byte v3, v15, v2

    const/16 v2, 0x1f

    const/16 v3, 0x5e

    aput-byte v3, v15, v2

    const/16 v2, 0x20

    const/16 v3, 0x59

    aput-byte v3, v15, v2

    const/16 v2, 0x21

    const/16 v3, 0x53

    aput-byte v3, v15, v2

    const/16 v2, 0x22

    const/16 v3, 0x73

    aput-byte v3, v15, v2

    const/16 v2, 0x23

    const/16 v3, 0x56

    aput-byte v3, v15, v2

    const/16 v2, 0x24

    const/16 v3, 0x43

    aput-byte v3, v15, v2

    const/16 v2, 0x25

    const/16 v3, 0x56

    aput-byte v3, v15, v2

    :goto_94b
    if-ge v12, v1, :cond_956

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_94b

    :cond_956
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_95c
    const/16 v1, 0x23d

    if-ne v0, v1, :cond_98f

    new-array v1, v6, [B

    const/16 v2, 0x5c

    aput-byte v2, v1, v12

    const/16 v2, 0x4d

    aput-byte v2, v1, v13

    const/16 v2, 0x4d

    aput-byte v2, v1, v8

    const/16 v2, 0x74

    aput-byte v2, v1, v11

    const/16 v2, 0x53

    aput-byte v2, v1, v9

    const/16 v2, 0x5b

    aput-byte v2, v1, v7

    const/16 v2, 0x52

    aput-byte v2, v1, v10

    :goto_97e
    if-ge v12, v6, :cond_989

    aget-byte v2, v1, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v1, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_97e

    :cond_989
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v1, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_98f
    const/16 v1, 0x262

    if-ne v0, v1, :cond_9ec

    const/16 v1, 0x15

    new-array v15, v1, [B

    aput-byte v11, v15, v12

    const/16 v1, 0xc

    aput-byte v1, v15, v13

    aput-byte v10, v15, v8

    const/16 v1, 0x10

    aput-byte v1, v15, v11

    aput-byte v19, v15, v9

    aput-byte v14, v15, v7

    aput-byte v10, v15, v10

    const/16 v1, 0x4c

    aput-byte v1, v15, v6

    aput-byte v11, v15, v3

    aput-byte v17, v15, v4

    aput-byte v17, v15, v2

    const/16 v1, 0x4c

    aput-byte v1, v15, v14

    const/16 v1, 0x2e

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    aput-byte v19, v15, v19

    aput-byte v11, v15, v18

    const/16 v1, 0xf

    aput-byte v10, v15, v1

    const/16 v1, 0x10

    aput-byte v6, v15, v1

    const/16 v1, 0x11

    aput-byte v10, v15, v1

    const/16 v1, 0x23

    aput-byte v1, v15, v17

    const/16 v1, 0x13

    aput-byte v17, v15, v1

    const/16 v1, 0x14

    aput-byte v4, v15, v1

    :goto_9d9
    const/16 v1, 0x15

    if-ge v12, v1, :cond_9e6

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_9d9

    :cond_9e6
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_9ec
    const/16 v1, 0x268

    if-ne v0, v1, :cond_a2f

    const/16 v1, 0xf

    new-array v15, v1, [B

    aput-byte v7, v15, v12

    aput-byte v4, v15, v13

    aput-byte v11, v15, v8

    aput-byte v19, v15, v11

    const/16 v1, 0x29

    aput-byte v1, v15, v9

    const/16 v1, 0x18

    aput-byte v1, v15, v7

    const/16 v1, 0x18

    aput-byte v1, v15, v10

    aput-byte v9, v15, v6

    aput-byte v13, v15, v3

    aput-byte v14, v15, v4

    aput-byte v4, v15, v2

    const/16 v1, 0x1c

    aput-byte v1, v15, v14

    const/16 v1, 0xc

    aput-byte v13, v15, v1

    aput-byte v6, v15, v19

    aput-byte v10, v15, v18

    :goto_a1c
    const/16 v1, 0xf

    if-ge v12, v1, :cond_a29

    aget-byte v1, v15, v12

    xor-int/2addr v1, v0

    int-to-byte v1, v1

    aput-byte v1, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_a1c

    :cond_a29
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_a2f
    const/16 v1, 0x287

    if-ne v0, v1, :cond_ac5

    const/16 v1, 0x1a

    new-array v15, v1, [B

    const/16 v20, -0x1a

    aput-byte v20, v15, v12

    const/16 v20, -0x17

    aput-byte v20, v15, v13

    const/16 v13, -0x1d

    aput-byte v13, v15, v8

    const/16 v8, -0xb

    aput-byte v8, v15, v11

    const/16 v8, -0x18

    aput-byte v8, v15, v9

    const/16 v8, -0x12

    aput-byte v8, v15, v7

    const/16 v7, -0x1d

    aput-byte v7, v15, v10

    const/16 v7, -0x57

    aput-byte v7, v15, v6

    const/16 v6, -0x1a

    aput-byte v6, v15, v3

    const/16 v3, -0x9

    aput-byte v3, v15, v4

    const/16 v3, -0x9

    aput-byte v3, v15, v2

    const/16 v2, -0x57

    aput-byte v2, v15, v14

    const/16 v2, -0x3a

    const/16 v3, 0xc

    aput-byte v2, v15, v3

    const/16 v2, -0x1c

    aput-byte v2, v15, v19

    const/16 v2, -0xd

    aput-byte v2, v15, v18

    const/16 v2, -0x12

    const/16 v3, 0xf

    aput-byte v2, v15, v3

    const/16 v2, -0xf

    const/16 v3, 0x10

    aput-byte v2, v15, v3

    const/16 v2, -0x12

    const/16 v3, 0x11

    aput-byte v2, v15, v3

    const/16 v2, -0xd

    aput-byte v2, v15, v17

    const/4 v2, -0x2

    const/16 v3, 0x13

    aput-byte v2, v15, v3

    const/16 v2, 0x14

    const/16 v3, -0x2d

    aput-byte v3, v15, v2

    const/16 v2, -0x11

    const/16 v3, 0x15

    aput-byte v2, v15, v3

    const/16 v2, 0x16

    const/16 v3, -0xb

    aput-byte v3, v15, v2

    const/16 v2, 0x17

    const/16 v3, -0x1e

    aput-byte v3, v15, v2

    const/16 v2, 0x18

    const/16 v3, -0x1a

    aput-byte v3, v15, v2

    const/16 v2, 0x19

    const/16 v3, -0x1d

    aput-byte v3, v15, v2

    :goto_ab4
    if-ge v12, v1, :cond_abf

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_ab4

    :cond_abf
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V

    return-object v0

    :cond_ac5
    const/16 v1, 0x28d

    if-ne v0, v1, :cond_b32

    const/16 v1, 0x13

    new-array v15, v1, [B

    const/16 v1, -0x20

    aput-byte v1, v15, v12

    const/16 v1, -0x3c

    aput-byte v1, v15, v13

    const/16 v1, -0x1d

    aput-byte v1, v15, v8

    const/16 v1, -0x1c

    aput-byte v1, v15, v11

    const/4 v1, -0x7

    aput-byte v1, v15, v9

    const/16 v1, -0x1c

    aput-byte v1, v15, v7

    const/16 v1, -0x14

    aput-byte v1, v15, v10

    const/16 v1, -0x1f

    aput-byte v1, v15, v6

    const/16 v1, -0x34

    aput-byte v1, v15, v3

    const/4 v1, -0x3

    aput-byte v1, v15, v4

    const/4 v1, -0x3

    aput-byte v1, v15, v2

    const/16 v1, -0x1f

    aput-byte v1, v15, v14

    const/16 v1, -0x1c

    const/16 v2, 0xc

    aput-byte v1, v15, v2

    const/16 v1, -0x12

    aput-byte v1, v15, v19

    const/16 v1, -0x14

    aput-byte v1, v15, v18

    const/4 v1, -0x7

    const/16 v2, 0xf

    aput-byte v1, v15, v2

    const/16 v1, -0x1c

    const/16 v2, 0x10

    aput-byte v1, v15, v2

    const/16 v1, -0x1e

    const/16 v2, 0x11

    aput-byte v1, v15, v2

    const/16 v1, -0x1d

    aput-byte v1, v15, v17

    :goto_b1d
    const/16 v1, 0x13

    if-ge v12, v1, :cond_b2a

    aget-byte v2, v15, v12

    xor-int/2addr v2, v0

    int-to-byte v2, v2

    aput-byte v2, v15, v12

    add-int/lit8 v12, v12, 0x1

    goto :goto_b1d

    :cond_b2a
    new-instance v0, Ljava/lang/String;

    invoke-direct {v0, v15, v5}, Ljava/lang/String;-><init>([BLjava/lang/String;)V
    :try_end_b2f
    .catch Ljava/lang/Exception; {:try_start_1e .. :try_end_b2f} :catch_b30

    return-object v0

    :catch_b30
    const/4 v0, 0x0

    return-object v0

    :cond_b32
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
