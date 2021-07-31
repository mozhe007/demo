package rt.java.lang;

public class ByteDemo {

    public void equalsTest(Object object){
        byte a = 127;
        Byte byte1 = new Byte(a);

        // 强转1
        boolean isEqulas = byte1.equals("ssdf");
        System.out.println(isEqulas);
        // 强转2
        boolean isEqulas2 = byte1.equals(object);
        System.out.println(isEqulas2);


        // compare
        byte b1 = 1;
        byte b2 = 5;
        System.out.println(Byte.compare(b1,b2));

        // toUnsignedInt
        System.out.println(Byte.toUnsignedInt(b1));

    }

    public static void main(String[] args) {
        ByteDemo byteDemo = new ByteDemo();
        byteDemo.equalsTest(new String("23"));
    }
}
