#include <string>
#include <cstring>
#include "ApkProtect.h"
#include "log/android_log.h"
#include "utils/base64.h"
#include "utils/xxtea.h"
//static const char* TAG = "ApkProtect";

#ifdef __cplusplus
extern "C" {
#endif

//encrypt
JNIEXPORT jbyteArray JNICALL Java_com_mcal_dexprotect_utils_Native_a
  (JNIEnv *env, jclass clazz, jbyteArray data)
{
	// 把jbyteArray转换为byte*
	 auto isCopy = static_cast<jboolean>(false);
	 jbyte* dataBuff = env->GetByteArrayElements(data, &isCopy); //获取jbyteArray的数据，返回byte*
	 jsize dataSize = env->GetArrayLength(data);

    //LOGE(TAG,"data size %d",dataSize);

    // encrypt
    const char *key = d("FpebFmJ1FRe5FmTbGRDnGQ==").c_str();// 6717454797118328
	size_t len;
	auto* encrypt_data = (jbyte*)xxtea_encrypt(dataBuff, static_cast<size_t>(dataSize), key, &len);
	env->ReleaseByteArrayElements(data, dataBuff, JNI_ABORT); //JNI_ABORT表示不拷贝数据回jbyteArray

    //LOGE(TAG,"encrypt_data size %d",len);

    // char * to jbyteArray
    jbyteArray array = env->NewByteArray (len);
    env->SetByteArrayRegion (array, 0, len, encrypt_data);
    return array;
}

//decrypt
JNIEXPORT jbyteArray JNICALL Java_com_mcal_dexprotect_utils_Native_A
  (JNIEnv *env, jclass clazz, jbyteArray data)
{
	// jbyteArray to jbyte*
	auto isCopy = static_cast<jboolean>(false);
	jbyte* dataBuff = env->GetByteArrayElements(data, &isCopy);
    jsize dataSize = env->GetArrayLength(data);

    //LOGE(TAG,"data size %d",dataSize);

    // decrypt
    const char *key = d("FpebFmJ1FRe5FmTbGRDnGQ==").c_str();// 6717454797118328
	size_t len;
	auto* decrypt_data = (jbyte*)xxtea_decrypt(dataBuff, static_cast<size_t>(dataSize), key, &len);
	env->ReleaseByteArrayElements(data, dataBuff, JNI_ABORT); //JNI_ABORT表示不拷贝数据回jbyteArray

    //LOGE(TAG,"decrypt_data size %d",len);

	 // char * to jbyteArray
	 jbyteArray array = env->NewByteArray (len);
	 env->SetByteArrayRegion (array, 0, len, decrypt_data);
	 return array;
}

/*
JNIEXPORT jstring JNICALL Java_com_mcal_dexprotect_utils_Native_b
        (JNIEnv *env, jclass clazz) {
//    jclass clsstring = env->FindClass("java/lang/String");
//    jstring strencode = env->NewStringUTF("UTF-8");
//    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
//    jstring  keystr=env->NewStringUTF("wjshell");
//    jbyteArray key = (jbyteArray) env->CallObjectMethod(keystr, mid, strencode);
//    key=Java_com_qianfandu_utils_ApkToolPlus_encrypt(env, clazz, key);
    return env->NewStringUTF(d("NBWkeIPcrUCprQ==").c_str());// "apkprotect"
}
*/

#ifdef __cplusplus
}
#endif
