package middleware.jms.Sender;


import middleware.jms.domain.User;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by James on 2017/5/30.
 */
public class MyQueueSender {

    //定义JNDI上下文工厂。
    public final static  String JNDI_FACTORY =  "weblogic.jndi.WLInitialContextFactory";

    //定义JNDI提供程序的URL。
    public final static  String PROVIDER_URL =  "t3://localhost:7001";

    //定义队列的JMS连接工厂。
    public final static  String CONNECTION_FACTORY_JNDI_NAME = "jndiConnectFactory";

    //定义队列，使用队列JNDI名称
    public final static  String QUEUE_JNDI_NAME =  "JMSQUECE";

    private QueueConnectionFactory qconFactory;
    private QueueConnection queueConnection;
    private QueueSession queueSession;
    private QueueSender queueSender;
    private Queue queue;
    private  ObjectMessage objectMessage;

    private static InitialContext getInitialContext()  throws  NamingException
    {
        Hashtable table =  new  Hashtable();
        table.put(Context.INITIAL_CONTEXT_FACTORY,JNDI_FACTORY);
        table.put(Context.PROVIDER_URL,PROVIDER_URL);
        InitialContext context =  new  InitialContext(table);
        return context;
    }

    public void init(Context ctx,String queueName) throws NamingException, JMSException {
        qconFactory =(QueueConnectionFactory)ctx.lookup(CONNECTION_FACTORY_JNDI_NAME);
        queueConnection = qconFactory.createQueueConnection();
        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = (Queue)ctx.lookup(queueName);
        queueSender = queueSession.createSender(queue);

        objectMessage = queueSession.createObjectMessage();
        queueConnection.start();
    }

    public void  send(String message)  throws  JMSException {
        // type5：set ObjectMessage
        User user =  new User();
        user.setName(message);
        objectMessage.setObject(user);
        queueSender.send(objectMessage);
    }

    public void  close()  throws  JMSException {
        queueSender.close();
        queueSession.close();
        queueConnection.close();
    }

    public static void  main(String [] args)  throws  Exception {
        InitialContext ctx = getInitialContext();
        MyQueueSender sender =  new  MyQueueSender();
        sender.init(ctx,QUEUE_JNDI_NAME);
        sender.send("狗熊歌4");
        sender.close();
    }
}
