package com.mcal.apkprotector.async;

public interface ProtectAsyncListener {
    void onCompleted();

    void onProtected();

    void onFailed();

    void onProcess();
}
