package com.mcal.dexprotect.async;

public interface ProtectAsyncListener {
    void onCompleted();

    void onProtected();

    void onFailed();

    void onProcess();
}
