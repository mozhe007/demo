package spring.annotation.demo3xmlconfig;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation3 {
    String description() default "";
}
