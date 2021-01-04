package rt.java.lang.annotation;

import java.lang.annotation.Native;

/*注释 @Native 和 关键字 native 不是同一个东西，
        注释 @Native 表示成员变量是和原生语言有关联
Indicates that a field defining a constant value may be referenced
from native code.
@Integer MIN_VALUE=0x80000000;
*/

public class NativeDemo {
    @Native
    private String foo;
}
