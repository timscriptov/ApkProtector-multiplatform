
LOCAL_PATH := $(my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := utils_static
LOCAL_SRC_FILES := \
	Errors.cpp \
        FileMap.cpp \
        JenkinsHash.cpp \
        NativeHandle.cpp \
        Printer.cpp \
        RefBase.cpp \
        SharedBuffer.cpp \
        StopWatch.cpp \
        String8.cpp \
        String16.cpp \
        StrongPointer.cpp \
        SystemClock.cpp \
        Threads.cpp \
        Timers.cpp \
        Tokenizer.cpp \
        Unicode.cpp \
        VectorImpl.cpp \
        misc.cpp \
    Looper.cpp
LOCAL_C_INCLUDES += jni/include \
	$(LOCAL_PATH)/include \
	jni
LOCAL_STATIC_LIBRARIES := log_static
include $(BUILD_STATIC_LIBRARY)


