package framework.guava;

import com.google.common.escape.Escapers;

public class MyEscaper {


        public static void main(String[] args) {

            Escapers.Builder builder = Escapers.builder();
            builder.addEscape('c',"b");
            builder.build().escape("abc");// abb
            System.out.println();

        }


}
