package framework.guava;

import com.google.common.base.CharMatcher;

public class MyGuava {
    public static void main(String[] args) {
        CharMatcher charMatcher = new CharMatcher() {
            @Override
            public boolean matches(char c) {
                return false;
            }
        };
        charMatcher.any();
    }
}
