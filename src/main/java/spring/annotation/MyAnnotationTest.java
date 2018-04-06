package spring.annotation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationTest {

    @MyAnnotation(description="des")
    public void foo(String str){
        System.out.println("thr str is "+ str);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:applicationContext.xml"});
        MyAnnotationTest userController = (MyAnnotationTest) applicationContext.getBean(MyAnnotationTest.class);
        userController.foo("james");
    }
}
