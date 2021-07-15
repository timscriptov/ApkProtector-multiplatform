.class public Lcom/mcal/apkprotector/ProtectApplication;
.super Landroid/app/Application;


# direct methods
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

    const/16 v0, 0xf7

    invoke-static {v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v0

    const/16 v1, 0xfd

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    const/4 v4, 0x0

    invoke-static {v0, v4, v1, v3, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    const/16 v1, 0x118

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/16 v3, 0x11e

    invoke-static {v3}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {v1, v0, v3}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    const/16 v3, 0x12d

    invoke-static {v3}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x133

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    invoke-static {v3, v1, v5}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    const/16 v5, 0x13c

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x142

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v3, v6, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    const/16 v5, 0x154

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x15a

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v0, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    const/16 v6, 0x174

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    const/16 v7, 0x17a

    invoke-static {v7}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v0, v7}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/ArrayList;

    invoke-virtual {v6, v5}, Ljava/util/ArrayList;->remove(Ljava/lang/Object;)Z

    const/16 v5, 0x195

    invoke-static {v5}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v5

    const/16 v6, 0x19b

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v3, v6}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Landroid/content/pm/ApplicationInfo;

    const/16 v6, 0x1ae

    invoke-static {v6}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v6

    const/16 v7, 0x1b4

    invoke-static {v7}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6, v1, v7}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁪(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/content/pm/ApplicationInfo;

    iput-object p1, v5, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    iput-object p1, v1, Landroid/content/pm/ApplicationInfo;->className:Ljava/lang/String;

    const/16 p1, 0x1d9

    invoke-static {p1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object p1

    const/16 v1, 0x1df

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

    invoke-static {p1, v3, v1, v6, v4}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁪⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/app/Application;

    const/16 v1, 0x1fe

    invoke-static {v1}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v1

    const/16 v2, 0x204

    invoke-static {v2}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪⁪(I)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v0, v2, p1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁬⁫/ﾠ⁪⁫;->ﾠ⁬⁫(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)Z

    return-object p1
.end method

.method public static ﾠ⁪⁪(I)Ljava/lang/String;
    .registers 26
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "llIIlJ1"
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
    const/16 v1, 0xf7

    const/16 v3, 0x12

    const/16 v4, 0x11

    const/16 v5, 0x13

    const/16 v7, 0xf

    const/16 v8, 0xe

    const/16 v9, 0xd

    const/16 v10, 0xb

    const/16 v11, 0x8

    const/16 v12, 0xc

    const/16 v13, 0x10

    const/4 v14, 0x7

    const/4 v15, 0x5

    const/16 v16, 0xa

    const/16 v17, 0x9

    const/16 v18, 0x6

    const/16 v19, 0x2

    const/4 v6, 0x4

    const/16 v21, 0x3

    const/16 v22, 0x1

    const/16 v23, 0x0

    if-ne v0, v1, :cond_bf

    const/16 v1, 0x1a

    :try_start_35
    new-array v2, v1, [B

    const/16 v24, -0x6a

    aput-byte v24, v2, v23

    const/16 v24, -0x67

    aput-byte v24, v2, v22

    const/16 v22, -0x6d

    aput-byte v22, v2, v19

    const/16 v19, -0x7b

    aput-byte v19, v2, v21

    const/16 v19, -0x68

    aput-byte v19, v2, v6

    const/16 v6, -0x62

    aput-byte v6, v2, v15

    const/16 v6, -0x6d

    aput-byte v6, v2, v18

    const/16 v6, -0x27

    aput-byte v6, v2, v14

    const/16 v6, -0x6a

    aput-byte v6, v2, v11

    const/16 v6, -0x79

    aput-byte v6, v2, v17

    const/16 v6, -0x79

    aput-byte v6, v2, v16

    const/16 v6, -0x27

    aput-byte v6, v2, v10

    const/16 v6, -0x4a

    aput-byte v6, v2, v12

    const/16 v6, -0x6c

    aput-byte v6, v2, v9

    const/16 v6, -0x7d

    aput-byte v6, v2, v8

    const/16 v6, -0x62

    aput-byte v6, v2, v7

    const/16 v6, -0x7f

    aput-byte v6, v2, v13

    const/16 v6, -0x62

    aput-byte v6, v2, v4

    const/16 v4, -0x7d

    aput-byte v4, v2, v3

    const/16 v3, -0x72

    aput-byte v3, v2, v5

    const/16 v3, -0x5d

    const/16 v4, 0x14

    aput-byte v3, v2, v4

    const/16 v3, -0x61

    const/16 v4, 0x15

    aput-byte v3, v2, v4

    const/16 v3, 0x16

    const/16 v4, -0x7b

    aput-byte v4, v2, v3

    const/16 v3, 0x17

    const/16 v4, -0x6e

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    const/16 v4, -0x6a

    aput-byte v4, v2, v3

    const/16 v3, 0x19

    const/16 v4, -0x6d

    aput-byte v4, v2, v3

    const/4 v3, 0x0

    :goto_ac
    if-ge v3, v1, :cond_b7

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_ac

    :cond_b7
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_bf
    const/16 v1, 0xfd

    if-ne v0, v1, :cond_133

    const/16 v1, 0x15

    new-array v2, v1, [B

    const/16 v1, -0x62

    aput-byte v1, v2, v23

    const/16 v1, -0x78

    aput-byte v1, v2, v22

    const/16 v1, -0x71

    aput-byte v1, v2, v19

    const/16 v1, -0x71

    aput-byte v1, v2, v21

    const/16 v1, -0x68

    aput-byte v1, v2, v6

    const/16 v1, -0x6d

    aput-byte v1, v2, v15

    const/16 v1, -0x77

    aput-byte v1, v2, v18

    const/16 v1, -0x44

    aput-byte v1, v2, v14

    const/16 v1, -0x62

    aput-byte v1, v2, v11

    const/16 v1, -0x77

    aput-byte v1, v2, v17

    const/16 v1, -0x6c

    aput-byte v1, v2, v16

    const/16 v1, -0x75

    aput-byte v1, v2, v10

    const/16 v1, -0x6c

    aput-byte v1, v2, v12

    const/16 v1, -0x77

    aput-byte v1, v2, v9

    const/16 v1, -0x7c

    aput-byte v1, v2, v8

    const/16 v1, -0x57

    aput-byte v1, v2, v7

    const/16 v1, -0x6b

    aput-byte v1, v2, v13

    const/16 v1, -0x71

    aput-byte v1, v2, v4

    const/16 v1, -0x68

    aput-byte v1, v2, v3

    const/16 v1, -0x64

    aput-byte v1, v2, v5

    const/16 v1, -0x67

    const/16 v3, 0x14

    aput-byte v1, v2, v3

    const/4 v1, 0x0

    :goto_11e
    const/16 v3, 0x15

    if-ge v1, v3, :cond_12b

    aget-byte v3, v2, v1

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v2, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_11e

    :cond_12b
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_133
    const/16 v1, 0x118

    if-ne v0, v1, :cond_1c3

    const/16 v1, 0x1a

    new-array v2, v1, [B

    const/16 v24, 0x79

    aput-byte v24, v2, v23

    const/16 v24, 0x76

    aput-byte v24, v2, v22

    const/16 v22, 0x7c

    aput-byte v22, v2, v19

    const/16 v19, 0x6a

    aput-byte v19, v2, v21

    const/16 v19, 0x77

    aput-byte v19, v2, v6

    const/16 v6, 0x71

    aput-byte v6, v2, v15

    const/16 v6, 0x7c

    aput-byte v6, v2, v18

    const/16 v6, 0x36

    aput-byte v6, v2, v14

    const/16 v6, 0x79

    aput-byte v6, v2, v11

    const/16 v6, 0x68

    aput-byte v6, v2, v17

    const/16 v6, 0x68

    aput-byte v6, v2, v16

    const/16 v6, 0x36

    aput-byte v6, v2, v10

    const/16 v6, 0x59

    aput-byte v6, v2, v12

    const/16 v6, 0x7b

    aput-byte v6, v2, v9

    const/16 v6, 0x6c

    aput-byte v6, v2, v8

    const/16 v6, 0x71

    aput-byte v6, v2, v7

    const/16 v6, 0x6e

    aput-byte v6, v2, v13

    const/16 v6, 0x71

    aput-byte v6, v2, v4

    const/16 v4, 0x6c

    aput-byte v4, v2, v3

    const/16 v3, 0x61

    aput-byte v3, v2, v5

    const/16 v3, 0x4c

    const/16 v4, 0x14

    aput-byte v3, v2, v4

    const/16 v3, 0x70

    const/16 v4, 0x15

    aput-byte v3, v2, v4

    const/16 v3, 0x16

    const/16 v4, 0x6a

    aput-byte v4, v2, v3

    const/16 v3, 0x17

    const/16 v4, 0x7d

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    const/16 v4, 0x79

    aput-byte v4, v2, v3

    const/16 v3, 0x19

    const/16 v4, 0x7c

    aput-byte v4, v2, v3

    const/4 v3, 0x0

    :goto_1b0
    if-ge v3, v1, :cond_1bb

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_1b0

    :cond_1bb
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_1c3
    const/16 v1, 0x11e

    if-ne v0, v1, :cond_221

    new-array v1, v4, [B

    const/16 v2, 0x73

    aput-byte v2, v1, v23

    const/16 v2, 0x5c

    aput-byte v2, v1, v22

    const/16 v2, 0x71

    aput-byte v2, v1, v19

    const/16 v2, 0x6b

    aput-byte v2, v1, v21

    const/16 v2, 0x70

    aput-byte v2, v1, v6

    const/16 v2, 0x7a

    aput-byte v2, v1, v15

    const/16 v2, 0x5f

    aput-byte v2, v1, v18

    const/16 v2, 0x6e

    aput-byte v2, v1, v14

    const/16 v2, 0x6e

    aput-byte v2, v1, v11

    const/16 v2, 0x72

    aput-byte v2, v1, v17

    const/16 v2, 0x77

    aput-byte v2, v1, v16

    const/16 v2, 0x7d

    aput-byte v2, v1, v10

    const/16 v2, 0x7f

    aput-byte v2, v1, v12

    const/16 v2, 0x6a

    aput-byte v2, v1, v9

    const/16 v2, 0x77

    aput-byte v2, v1, v8

    const/16 v2, 0x71

    aput-byte v2, v1, v7

    const/16 v2, 0x70

    aput-byte v2, v1, v13

    const/4 v2, 0x0

    :goto_20e
    if-ge v2, v4, :cond_219

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_20e

    :cond_219
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_221
    const/16 v1, 0x12d

    if-ne v0, v1, :cond_2f3

    const/16 v1, 0x26

    new-array v2, v1, [B

    const/16 v24, 0x4c

    aput-byte v24, v2, v23

    const/16 v24, 0x43

    aput-byte v24, v2, v22

    const/16 v22, 0x49

    aput-byte v22, v2, v19

    const/16 v19, 0x5f

    aput-byte v19, v2, v21

    const/16 v19, 0x42

    aput-byte v19, v2, v6

    const/16 v6, 0x44

    aput-byte v6, v2, v15

    const/16 v6, 0x49

    aput-byte v6, v2, v18

    aput-byte v21, v2, v14

    const/16 v6, 0x4c

    aput-byte v6, v2, v11

    const/16 v6, 0x5d

    aput-byte v6, v2, v17

    const/16 v6, 0x5d

    aput-byte v6, v2, v16

    aput-byte v21, v2, v10

    const/16 v6, 0x6c

    aput-byte v6, v2, v12

    const/16 v6, 0x4e

    aput-byte v6, v2, v9

    const/16 v6, 0x59

    aput-byte v6, v2, v8

    const/16 v6, 0x44

    aput-byte v6, v2, v7

    const/16 v6, 0x5b

    aput-byte v6, v2, v13

    const/16 v6, 0x44

    aput-byte v6, v2, v4

    const/16 v4, 0x59

    aput-byte v4, v2, v3

    const/16 v3, 0x54

    aput-byte v3, v2, v5

    const/16 v3, 0x79

    const/16 v4, 0x14

    aput-byte v3, v2, v4

    const/16 v3, 0x45

    const/16 v4, 0x15

    aput-byte v3, v2, v4

    const/16 v3, 0x16

    const/16 v4, 0x5f

    aput-byte v4, v2, v3

    const/16 v3, 0x17

    const/16 v4, 0x48

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    const/16 v4, 0x4c

    aput-byte v4, v2, v3

    const/16 v3, 0x19

    const/16 v4, 0x49

    aput-byte v4, v2, v3

    const/16 v3, 0x1a

    aput-byte v17, v2, v3

    const/16 v3, 0x1b

    const/16 v4, 0x6c

    aput-byte v4, v2, v3

    const/16 v3, 0x1c

    const/16 v4, 0x5d

    aput-byte v4, v2, v3

    const/16 v3, 0x1d

    const/16 v4, 0x5d

    aput-byte v4, v2, v3

    const/16 v3, 0x1e

    const/16 v4, 0x6f

    aput-byte v4, v2, v3

    const/16 v3, 0x1f

    const/16 v4, 0x44

    aput-byte v4, v2, v3

    const/16 v3, 0x20

    const/16 v4, 0x43

    aput-byte v4, v2, v3

    const/16 v3, 0x21

    const/16 v4, 0x49

    aput-byte v4, v2, v3

    const/16 v3, 0x22

    const/16 v4, 0x69

    aput-byte v4, v2, v3

    const/16 v3, 0x23

    const/16 v4, 0x4c

    aput-byte v4, v2, v3

    const/16 v3, 0x24

    const/16 v4, 0x59

    aput-byte v4, v2, v3

    const/16 v3, 0x25

    const/16 v4, 0x4c

    aput-byte v4, v2, v3

    const/4 v3, 0x0

    :goto_2e0
    if-ge v3, v1, :cond_2eb

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_2e0

    :cond_2eb
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_2f3
    const/16 v1, 0x133

    if-ne v0, v1, :cond_31d

    new-array v1, v6, [B

    const/16 v2, 0x5a

    aput-byte v2, v1, v23

    const/16 v2, 0x5d

    aput-byte v2, v1, v22

    const/16 v2, 0x55

    aput-byte v2, v1, v19

    const/16 v2, 0x5c

    aput-byte v2, v1, v21

    const/4 v2, 0x0

    :goto_30a
    if-ge v2, v6, :cond_315

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_30a

    :cond_315
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_31d
    const/16 v1, 0x13c

    if-ne v0, v1, :cond_38d

    const/16 v1, 0x15

    new-array v2, v1, [B

    const/16 v1, 0x5d

    aput-byte v1, v2, v23

    const/16 v1, 0x52

    aput-byte v1, v2, v22

    const/16 v1, 0x58

    aput-byte v1, v2, v19

    const/16 v1, 0x4e

    aput-byte v1, v2, v21

    const/16 v1, 0x53

    aput-byte v1, v2, v6

    const/16 v1, 0x55

    aput-byte v1, v2, v15

    const/16 v1, 0x58

    aput-byte v1, v2, v18

    aput-byte v3, v2, v14

    const/16 v1, 0x5d

    aput-byte v1, v2, v11

    const/16 v1, 0x4c

    aput-byte v1, v2, v17

    const/16 v1, 0x4c

    aput-byte v1, v2, v16

    aput-byte v3, v2, v10

    const/16 v1, 0x70

    aput-byte v1, v2, v12

    const/16 v1, 0x53

    aput-byte v1, v2, v9

    const/16 v1, 0x5d

    aput-byte v1, v2, v8

    const/16 v1, 0x58

    aput-byte v1, v2, v7

    const/16 v1, 0x59

    aput-byte v1, v2, v13

    const/16 v1, 0x58

    aput-byte v1, v2, v4

    const/16 v1, 0x7d

    aput-byte v1, v2, v3

    const/16 v1, 0x4c

    aput-byte v1, v2, v5

    const/16 v1, 0x57

    const/16 v3, 0x14

    aput-byte v1, v2, v3

    const/4 v1, 0x0

    :goto_378
    const/16 v3, 0x15

    if-ge v1, v3, :cond_385

    aget-byte v3, v2, v1

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v2, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_378

    :cond_385
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_38d
    const/16 v1, 0x142

    if-ne v0, v1, :cond_3d5

    new-array v1, v12, [B

    const/16 v2, 0x2f

    aput-byte v2, v1, v23

    aput-byte v21, v1, v22

    const/16 v2, 0x32

    aput-byte v2, v1, v19

    const/16 v2, 0x32

    aput-byte v2, v1, v21

    const/16 v2, 0x2e

    aput-byte v2, v1, v6

    const/16 v2, 0x2b

    aput-byte v2, v1, v15

    const/16 v2, 0x21

    aput-byte v2, v1, v18

    const/16 v2, 0x23

    aput-byte v2, v1, v14

    const/16 v2, 0x36

    aput-byte v2, v1, v11

    const/16 v2, 0x2b

    aput-byte v2, v1, v17

    const/16 v2, 0x2d

    aput-byte v2, v1, v16

    const/16 v2, 0x2c

    aput-byte v2, v1, v10

    const/4 v2, 0x0

    :goto_3c2
    if-ge v2, v12, :cond_3cd

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_3c2

    :cond_3cd
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_3d5
    const/16 v1, 0x154

    if-ne v0, v1, :cond_463

    const/16 v1, 0x1a

    new-array v2, v1, [B

    const/16 v24, 0x35

    aput-byte v24, v2, v23

    const/16 v24, 0x3a

    aput-byte v24, v2, v22

    const/16 v22, 0x30

    aput-byte v22, v2, v19

    const/16 v19, 0x26

    aput-byte v19, v2, v21

    const/16 v19, 0x3b

    aput-byte v19, v2, v6

    const/16 v6, 0x3d

    aput-byte v6, v2, v15

    const/16 v6, 0x30

    aput-byte v6, v2, v18

    const/16 v6, 0x7a

    aput-byte v6, v2, v14

    const/16 v6, 0x35

    aput-byte v6, v2, v11

    const/16 v6, 0x24

    aput-byte v6, v2, v17

    const/16 v6, 0x24

    aput-byte v6, v2, v16

    const/16 v6, 0x7a

    aput-byte v6, v2, v10

    const/16 v6, 0x15

    aput-byte v6, v2, v12

    const/16 v6, 0x37

    aput-byte v6, v2, v9

    const/16 v6, 0x20

    aput-byte v6, v2, v8

    const/16 v6, 0x3d

    aput-byte v6, v2, v7

    const/16 v6, 0x22

    aput-byte v6, v2, v13

    const/16 v6, 0x3d

    aput-byte v6, v2, v4

    const/16 v4, 0x20

    aput-byte v4, v2, v3

    const/16 v3, 0x2d

    aput-byte v3, v2, v5

    const/16 v3, 0x14

    aput-byte v23, v2, v3

    const/16 v3, 0x3c

    const/16 v4, 0x15

    aput-byte v3, v2, v4

    const/16 v3, 0x16

    const/16 v4, 0x26

    aput-byte v4, v2, v3

    const/16 v3, 0x17

    const/16 v4, 0x31

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    const/16 v4, 0x35

    aput-byte v4, v2, v3

    const/16 v3, 0x19

    const/16 v4, 0x30

    aput-byte v4, v2, v3

    const/4 v3, 0x0

    :goto_450
    if-ge v3, v1, :cond_45b

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_450

    :cond_45b
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_463
    const/16 v1, 0x15a

    if-ne v0, v1, :cond_4c7

    new-array v1, v5, [B

    const/16 v2, 0x37

    aput-byte v2, v1, v23

    aput-byte v5, v1, v22

    const/16 v2, 0x34

    aput-byte v2, v1, v19

    const/16 v2, 0x33

    aput-byte v2, v1, v21

    const/16 v2, 0x2e

    aput-byte v2, v1, v6

    const/16 v2, 0x33

    aput-byte v2, v1, v15

    const/16 v2, 0x3b

    aput-byte v2, v1, v18

    const/16 v2, 0x36

    aput-byte v2, v1, v14

    const/16 v2, 0x1b

    aput-byte v2, v1, v11

    const/16 v2, 0x2a

    aput-byte v2, v1, v17

    const/16 v2, 0x2a

    aput-byte v2, v1, v16

    const/16 v2, 0x36

    aput-byte v2, v1, v10

    const/16 v2, 0x33

    aput-byte v2, v1, v12

    const/16 v2, 0x39

    aput-byte v2, v1, v9

    const/16 v2, 0x3b

    aput-byte v2, v1, v8

    const/16 v2, 0x2e

    aput-byte v2, v1, v7

    const/16 v2, 0x33

    aput-byte v2, v1, v13

    const/16 v2, 0x35

    aput-byte v2, v1, v4

    const/16 v2, 0x34

    aput-byte v2, v1, v3

    const/4 v2, 0x0

    :goto_4b4
    if-ge v2, v5, :cond_4bf

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_4b4

    :cond_4bf
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_4c7
    const/16 v1, 0x174

    if-ne v0, v1, :cond_53d

    const/16 v1, 0x1a

    new-array v2, v1, [B

    const/16 v20, 0x15

    aput-byte v20, v2, v23

    const/16 v24, 0x1a

    aput-byte v24, v2, v22

    aput-byte v13, v2, v19

    aput-byte v18, v2, v21

    const/16 v21, 0x1b

    aput-byte v21, v2, v6

    const/16 v21, 0x1d

    aput-byte v21, v2, v15

    aput-byte v13, v2, v18

    const/16 v15, 0x5a

    aput-byte v15, v2, v14

    const/16 v14, 0x15

    aput-byte v14, v2, v11

    aput-byte v6, v2, v17

    aput-byte v6, v2, v16

    const/16 v6, 0x5a

    aput-byte v6, v2, v10

    const/16 v6, 0x35

    aput-byte v6, v2, v12

    const/16 v6, 0x17

    aput-byte v6, v2, v9

    aput-byte v23, v2, v8

    const/16 v6, 0x1d

    aput-byte v6, v2, v7

    aput-byte v19, v2, v13

    const/16 v6, 0x1d

    aput-byte v6, v2, v4

    aput-byte v23, v2, v3

    aput-byte v9, v2, v5

    const/16 v3, 0x20

    const/16 v5, 0x14

    aput-byte v3, v2, v5

    const/16 v3, 0x1c

    const/16 v5, 0x15

    aput-byte v3, v2, v5

    const/16 v3, 0x16

    aput-byte v18, v2, v3

    const/16 v3, 0x17

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    aput-byte v5, v2, v3

    const/16 v3, 0x19

    aput-byte v13, v2, v3

    const/4 v3, 0x0

    :goto_52a
    if-ge v3, v1, :cond_535

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_52a

    :cond_535
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_53d
    const/16 v1, 0x17a

    if-ne v0, v1, :cond_58b

    new-array v1, v13, [B

    const/16 v2, 0x17

    aput-byte v2, v1, v23

    const/16 v2, 0x3b

    aput-byte v2, v1, v22

    const/16 v2, 0x16

    aput-byte v2, v1, v19

    const/16 v2, 0x16

    aput-byte v2, v1, v21

    const/16 v2, 0x3b

    aput-byte v2, v1, v6

    aput-byte v16, v1, v15

    aput-byte v16, v1, v18

    const/16 v2, 0x16

    aput-byte v2, v1, v14

    aput-byte v5, v1, v11

    const/16 v2, 0x19

    aput-byte v2, v1, v17

    const/16 v2, 0x1b

    aput-byte v2, v1, v16

    aput-byte v8, v1, v10

    aput-byte v5, v1, v12

    const/16 v2, 0x15

    aput-byte v2, v1, v9

    const/16 v2, 0x14

    aput-byte v2, v1, v8

    aput-byte v17, v1, v7

    const/4 v2, 0x0

    :goto_578
    if-ge v2, v13, :cond_583

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_578

    :cond_583
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_58b
    const/16 v1, 0x195

    if-ne v0, v1, :cond_5fa

    const/16 v1, 0x15

    new-array v2, v1, [B

    const/16 v1, -0xc

    aput-byte v1, v2, v23

    const/4 v1, -0x5

    aput-byte v1, v2, v22

    const/16 v1, -0xf

    aput-byte v1, v2, v19

    const/16 v1, -0x19

    aput-byte v1, v2, v21

    const/4 v1, -0x6

    aput-byte v1, v2, v6

    const/4 v1, -0x4

    aput-byte v1, v2, v15

    const/16 v1, -0xf

    aput-byte v1, v2, v18

    const/16 v1, -0x45

    aput-byte v1, v2, v14

    const/16 v1, -0xc

    aput-byte v1, v2, v11

    const/16 v1, -0x1b

    aput-byte v1, v2, v17

    const/16 v1, -0x1b

    aput-byte v1, v2, v16

    const/16 v1, -0x45

    aput-byte v1, v2, v10

    const/16 v1, -0x27

    aput-byte v1, v2, v12

    const/4 v1, -0x6

    aput-byte v1, v2, v9

    const/16 v1, -0xc

    aput-byte v1, v2, v8

    const/16 v1, -0xf

    aput-byte v1, v2, v7

    const/16 v1, -0x10

    aput-byte v1, v2, v13

    const/16 v1, -0xf

    aput-byte v1, v2, v4

    const/16 v1, -0x2c

    aput-byte v1, v2, v3

    const/16 v1, -0x1b

    aput-byte v1, v2, v5

    const/4 v1, -0x2

    const/16 v3, 0x14

    aput-byte v1, v2, v3

    const/4 v1, 0x0

    :goto_5e5
    const/16 v3, 0x15

    if-ge v1, v3, :cond_5f2

    aget-byte v3, v2, v1

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v2, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_5e5

    :cond_5f2
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_5fa
    const/16 v1, 0x19b

    if-ne v0, v1, :cond_651

    new-array v1, v13, [B

    const/16 v2, -0xa

    aput-byte v2, v1, v23

    const/16 v2, -0x26

    aput-byte v2, v1, v22

    const/16 v2, -0x15

    aput-byte v2, v1, v19

    const/16 v2, -0x15

    aput-byte v2, v1, v21

    const/16 v2, -0x9

    aput-byte v2, v1, v6

    const/16 v2, -0xe

    aput-byte v2, v1, v15

    const/4 v2, -0x8

    aput-byte v2, v1, v18

    const/4 v2, -0x6

    aput-byte v2, v1, v14

    const/16 v2, -0x11

    aput-byte v2, v1, v11

    const/16 v2, -0xe

    aput-byte v2, v1, v17

    const/16 v2, -0xc

    aput-byte v2, v1, v16

    const/16 v2, -0xb

    aput-byte v2, v1, v10

    const/16 v2, -0x2e

    aput-byte v2, v1, v12

    const/16 v2, -0xb

    aput-byte v2, v1, v9

    const/4 v2, -0x3

    aput-byte v2, v1, v8

    const/16 v2, -0xc

    aput-byte v2, v1, v7

    const/4 v2, 0x0

    :goto_63e
    if-ge v2, v13, :cond_649

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_63e

    :cond_649
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_651
    const/16 v1, 0x1ae

    if-ne v0, v1, :cond_728

    const/16 v1, 0x26

    new-array v2, v1, [B

    const/16 v24, -0x31

    aput-byte v24, v2, v23

    const/16 v24, -0x40

    aput-byte v24, v2, v22

    const/16 v22, -0x36

    aput-byte v22, v2, v19

    const/16 v19, -0x24

    aput-byte v19, v2, v21

    const/16 v19, -0x3f

    aput-byte v19, v2, v6

    const/16 v6, -0x39

    aput-byte v6, v2, v15

    const/16 v6, -0x36

    aput-byte v6, v2, v18

    const/16 v6, -0x80

    aput-byte v6, v2, v14

    const/16 v6, -0x31

    aput-byte v6, v2, v11

    const/16 v6, -0x22

    aput-byte v6, v2, v17

    const/16 v6, -0x22

    aput-byte v6, v2, v16

    const/16 v6, -0x80

    aput-byte v6, v2, v10

    const/16 v6, -0x11

    aput-byte v6, v2, v12

    const/16 v6, -0x33

    aput-byte v6, v2, v9

    const/16 v6, -0x26

    aput-byte v6, v2, v8

    const/16 v6, -0x39

    aput-byte v6, v2, v7

    const/16 v6, -0x28

    aput-byte v6, v2, v13

    const/16 v6, -0x39

    aput-byte v6, v2, v4

    const/16 v4, -0x26

    aput-byte v4, v2, v3

    const/16 v3, -0x29

    aput-byte v3, v2, v5

    const/4 v3, -0x6

    const/16 v4, 0x14

    aput-byte v3, v2, v4

    const/16 v3, -0x3a

    const/16 v4, 0x15

    aput-byte v3, v2, v4

    const/16 v3, 0x16

    const/16 v4, -0x24

    aput-byte v4, v2, v3

    const/16 v3, 0x17

    const/16 v4, -0x35

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    const/16 v4, -0x31

    aput-byte v4, v2, v3

    const/16 v3, 0x19

    const/16 v4, -0x36

    aput-byte v4, v2, v3

    const/16 v3, 0x1a

    const/16 v4, -0x76

    aput-byte v4, v2, v3

    const/16 v3, 0x1b

    const/16 v4, -0x11

    aput-byte v4, v2, v3

    const/16 v3, 0x1c

    const/16 v4, -0x22

    aput-byte v4, v2, v3

    const/16 v3, 0x1d

    const/16 v4, -0x22

    aput-byte v4, v2, v3

    const/16 v3, 0x1e

    const/16 v4, -0x14

    aput-byte v4, v2, v3

    const/16 v3, 0x1f

    const/16 v4, -0x39

    aput-byte v4, v2, v3

    const/16 v3, 0x20

    const/16 v4, -0x40

    aput-byte v4, v2, v3

    const/16 v3, 0x21

    const/16 v4, -0x36

    aput-byte v4, v2, v3

    const/16 v3, 0x22

    const/16 v4, -0x16

    aput-byte v4, v2, v3

    const/16 v3, 0x23

    const/16 v4, -0x31

    aput-byte v4, v2, v3

    const/16 v3, 0x24

    const/16 v4, -0x26

    aput-byte v4, v2, v3

    const/16 v3, 0x25

    const/16 v4, -0x31

    aput-byte v4, v2, v3

    const/4 v3, 0x0

    :goto_715
    if-ge v3, v1, :cond_720

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_715

    :cond_720
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_728
    const/16 v1, 0x1b4

    if-ne v0, v1, :cond_75d

    new-array v1, v14, [B

    const/16 v2, -0x2b

    aput-byte v2, v1, v23

    const/16 v2, -0x3c

    aput-byte v2, v1, v22

    const/16 v2, -0x3c

    aput-byte v2, v1, v19

    const/4 v2, -0x3

    aput-byte v2, v1, v21

    const/16 v2, -0x26

    aput-byte v2, v1, v6

    const/16 v2, -0x2e

    aput-byte v2, v1, v15

    const/16 v2, -0x25

    aput-byte v2, v1, v18

    const/4 v2, 0x0

    :goto_74a
    if-ge v2, v14, :cond_755

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_74a

    :cond_755
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_75d
    const/16 v1, 0x1d9

    if-ne v0, v1, :cond_7d1

    const/16 v1, 0x15

    new-array v2, v1, [B

    const/16 v1, -0x48

    aput-byte v1, v2, v23

    const/16 v1, -0x49

    aput-byte v1, v2, v22

    const/16 v1, -0x43

    aput-byte v1, v2, v19

    const/16 v1, -0x55

    aput-byte v1, v2, v21

    const/16 v1, -0x4a

    aput-byte v1, v2, v6

    const/16 v1, -0x50

    aput-byte v1, v2, v15

    const/16 v1, -0x43

    aput-byte v1, v2, v18

    const/16 v1, -0x9

    aput-byte v1, v2, v14

    const/16 v1, -0x48

    aput-byte v1, v2, v11

    const/16 v1, -0x57

    aput-byte v1, v2, v17

    const/16 v1, -0x57

    aput-byte v1, v2, v16

    const/16 v1, -0x9

    aput-byte v1, v2, v10

    const/16 v1, -0x6b

    aput-byte v1, v2, v12

    const/16 v1, -0x4a

    aput-byte v1, v2, v9

    const/16 v1, -0x48

    aput-byte v1, v2, v8

    const/16 v1, -0x43

    aput-byte v1, v2, v7

    const/16 v1, -0x44

    aput-byte v1, v2, v13

    const/16 v1, -0x43

    aput-byte v1, v2, v4

    const/16 v1, -0x68

    aput-byte v1, v2, v3

    const/16 v1, -0x57

    aput-byte v1, v2, v5

    const/16 v1, -0x4e

    const/16 v3, 0x14

    aput-byte v1, v2, v3

    const/4 v1, 0x0

    :goto_7bc
    const/16 v3, 0x15

    if-ge v1, v3, :cond_7c9

    aget-byte v3, v2, v1

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v2, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_7bc

    :cond_7c9
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_7d1
    const/16 v1, 0x1df

    if-ne v0, v1, :cond_827

    new-array v1, v7, [B

    const/16 v2, -0x4e

    aput-byte v2, v1, v23

    const/16 v2, -0x42

    aput-byte v2, v1, v22

    const/16 v2, -0x4c

    aput-byte v2, v1, v19

    const/16 v2, -0x46

    aput-byte v2, v1, v21

    const/16 v2, -0x62

    aput-byte v2, v1, v6

    const/16 v2, -0x51

    aput-byte v2, v1, v15

    const/16 v2, -0x51

    aput-byte v2, v1, v18

    const/16 v2, -0x4d

    aput-byte v2, v1, v14

    const/16 v2, -0x4a

    aput-byte v2, v1, v11

    const/16 v2, -0x44

    aput-byte v2, v1, v17

    const/16 v2, -0x42

    aput-byte v2, v1, v16

    const/16 v2, -0x55

    aput-byte v2, v1, v10

    const/16 v2, -0x4a

    aput-byte v2, v1, v12

    const/16 v2, -0x50

    aput-byte v2, v1, v9

    const/16 v2, -0x4f

    aput-byte v2, v1, v8

    const/4 v2, 0x0

    :goto_814
    if-ge v2, v7, :cond_81f

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_814

    :cond_81f
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_827
    const/16 v1, 0x1fe

    if-ne v0, v1, :cond_8b7

    const/16 v1, 0x1a

    new-array v2, v1, [B

    const/16 v24, -0x61

    aput-byte v24, v2, v23

    const/16 v24, -0x70

    aput-byte v24, v2, v22

    const/16 v22, -0x66

    aput-byte v22, v2, v19

    const/16 v19, -0x74

    aput-byte v19, v2, v21

    const/16 v19, -0x6f

    aput-byte v19, v2, v6

    const/16 v6, -0x69

    aput-byte v6, v2, v15

    const/16 v6, -0x66

    aput-byte v6, v2, v18

    const/16 v6, -0x30

    aput-byte v6, v2, v14

    const/16 v6, -0x61

    aput-byte v6, v2, v11

    const/16 v6, -0x72

    aput-byte v6, v2, v17

    const/16 v6, -0x72

    aput-byte v6, v2, v16

    const/16 v6, -0x30

    aput-byte v6, v2, v10

    const/16 v6, -0x41

    aput-byte v6, v2, v12

    const/16 v6, -0x63

    aput-byte v6, v2, v9

    const/16 v6, -0x76

    aput-byte v6, v2, v8

    const/16 v6, -0x69

    aput-byte v6, v2, v7

    const/16 v6, -0x78

    aput-byte v6, v2, v13

    const/16 v6, -0x69

    aput-byte v6, v2, v4

    const/16 v4, -0x76

    aput-byte v4, v2, v3

    const/16 v3, -0x79

    aput-byte v3, v2, v5

    const/16 v3, -0x56

    const/16 v4, 0x14

    aput-byte v3, v2, v4

    const/16 v3, -0x6a

    const/16 v4, 0x15

    aput-byte v3, v2, v4

    const/16 v3, 0x16

    const/16 v4, -0x74

    aput-byte v4, v2, v3

    const/16 v3, 0x17

    const/16 v4, -0x65

    aput-byte v4, v2, v3

    const/16 v3, 0x18

    const/16 v4, -0x61

    aput-byte v4, v2, v3

    const/16 v3, 0x19

    const/16 v4, -0x66

    aput-byte v4, v2, v3

    const/4 v3, 0x0

    :goto_8a4
    if-ge v3, v1, :cond_8af

    aget-byte v4, v2, v3

    xor-int/2addr v4, v0

    int-to-byte v4, v4

    aput-byte v4, v2, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_8a4

    :cond_8af
    new-instance v0, Ljava/lang/String;

    sget-object v1, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v2, v1}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    return-object v0

    :cond_8b7
    const/16 v1, 0x204

    if-ne v0, v1, :cond_91f

    new-array v1, v5, [B

    const/16 v2, 0x69

    aput-byte v2, v1, v23

    const/16 v2, 0x4d

    aput-byte v2, v1, v22

    const/16 v2, 0x6a

    aput-byte v2, v1, v19

    const/16 v2, 0x6d

    aput-byte v2, v1, v21

    const/16 v2, 0x70

    aput-byte v2, v1, v6

    const/16 v2, 0x6d

    aput-byte v2, v1, v15

    const/16 v2, 0x65

    aput-byte v2, v1, v18

    const/16 v2, 0x68

    aput-byte v2, v1, v14

    const/16 v2, 0x45

    aput-byte v2, v1, v11

    const/16 v2, 0x74

    aput-byte v2, v1, v17

    const/16 v2, 0x74

    aput-byte v2, v1, v16

    const/16 v2, 0x68

    aput-byte v2, v1, v10

    const/16 v2, 0x6d

    aput-byte v2, v1, v12

    const/16 v2, 0x67

    aput-byte v2, v1, v9

    const/16 v2, 0x65

    aput-byte v2, v1, v8

    const/16 v2, 0x70

    aput-byte v2, v1, v7

    const/16 v2, 0x6d

    aput-byte v2, v1, v13

    const/16 v2, 0x6b

    aput-byte v2, v1, v4

    const/16 v2, 0x6a

    aput-byte v2, v1, v3

    const/4 v2, 0x0

    :goto_90a
    if-ge v2, v5, :cond_915

    aget-byte v3, v1, v2

    xor-int/2addr v3, v0

    int-to-byte v3, v3

    aput-byte v3, v1, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_90a

    :cond_915
    new-instance v0, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v0, v1, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V
    :try_end_91c
    .catch Ljava/lang/Exception; {:try_start_35 .. :try_end_91c} :catch_91d

    return-object v0

    :catch_91d
    const/4 v0, 0x0

    return-object v0

    :cond_91f
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

    invoke-static {p0}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁪⁫/ﾠ⁪͏;->ﾠ͏(Landroid/content/Context;)V

    return-void
.end method

.method public onCreate()V
    .registers 3

    invoke-super {p0}, Landroid/app/Application;->onCreate()V

    new-instance v0, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫/ﾠ⁪͏;

    sget-object v1, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫⁪/ﾠ⁪͏;->ﾠ⁫:Ljava/lang/String;

    invoke-direct {v0, p0, v1}, Lﾠ⁪͏/ﾠ⁪͏/ﾠ⁪͏/ﾠ⁫/ﾠ⁪͏;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    const-string v0, "$APPLICATION"

    invoke-direct {p0, v0}, Lcom/mcal/apkprotector/ProtectApplication;->ﾠ⁪͏(Ljava/lang/String;)Landroid/app/Application;

    move-result-object v0

    if-eqz v0, :cond_15

    invoke-virtual {v0}, Landroid/app/Application;->onCreate()V

    :cond_15
    return-void
.end method
