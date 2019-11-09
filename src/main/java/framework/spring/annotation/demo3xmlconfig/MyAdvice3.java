package framework.spring.annotation.demo3xmlconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAdvice3 {

    public void doBefore(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        System.out.println(params[0]);
        System.out.println("==========doBefore()===============");
    }

    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("==========doAround start()===============");
        Object[] params = pjp.getArgs();
        System.out.println(params[0]);
        pjp.proceed();
        System.out.println("==========doAround end()===============");
    }

    public void doAfter(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        System.out.println(params[0]);
        System.out.println("==========doAfter()===============");
    }


}
