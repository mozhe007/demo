package spring.bean;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import spring.bean.name1.SameName;

@Component
public class MyBeanTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:applicationContext.xml"});
        applicationContext.setAllowBeanDefinitionOverriding(true);
        applicationContext.refresh();
        SameName sameName = (SameName)applicationContext.getBean("sameName");
        System.out.println(sameName.name);
    }

}