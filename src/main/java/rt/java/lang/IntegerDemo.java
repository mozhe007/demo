package rt.java.lang;

public class IntegerDemo {

    void parseInt(){
        // 各种进制转为2进制
        System.out.println(Integer.parseInt("11", 2));
        System.out.println(Integer.parseInt("11", 10));
        System.out.println(Integer.parseInt("11", 16));
        // 16进制是0123456789abcdef
        System.out.println(Integer.parseInt("9", 16));
        System.out.println(Integer.parseInt("a", 16));
        System.out.println(Integer.parseInt("e", 16));
        System.out.println(Integer.parseInt("f", 16));
        System.out.println(Integer.parseInt("10", 16));
    }

    public static void main(String[] args) {
        IntegerDemo integerDemo = new IntegerDemo();
        integerDemo.parseInt();
    }
}
