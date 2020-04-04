package framework.spring.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class MyAdvice {

    /* annotation is only for method */
    @Pointcut("@annotation(framework.spring.annotation.MyAnnotation)")
    public void userAnnotation() {

    }

    @Before("userAnnotation()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        System.out.println(params[0]);
        System.out.println("==========doBefore()===============");
    }

    @Around("userAnnotation()")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("==========doAround start()===============");
        Object[] params = pjp.getArgs();
        System.out.println(params[0]);
        pjp.proceed();
        System.out.println("==========doAround end()===============");
    }

    @After("userAnnotation()")
    public void doAfter(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        System.out.println(params[0]);
        System.out.println("==========doAfter()===============");
    }

}
