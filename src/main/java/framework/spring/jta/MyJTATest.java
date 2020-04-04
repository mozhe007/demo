package framework.spring.jta;

import framework.spring.annotation.MyAdviceTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyJTATest {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:applicationContext.xml"});
        MyAdviceTest userController = applicationContext.getBean(MyAdviceTest.class);
        userController.foo("james");
    }
}
