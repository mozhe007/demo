package middleware.activemq.withspring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class Publisher {

    public void sender() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:spring-activeMQ.xml"});
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("destination");
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("测试语句 ");
            }
        });
    }

    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        publisher.sender();
    }
}
