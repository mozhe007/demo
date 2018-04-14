package spring.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice {

    /* annotation is only for method */
    @Pointcut("@annotation(spring.annotation.MyAnnotation)")
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