package rt.java.reflect;

import java.lang.reflect.Array;

public class ArrayDemo {

    public static void main(String[] args) {
        Object array = Array.newInstance(String.class,10);
        Array.set(array, 4, "hello");
        //取出array中的第五个元素
        String value = (String)Array.get(array, 4);
        System.out.println(value);


        // setXXX  / getXXX
        Object intArray = Array.newInstance(int.class,10);
        Array.setInt(intArray, 4, 530);
        //取出array中的第五个元素
        int intV = Array.getInt(intArray, 4);
        System.out.println(intV);

    }

}
