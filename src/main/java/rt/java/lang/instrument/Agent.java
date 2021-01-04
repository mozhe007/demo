package rt.java.lang.instrument;

import java.lang.instrument.Instrumentation;

public class Agent {
    /**
     * 编写一个 Java 类 包含
     * public static void premain(String agentArgs, Instrumentation inst);  [1]
     * public static void premain(String agentArgs); [2]
     * @param options
     * @param ins
     */
    public static void premain(String options, Instrumentation ins) {
        if (options != null) {
            System.out.printf("  I've been called with options: \"%s\"\n", options);
        }
        else {
            System.out.println("  I've been called with no options.");
        }
        ins.addTransformer(new ClassFileTransformerDemo());
    }

}
