package spring.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAnnotationHardler {

    @Pointcut("@annotation(spring.annotation.MyAnnotation)")
    public void myWork() {
        System.out.println(" my work");
    }

    @Before("myWork()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========doBefore()===============");
    }

    @Around("myWork()")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("==========doAround  start()===============");
        pjp.proceed();
        System.out.println("==========doAround end()===============");
    }

    @After("myWork()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("==========doAfter()===============");
    }
}
