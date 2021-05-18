
LOCAL_PATH := $(my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := cutils_static
LOCAL_SRC_FILES := \
     android_get_control_file.cpp \
                 socket_inaddr_any_server_unix.cpp \
                 socket_local_client_unix.cpp \
                 socket_local_server_unix.cpp \
                 socket_network_client_unix.cpp \
                 sockets_unix.cpp \
		 config_utils.cpp \
         canned_fs_config.cpp \
         iosched_policy.cpp \
         load_file.cpp \
         native_handle.cpp \
         record_stream.cpp \
         strlcpy.c \
         


LOCAL_STATIC_LIBRARIES := log_static
LOCAL_C_INCLUDES += jni/include \
	$(LOCAL_PATH)/include \
	jni
include $(BUILD_STATIC_LIBRARY)

