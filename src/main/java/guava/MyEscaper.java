package guava;

import com.google.common.escape.CharEscaper;
import com.google.common.escape.Escapers;
import com.google.common.html.HtmlEscapers;
import org.apache.commons.lang3.StringEscapeUtils;

public class MyEscaper {


        public static void main(String[] args) {

            Escapers.Builder builder = Escapers.builder();
            builder.addEscape('c',"b");
            builder.build().escape("abc");// abb
            System.out.println();

        }


}
