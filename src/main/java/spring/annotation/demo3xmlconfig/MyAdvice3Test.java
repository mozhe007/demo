package spring.annotation.demo3xmlconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyAdvice3Test {

    @MyAnnotation3(description = "des")
    public void foo(String str) {
        System.out.println("thr str is " + str);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:applicationContext.xml"});
        MyAdvice3Test userController = (MyAdvice3Test) applicationContext.getBean(MyAdvice3Test.class);
        userController.foo("james");
    }
    
}