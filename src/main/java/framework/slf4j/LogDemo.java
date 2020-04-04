package framework.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LogDemo {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LogDemo logDemo = applicationContext.getBean(LogDemo.class);
        /*logDemo.logger.error("--errorLevel--");
        logDemo.logger.info("--infoLevel--");
        logDemo.logger.debug("--debugLevel--");*/

        /*staticLogger2.error("--errorLevel--");
        staticLogger2.info("--infoLevel--");
        staticLogger2.debug("--debugLevel--");*/
        try {
            if (true) {
                throw new RuntimeException("我的错误");
            }
        } catch (Exception e) {
            logDemo.logger.error("11{},22{}", "aa", "bb", e);
        }


        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
    }
}
