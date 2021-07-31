package rt.java.lang;

public class IntegerDemo {


    void toStringDemo(){
        Integer integer = new Integer(5);
        String s1 = integer.toString(5,5);
        String s2 = integer.toString(5,4);
        System.out.println(s1); // 10
        System.out.println(s2);  //  11
    }

    // 可以获取汉字的 unicode 编码
    void toHexString(){
        String str = "编";
        StringBuffer sb = new StringBuffer();
        char [] source_char = str.toCharArray();
        String unicode = null;
        for (int i=0;i<source_char.length;i++) {
            unicode = Integer.toHexString(source_char[i]);
            if (unicode.length() <= 2) {
                unicode = "00" + unicode;
            }
            sb.append("\\u" + unicode);
        }
        System.out.println(sb);
    }

    void parseInt(){
        // 各种进制转为2进制
        System.out.println(Integer.parseInt("11", 2));
        System.out.println(Integer.parseInt("11", 10));
        System.out.println(Integer.parseInt("11", 16));
        // 16进制是0123456789Oabcdef
        System.out.println(Integer.parseInt("9", 16));
        System.out.println(Integer.parseInt("a", 16));
        System.out.println(Integer.parseInt("e", 16));
        System.out.println(Integer.parseInt("f", 16));
        System.out.println(Integer.parseInt("10", 16));
    }

    public static void main(String[] args) {
        IntegerDemo integerDemo = new IntegerDemo();
        integerDemo.toHexString();
    }
}
