package rt.java.annotation;


import java.lang.annotation.Documented;

/**
 *
 * 由所有批注类型扩展的公共接口。请注意
 *
 * *手动扩展此接口的接口不定义
 *
 * *注释类型。还要注意，这个接口本身并不是
 *
 * *定义批注类型。
 *
 * 模拟一个注解
 */
public @interface AnnotationDemo  {

}




/**
 * 测试 class.isAnnotation方法 ,因为 interface 下不能写  public static void main
 *
 */
class test{

    public static void main(String[] args) {
        System.out.println(AnnotationDemo.class.isAnnotation());
    }
}
