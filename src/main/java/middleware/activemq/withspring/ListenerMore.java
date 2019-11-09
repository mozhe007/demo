package middleware.activemq.withspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ListenerMore implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        System.out.println(msg);
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"classpath*:spring-activeMQ.xml"});
        DefaultMessageListenerContainer consumer =
                (DefaultMessageListenerContainer)context.getBean("consumer");
        consumer.start();
    }
}
