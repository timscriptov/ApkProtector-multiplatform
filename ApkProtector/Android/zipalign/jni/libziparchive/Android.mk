
LOCAL_PATH := $(my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := ziparchive_static
LOCAL_SRC_FILES := \
    zip_archive.cc \
    zip_archive_stream_entry.cc \
    zip_writer.cc \
    zip_cd_entry_map.cc \
    zip_error.cpp
LOCAL_C_INCLUDES += jni/include \
	$(LOCAL_PATH)/include \
	jni
LOCAL_CXXFLAGS := -D_FILE_OFFSET_BITS=64 -DZLIB_CONST
LOCAL_STATIC_LIBRARIES := base_static utils_static
include $(BUILD_STATIC_LIBRARY)


