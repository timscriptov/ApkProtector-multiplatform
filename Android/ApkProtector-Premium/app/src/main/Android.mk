LOCAL_PATH := $(my-dir)

# ==========================================================
# Build the host executable: base_static
# ==========================================================
base_SRC_FILES := base/abi_compatibility.cpp \
				base/chrono_utils.cpp \
        		base/cmsg.cpp \
        		base/file.cpp \
	   			base/logging.cpp \
        		base/mapped_file.cpp \
        		base/parsebool.cpp \
        		base/parsenetaddress.cpp \
        		base/process.cpp \
        		base/stringprintf.cpp \
        		base/strings.cpp \
        		base/threads.cpp \
        		base/test_utils.cpp

base_C_INCLUDES += jni/base/include \
				$(LOCAL_PATH)/include \
				jni

base_STATIC_LIBRARIES := log_static

# ==========================================================
# Build the host executable: cutils_static
# ==========================================================
libcutils_SRC_FILES := libcutils/android_get_control_file.cpp \
                	libcutils/socket_inaddr_any_server_unix.cpp \
                	libcutils/socket_local_client_unix.cpp \
                	libcutils/socket_local_server_unix.cpp \
                	libcutils/socket_network_client_unix.cpp \
                	libcutils/sockets_unix.cpp \
		        	libcutils/config_utils.cpp \
                	libcutils/canned_fs_config.cpp \
                	libcutils/iosched_policy.cpp \
                	libcutils/load_file.cpp \
                	libcutils/native_handle.cpp \
                	libcutils/record_stream.cpp \
                	libcutils/strlcpy.c

libcutils_STATIC_LIBRARIES := log_static

libcutils_C_INCLUDES += jni/libcutils/include \
					$(LOCAL_PATH)/include \
					jni

# ==========================================================
# Build the host executable: log_static
# ==========================================================
liblog_SRC_FILES := liblog/log.cpp

liblog_C_INCLUDES += jni/liblog/include \
					$(LOCAL_PATH)/include \
					jni

liblog_CFLAGS := -DHAVE_PTHREADS -Werror -Winvalid-noreturn

# ==========================================================
# Build the host executable: utils_static
# ==========================================================
libutils_SRC_FILES := libutils/Errors.cpp \
					libutils/FileMap.cpp \
           			libutils/JenkinsHash.cpp \
           			libutils/NativeHandle.cpp \
           			libutils/Printer.cpp \
           			libutils/RefBase.cpp \
           			libutils/SharedBuffer.cpp \
           			libutils/StopWatch.cpp \
           			libutils/String8.cpp \
           			libutils/String16.cpp \
           			libutils/StrongPointer.cpp \
           			libutils/SystemClock.cpp \
           			libutils/Threads.cpp \
           			libutils/Timers.cpp \
           			libutils/Tokenizer.cpp \
           			libutils/Unicode.cpp \
           			libutils/VectorImpl.cpp \
           			libutils/misc.cpp \
           			libutils/Looper.cpp

libutils_C_INCLUDES += jni/libutils/include \
					$(LOCAL_PATH)/include \
					jni

LOCAL_STATIC_LIBRARIES := log_static

# ==========================================================
# Build the host executable: ziparchive_static
# ==========================================================
libziparchive_SRC_FILES := libziparchive/zip_archive.cc \
    				libziparchive/zip_archive_stream_entry.cc \
    				libziparchive/zip_writer.cc

libziparchive_C_INCLUDES += jni/libziparchive/include \
					$(LOCAL_PATH)/include \
					jni

libziparchive_CXXFLAGS := -D_FILE_OFFSET_BITS=64 -DZLIB_CONST

libziparchive_STATIC_LIBRARIES := base_static utils_static

# ==========================================================
# Build the host executable: zopfli_static
# ==========================================================
zopfli_SRC_FILES :=  zopfli/blocksplitter.c \
        			zopfli/cache.c \
        			zopfli/deflate.c \
        			zopfli/gzip_container.c \
        			zopfli/hash.c \
        			zopfli/katajainen.c \
        			zopfli/lz77.c \
        			zopfli/squeeze.c \
        			zopfli/tree.c \
        			zopfli/util.c \
        			zopfli/zlib_container.c \
        			zopfli/zopfli_lib.c

zopfli_C_INCLUDES += jni/zopfli/include \
					$(LOCAL_PATH)/include \
					jni

zopfli_CFLAGS :=-Wno-unused-parameter -Werror

# ==========================================================
# Build the host executable: zipalign_static
# ==========================================================
zipalign_SRC_FILES := JNIMain.cpp \
                    zipalign/ZipAlign.cpp \
                    zipalign/ZipEntry.cpp \
                    zipalign/ZipFile.cpp

zipalign_C_INCLUDES += $(LOCAL_PATH)/zipalign/include \
                    $(LOCAL_PATH)/include \
                    jni

zipalign_STATIC_LIBRARIES := utils_static ziparchive_static zopfli_static

zipalign_CFLAGS := -Wno-format-y2k

#############################################################################
# put it all together
#############################################################################
include $(CLEAR_VARS)
LOCAL_MODULE:= libzipalign-cmd

LOCAL_SRC_FILES += $(base_SRC_FILES)
LOCAL_SRC_FILES += $(libcutils_SRC_FILES)
LOCAL_SRC_FILES += $(liblog_SRC_FILES)
LOCAL_SRC_FILES += $(libutils_SRC_FILES)
LOCAL_SRC_FILES += $(libziparchive_SRC_FILES)
LOCAL_SRC_FILES += $(zopfli_SRC_FILES)
LOCAL_SRC_FILES += $(zipalign_SRC_FILES)

LOCAL_C_INCLUDES += $(base_C_INCLUDES)
LOCAL_C_INCLUDES += $(libcutils_C_INCLUDES)
LOCAL_C_INCLUDES += $(liblog_C_INCLUDES)
LOCAL_C_INCLUDES += $(libutils_C_INCLUDES)
LOCAL_C_INCLUDES += $(libziparchive_C_INCLUDES)
LOCAL_C_INCLUDES += $(zopfli_C_INCLUDES)
LOCAL_C_INCLUDES += $(zipalign_C_INCLUDES)

LOCAL_CXXFLAGS+= $(libziparchive_CXXFLAGS)

LOCAL_CFLAGS += $(liblog_CFLAGS)
LOCAL_CFLAGS += $(zopfli_CFLAGS)
LOCAL_CFLAGS += $(zipalign_CFLAGS)

LOCAL_LDLIBS += -lz -llog

include $(BUILD_SHARED_LIBRARY)