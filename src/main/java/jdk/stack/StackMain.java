package jdk.stack;

/*
* 利用
*
* */
public class StackMain {

    public static void main(String[] args) {
        StackMain stackMain = new StackMain();
        stackMain.s1();
    }

    private void s1() {
        System.out.println(1);
        s2();
    }

    private void s2() {
        System.out.println(2);
        s3();
    }

    private void s3() {
        System.out.println(3);
    }
}
