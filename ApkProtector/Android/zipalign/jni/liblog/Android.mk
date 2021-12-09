
LOCAL_PATH := $(my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := log_static
LOCAL_SRC_FILES := \
    log.cpp

LOCAL_C_INCLUDES += jni/include \
	$(LOCAL_PATH)/include \
	jni
include $(BUILD_STATIC_LIBRARY)


