package rt.java.lang.annotation;

import java.lang.annotation.Repeatable;


/**
 *  有种情况, 一个类可能拥有同样的 有个注解,
 *  表明会多次使用同一个注解里,但不同的值, 重复使用 注解需要使用 @Repeatable
 *  需要配合2个注解,相互引用对面.
 *
 *  比如一个人, 可能有多个角色 , "父亲","儿子","医生","乘客" ,那么
 *   @角色( "父亲")
 *   @角色( "儿子")
 *   @角色( "医生")
 * @
 *
 */
public @interface RepeatableDemo {
}

@interface WithRepeatableS {
    WithRepeatable[] value();
}

@interface NoRepeatableS {
    NoRepeatable[] value();
}
@Repeatable(WithRepeatableS.class) // 必须在 WithRepeatableS 里用WithRepeatable 的默认引用
@interface WithRepeatable{
    String[] value() default {};
}

@interface NoRepeatable{
    String[] value() default {};
}

@WithRepeatable("1")
@WithRepeatable("2")
@NoRepeatable("1")
//@NoRepeatable("2")  差别在这里
class Foo{
}

