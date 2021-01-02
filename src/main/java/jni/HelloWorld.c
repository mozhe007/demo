#include "jni.h"
#include "jni_JniDemo.h"
#include <stdio.h>
#include <stdlib.h>

JNIEXPORT void JNICALL Java_jni_JniDemo_helloWorld(JNIEnv * env, jobject obj)

        {

          printf("Hello World!\n");

        }