package rt.java.lang;

public class BooleanDemo {
    public static void main(String[] args) {
        boolean t= true;
        boolean f = false;
        System.out.println(Boolean.logicalAnd(t,f)); // 且
        System.out.println(Boolean.logicalOr(t,f));  // 或
        System.out.println(Boolean.logicalXor(t,f)); // 异或
    }
}
