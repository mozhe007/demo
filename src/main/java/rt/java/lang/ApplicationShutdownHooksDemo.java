package rt.java.lang;

public class ApplicationShutdownHooksDemo {

    // ApplicationShutdownHooks 没有public 修饰，只能通过 Runtime#addShutdownHook 操作
    public void demo(){
        System.out.println(1);
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.out.println("操作合并推出逻辑");

            }
        });
        System.out.println(2);
    }

    public static void main(String[] args) {


    }
}
