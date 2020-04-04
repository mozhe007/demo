package rt.java.lang;

public class StackTraceElementDemo {


    public static void demo(){
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("index: ").append(i).append(" ClassName: ").append(elements[i].getClassName())
                    .append(" Method Name : " + elements[i].getMethodName());
            System.out.println(buffer.toString());
        }
        System.out.println("eat");
    }


    public static void main(String[] args) {
        StackTraceElementDemo.demo();
    }
}
