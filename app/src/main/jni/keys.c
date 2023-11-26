#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_movieapp_MovieApp_getApiKey(JNIEnv *env, jobject instance) {

    return (*env)-> NewStringUTF(env, "9cf88a8f");

}