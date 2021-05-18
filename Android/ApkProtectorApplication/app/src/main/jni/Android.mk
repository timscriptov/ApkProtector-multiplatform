LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := apkprotect

LOCAL_C_INCLUDES := $(LOCAL_PATH)

LOCAL_SRC_FILES := ApkProtect.cpp \
                   utils/base64.cpp \
                   utils/xxtea.c

LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)
