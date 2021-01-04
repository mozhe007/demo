package rt.java.lang.instrument;

/*Instrumentation 的最大作用，就是类定义动态改变和操作。
程序运行时，通过 -javaagent 参数指定一个特定的 jar 文件来启动 
Instrumentation 的代理程序。 其实这个对很多人来说不陌生 xmind,
idea 永久破解的过程中，都有使用  -javaaegent ，
然后指定一个 jar 文件。甚至一些监控软件也用了，例如 skywalking。*/


/**
 * 操作步骤：
 * 1.编辑 resources/META-INF/MANIFEST.MF
 *          Manifest-Version: 1.0
 *          Premain-Class: rt.java.lang.instrument.Agent
 *          Can-Redefine-Classes: true
 * 2.打jar包, 执行plugins =>jar=>jar:jar, 会根据Pom.xml打出jar包
 * 3. 执行main方法时，增加VM参数 -javaagent:D:\workspace\demo\target\demo.jar=hello
 * console 打出 “
 *  I've been called with options: "hello"
 * wow wow~”
 *
 */
public class InstrumentationDemo {
    public String hello() {
        return "wow wow~";
    }

    public static void main(String[] args) {
        System.out.println(new InstrumentationDemo().hello());
    }
}
