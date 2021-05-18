#include <cstdlib>
#include <jni.h>
#include "ZipAlign.h"

using namespace android;

extern "C"
JNIEXPORT jboolean
JNICALL Java_com_mcal_apkprotector_zipalign_ZipAlign_process
(JNIEnv *env, jclass clazz, jstring inZipFile, jstring outZipFile, jint alignment, jboolean force, jboolean zopfli, jboolean pageAlignSharedLibs) {
    const char *inFileName = env->GetStringUTFChars(inZipFile, nullptr);
    const char *outFileName = env->GetStringUTFChars(outZipFile, nullptr);
    if(!inFileName || !outFileName) {
        return JNI_FALSE;
    }
    if (process(inFileName, outFileName, alignment, force, zopfli, pageAlignSharedLibs) == 0) return JNI_TRUE;
    return JNI_FALSE;
}

extern "C"
JNIEXPORT jboolean
JNICALL Java_com_mcal_apkprotector_zipalign_ZipAlign_verify
(JNIEnv *env, jclass clazz, jstring inZipFile, jint alignment, jboolean verbose, jboolean pageAlignSharedLibs) {
    const char *inFileName = env->GetStringUTFChars(inZipFile, nullptr);
    if(!inFileName) {
        return JNI_FALSE;
    }
    if(verify(inFileName, alignment, verbose, pageAlignSharedLibs) == 0) return JNI_TRUE;
    return JNI_FALSE;
}