package framework.spring.annotation.demo2forclass;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation2 {
    String description() default "";
}