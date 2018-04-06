package spring.annotation.demo2forclass;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/* the advise for class and method  */
@Aspect
@Component
public class MyAdvice2 {

    /* 对于 execution 表达式 * *.*(..) 第一个*为返回值，第2个*为类，第三个*为方法，（..）代表任意参数 */
    @Pointcut("execution(* spring.annotation.demo2forclass.MyAdvice2Test.*(..))")
    public void useExecution() {

    }

    @Before("useExecution()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========doBefore()===============");
    }

    @Around("useExecution()")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("==========doAround() start===============");
        pjp.proceed();
        System.out.println("==========doAround() end ===============");
    }

    @After("useExecution()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("==========doAfter()===============");
    }

}
