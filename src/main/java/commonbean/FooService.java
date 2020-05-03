package commonbean;

/**
 * 普通的Service
 */
public class FooService {

    public String foo(String string) {
        return string + "hello world";
    }

    public String foo(int i) {
        return i + "hello world";
    }

    public String foo() {
        return  "hello world";
    }

}
