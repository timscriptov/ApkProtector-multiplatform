LOCAL_PATH := $(my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := base_static
LOCAL_SRC_FILES := \
    abi_compatibility.cpp \
        chrono_utils.cpp \
        cmsg.cpp \
        file.cpp \
	logging.cpp \
        mapped_file.cpp \
        parsebool.cpp \
        parsenetaddress.cpp \
        process.cpp \
        stringprintf.cpp \
        strings.cpp \
        threads.cpp \
        test_utils.cpp

LOCAL_C_INCLUDES += jni/include \
	$(LOCAL_PATH)/include \
	jni

LOCAL_STATIC_LIBRARIES := log_static
include $(BUILD_STATIC_LIBRARY)


