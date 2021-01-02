package jni;


// step1 java -d . JniDemo.java
// step2 生成java头文件 jni_JniDemo.h
// A  (到项目的class目录下D:\workspace\demo\target\classes执行)javah -jni jni.JniDemo
// B (在此文件目录java)javah -classpath D:\workspace\demo\target\classes jni.JniDemo
// step3 安装 gcc 64位上的 https://nchc.dl.sourceforge.net/project/mingw-w64/Toolchains%20targetting%20Win32/Personal%20Builds/mingw-builds/installer/mingw-w64-install.exe 添加环境变量

// step4 编写C语言原文件 HelloWorld.c
// step5 生成动态链接文件 hello.dll gcc -m64 -fpic -shared -I D:\Java\jdk8\include  -I D:\Java\jdk8\include\win32 HelloWorld.c -o hello.dll
//       -m64 是对应CPU64位
//      -I D:\Java\jdk8\include  的意思是HelloWorld.c中使用#include<jni.h>, gcc默认目录是"/usr/include"，如果使用#include<jni.h>则找不到jni.h文件，因此要通过“-I <dir>”参数来指定包含的头文件jni.h的位置。
//     -I D:\Java\jdk8\include\win32  的意思是jni_md.h

// step6 把 hello.dll 放到 java.library.path 里，可以用 System.getProperty("java.library.path") 查看

public class JniDemo{
    public native void helloWorld(); // 注意，这个native方法就是调用C语言接口用的
    static{
      // System.loadLibrary("hello");  // 这行是调用动态链接库
       System.load("D:\\workspace\\demo\\src\\main\\java\\jni\\hello.dll");
    }
    public static void main(String[] args){
        new JniDemo().helloWorld();
    }
}