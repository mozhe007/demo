package rt.java.management;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadMXBeanDemo {
    /*
输出结果：
below is thread info:
6: Monitor Ctrl-Break
5: Attach Listener
4: Signal Dispatcher
3: Finalizer
2: Reference Handler
1: main

*/
    public static void main(String[] args) {
        System.out.println("below is thread info:");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + ": " + threadInfo.getThreadName());
        }
    }
}
