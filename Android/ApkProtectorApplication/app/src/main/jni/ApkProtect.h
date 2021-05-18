#include <jni.h>

#ifndef com_mcal_dexprotect_utils_Native_h
#define com_mcal_dexprotect_utils_Native_h
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_mcal_dexprotect_utils_Native
 * Method:    encrypt
 * Signature: ([BI)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_mcal_dexprotect_utils_Native_a
  (JNIEnv *, jclass, jbyteArray);

/*
 * Class:     com_mcal_dexprotect_utils_Native
 * Method:    decrypt
 * Signature: ([BI)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_mcal_dexprotect_utils_Native_A
  (JNIEnv *, jclass, jbyteArray);

#ifdef __cplusplus
}
#endif
#endif
