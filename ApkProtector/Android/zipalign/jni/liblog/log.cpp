#include <errno.h>
#include <inttypes.h>
#include <libgen.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>

#ifdef __BIONIC__
#include <android/set_abort_message.h>
#endif

#include <atomic>

#include <android-base/errno_restorer.h>
#include <android-base/macros.h>
#include <private/android_filesystem_config.h>
#include <private/android_logger.h>

#include "android/log.h"
#include "log/log_read.h"
#include "logger.h"
#include "uio.h"

#ifdef __ANDROID__
#include "logd_writer.h"
#include "pmsg_writer.h"
#endif

int __android_log_print(int prio, const char* tag, const char* fmt, ...){
    return 0;
}

int __android_log_buf_print(int bufID, int prio, const char* tag,
                            const char* fmt, ...){
    return 0;
}
void __android_log_assert(const char* cond, const char* tag, const char* fmt,
                          ...){}
int __android_log_error_write(int tag, const char* subTag, int32_t uid,
                              const char* data, uint32_t dataLen){
    return 0;
}

void __android_log_default_aborter(const char *abort_message){}

void __android_log_set_logger(__android_logger_function logger) {
  
}
void __android_log_write_log_message(__android_log_message* log_message) {}

void __android_log_logd_logger(const struct __android_log_message* log_message) {}

void __android_log_stderr_logger(const struct __android_log_message* log_message) {}

void __android_log_set_aborter(__android_aborter_function aborter) {
 
}

void __android_log_call_aborter(const char* abort_message) {
  
}

int32_t __android_log_set_minimum_priority(int32_t priority) {
  return 0;
}

int32_t __android_log_get_minimum_priority() {
  return 0;
}

void __android_log_set_default_tag(const char* tag) {
  
}

int __android_log_is_loggable(int prio, const char* tag, int default_prio) {
  auto len = tag ? strlen(tag) : 0;
  return __android_log_is_loggable_len(prio, tag, len, default_prio);
}


int __android_log_is_loggable_len(int prio, const char*, size_t, int def) {
   return 0;
}