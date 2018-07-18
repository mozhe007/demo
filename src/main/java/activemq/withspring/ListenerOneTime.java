package activemq.withspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

public class ListenerOneTime {
    public void listen(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:spring-activeMQ.xml"});
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("destination");
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        System.out.println(textMessage);
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ListenerOneTime listenerOneTime = new ListenerOneTime();
        listenerOneTime.listen();
    }
}
