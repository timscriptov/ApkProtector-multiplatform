
LOCAL_PATH := $(my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := zipalign_static

LOCAL_SRC_FILES := ZipAlign.cpp \
	ZipEntry.cpp \
	ZipFile.cpp

LOCAL_C_INCLUDES += $(LOCAL_PATH)/include \

LOCAL_STATIC_LIBRARIES := utils_static ziparchive_static zopfli_static
LOCAL_CFLAGS := -DSILENT -DRARDLL -I$(LOCAL_PATH)/rar -fexceptions -Os -ffunction-sections -fdata-sections -fvisibility=hidden -w -Wl,--gc-sections


include $(BUILD_STATIC_LIBRARY)

# ==========================================================
# Build the host executable: zipalign
# ==========================================================
include $(CLEAR_VARS)

LOCAL_MODULE := zipalign
LOCAL_SRC_FILES := Main.cpp
LOCAL_C_INCLUDES += $(LOCAL_PATH)/include

LOCAL_STATIC_LIBRARIES := zipalign_static
 
LOCAL_LDLIBS := -lz -llog

include $(BUILD_SHARED_LIBRARY)