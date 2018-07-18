package spring.annotation.demo2forclass;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@MyAnnotation2()
public class MyAdvice2Test {

    public void foo1(String str) {
        System.out.println("thr str is " + str);
    }

    public void foo2(String str) {
        System.out.println("thr str is " + str);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:applicationContext.xml"});
        MyAdvice2Test userController = applicationContext.getBean(MyAdvice2Test.class);
        userController.foo1("james");
        userController.foo2("lin");
    }
}
