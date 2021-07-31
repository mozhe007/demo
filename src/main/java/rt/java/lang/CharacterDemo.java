package rt.java.lang;

/**
 * 要知道codepoint就得先知道unicode字符集，unicode中的每个字符对应一个codepoint。Unicode就相当于一本字典，
 * 其中的每个字符都存在一个编号，即为代码点codepoint。java中的一个char类型含有两个字节，即16位，只能表示65536种字符，
 * 但是Unicode中的字符远不止65536个，这个时候就需要两个char才能表示一个Unicode字符，
 * 这时候codepoint就是对应这两个char表示的一个Unicode字符，如果需要取这个占两个char的Unicode字符，
 * 就不可以用charAt来取了，要用codePointAt了。即charAt函数是站在char的角度看待字符串，
 * 而codePointAt是站在unicode的角度看待字符串。
 * <p>
 * 65535 = 16*4096 OxFFFF
 */

/**
 * 65535 = 16*4096 OxFFFF
 */

/**
 * 一个字节 8 byte
 *
 * unicode  4个字节（utf-8,utf-16,utf-32）
 * Java 里char占2个字节 
 * 在Unicode 3.0里使用“U-”然后紧接着八位数，而“U+”则必须随后紧接着四位数。
 *
 * 目前的Unicode字符分为17组编排，0x0000至0x10FFFF
 *
 * 高位替代就是指这个范围的码位是两个WORD的UTF-16编码的第一个WORD。低位替代就是指这个范围的码位是两个WORD的UTF-16编码的第二个WORD。
 *
 *
 *     //簡而言之，Java 語言內部的字符信息是使用 UTF-16 編碼。因為，char 這個類型是 16-bit 的。
 *     // 它可以有65536種取值，即65536個編號，每個編號可以代表1種字符。但是，Unicode
 *     // 包含的字符已經遠遠超過65536個。那，編號大於65536的，還要用 16-bit 編碼，
 *     // 該怎麼辦？於是，Unicode 標準制定組想出的辦法就是，從這65536個編號裏，拿出2048個，
 *     // 規定它們是「Surrogates」，讓它們兩個為一組，來代表編號大於65536的那些字符。更具體地，
 *     // 編號為 U+D800 至 U+DBFF 的規定為「High Surrogates」，共1024個。編號為 U+DC00 至 U+DFFF
 *     // 的規定為「Low Surrogates」，也是1024個。它們兩兩組合出現，就又可以多表示1048576種字符。
 *     //
 *
 *     帮助测试的网址 https://unicode-table.com/cn/
 */
public class CharacterDemo {

    /**有效的 Unicode 代码点 ,准确算出指定字符(Unicode代码点)是否在补充字符范围内。
     *
     *  2 是补充字符， 范围大于等于10000 -->A Unicode code point between U+10000 and U+10FFFF.
     *  1 是非补充字符， 范围 1-9999
     * 因为 unicode编码是 \u0001-\u9999 ， 如果大于等于， 0x10000 ，则说明一个char unit 表示不了，返回2.
     *
     */
    public void charCount() {
        int charCount1 = Character.charCount(0x010000);
        int charCount2 = Character.charCount(0x009999);
        System.out.println(charCount1); // 2
        System.out.println(charCount2); // 1
    }

    // value 是final修饰的
    public void charValue() {
        Character character = 'a';
        //debug;
        System.out.println(character);
        character = 'b';  // 这一会修改内存地址
        System.out.println(character);
    }

    // 代码点（Code Point）就是指Unicode中为字符分配的编号
    // unicode的范围从000000 - 10FFFF

    // 逆方法参考 Character.toChars
    public void codePointAt() {
        char[] zhong = {'汉'};
        int codePoint = Character.codePointAt(zhong, 0); // 27721
        String hexString = Integer.toHexString(codePoint); // 6c49
        System.out.println(codePoint);
        System.out.println(hexString);

        String hexString1 = Integer.toHexString(65536); // 6c49
        System.out.println(hexString1);
    }

    /**
     * 这傻逼方法多此一举，就是 codePointAtImpl
     */
    public void codePointBefore() {
        char[] zhong = {'我', '爱', '汉', '字'};
        int codePoint = Character.codePointBefore(zhong, 3); // 27721
        System.out.println(codePoint);

        String string = "我爱汉字";
        codePoint = Character.codePointBefore(string, 3); // 27721
        System.out.println(codePoint);
    }

    /**
     * 就是返回一段字符串中  Unicode code points  的数量。
     *
     * a1 就是3个中文
     * a2 就是4个中文
     * a3 里面的 \ud800 是 high  Surrogate  \uDC00是 low Surrogate ，他们2个算一个codePoint
     */
    public void codePointCount() {
        String string1 = "我爱汉字";
        int a1 = Character.codePointCount(string1, 0, 3);
        System.out.println(a1); // 3  string1 没有每个字符都是独立的codePoint,  n=3-0=3

        int a2 = Character.codePointCount(string1,0,4);
        System.out.println(a2); // 4        / string1 没有每个字符都是独立的codePoint,  n=4-0=4

        String string2 = "'\uD800\uDC00我汉字"; //  string1 的 \ud800 是 high  Surrogate  \uDC00是 low Surrogate ，他们2个算一个codePoint
        int a3 = Character.codePointCount(string2, 0, 5);  // 5-0-1=4
        System.out.println(a3); // 4

    }

    /**
     * 和codePointCount 类似，返回指定个个codePoint的坐标
     */
    public void offsetByCodePoints() {
        String string1 = "11我爱汉字";
        int a1 = Character.offsetByCodePoints(string1, 0, 3);
        System.out.println(a1); // 3  string1 没有每个字符都是独立的codePoint,  n=3-0=3

        int a2 = Character.offsetByCodePoints(string1,0,4);
        System.out.println(a2); // 4        / string1 没有每个字符都是独立的codePoint,  n=4-0=4

        String string2 = "'\uD800\uDC00我汉字"; //  string1 的 \ud800 是 high  Surrogate  \uDC00是 low Surrogate ，他们2个算一个codePoint
        int a3 = Character.offsetByCodePoints(string2, 0, 3);  // 5-0-1=4
        System.out.println(a3); // 6
    }

    /**
     * 方法内部还是挺复杂的
     *
     * 第一步，初始化 String A_DATA  变成char[]
     * 第二步, 从已经初始化的 props 数组里取得对应的props
     * 第三步, props是32位的数据，其中最后5位表示类型，用 props & 0x1F 来获取类型
     * 第四步, 判断类型是否是小写 getType(codePoint) == Character.LOWERCASE_LETTER
     */
    public void isLowerCase(){
        char a = 'a';
        char A = 'A';
        char z = '中';
        boolean b1 = Character.isLowerCase(a);
        boolean b2 = Character.isLowerCase(A);
        boolean b3 = Character.isLowerCase(z);
        System.out.println(b1); // true
        System.out.println(b2); // false
        System.out.println(b3); // false
    }

    // isUpperCase 省略，和 isLowerCase 类似

    /**
     * 目前看这个方法挺小众的，针对个别拉丁字母
     * 下面给出了一些此方法返回true的Unicode字符：
     * 带有小写字母Z的拉丁文大写字母D带仰抑符(WITH CARON)
     * 带有小写字母J的拉丁文大写字母L
     * 带有小写字母J的拉丁文大写字母N,
     * 带有小写字母Z的拉丁文大写字母D, 当然还有其他字母。
     *
     * 这些字符可能被用作书名。
     */
    public void isTitleCase(){
        char a = 'a';
        char A = 'A';
        char DZ = 'ǅ';// \u01C5
        char lj = 'ǈ';// \u01C8
        char nj = 'ǋ';// \u01CB
        char Dz = 'ǲ';// \u01f2
        boolean b1 = Character.isTitleCase(a);
        boolean b2 = Character.isTitleCase(A);
        boolean b3 = Character.isTitleCase(DZ);
        boolean b4 = Character.isTitleCase(lj);
        boolean b5 = Character.isTitleCase(nj);
        boolean b6 = Character.isTitleCase(Dz);
        System.out.println(b1); // false
        System.out.println(b2); // false
        System.out.println(b3); // true
        System.out.println(b4); // true
        System.out.println(b5); // true
        System.out.println(b6); // true
    }

    /**
     * 很简单，是不是数字
     */
    public void isDigit() {
        char d1 = '9';
        char d2 = '٩'; //٩ 阿拉伯-印度文数字九  \u0669
        char d3 = '۹'; //۹ 扩充阿拉伯-印度文数字九 \u06F9
        char d4 = '९';// 梵文 9
        char d5 = '９';// 梵文 9
        boolean b1 = Character.isDigit(d1);
        boolean b2 = Character.isDigit(d2);
        boolean b3 = Character.isDigit(d3);
        boolean b4 = Character.isDigit(d4);
        boolean b5 = Character.isDigit(d5);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);
    }

    /**
     * 是否被定义 ，一般认知下的字符都会返回true， 想要返回false,自己一定一个超出 编码范围的就可以
     */
    public void isDefined(){
        int cp = 0x10000;//
        int cp1 = 0xffff;//
        boolean b1 = Character.isDefined(cp);
        boolean b2 = Character.isDefined(cp1);
        System.out.println(b1);
        System.out.println(b2);
    }

    /**
     * 把 char 变成 int
     *CharacterData.of()
     * 先判断 ch >>> 8 == 0 ，说明是单字节            >>>（无符号右移）
     *  得到对应的CharacterData  CharacterDataLatin1.instance;
     *  getType 又CharacterData由各子类实现。
     *
     *  获取属性 getProperties   获取数组下标
     *  props 8486882
     * a = 97 10进制=8486882  2进制=100000010111111111100010
     * A = 65 10进制=8552417  2进制=100000100111111111100001
     * B = 66 10进制=8552417  2进制=100000100111111111100001
     *
     *  props & 0x1F 和 10进制=31 16进制=0x1F  2进制=11111
     *
     *  a 的结果是2，即小写。  A的结果是1，即大写。
     *  public static final byte UPPERCASE_LETTER = 1;
     *  public static final byte LOWERCASE_LETTER = 2;
     *
     *  根据和 props 和不同的数字 & 操作，得到相关属性。
     */
    public void getType(){
        char a = 'a';
        char A = 'A';
        char B = 'B';
        int b1 = Character.getType(a);
        int b2 = Character.getType(A);
        int b3 = Character.getType(B);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

    // 这个方法对参数要求很高
    // char ch 必须是0-9 a-z ,A-Z中的
    // int radix（进制） 必须是 2-36的
    // radix（进制）作为模数， 为必须大于ch对应的数字
    public void digit() {
        char a = 'a';
        int ia10 = Character.digit(a, 10);
        int ia11 = Character.digit(a, 11);
        int iaMax = Character.digit(a, Character.MAX_RADIX);
        System.out.println(ia10); // -1 ，因为 0123456789abc.. a拍第一个，radix 必须>=11
        System.out.println(ia11); // 10
        System.out.println(iaMax); // 10
    }

    // digit 的逆方法
    public void forDigit() {
        char ia11 = Character.forDigit(10, 11);
        System.out.println(ia11); // a
    }

    /**
     * 强字符(strong)
     *
     * L
     *
     * left to right
     *
     * LRM(见表2)，大部分字母、音节、汉字、非欧洲或非阿拉伯数字
     *
     * R
     *
     * right to left
     *
     * RLM(见表2)，希伯来字母和相关的标点符号
     *
     * AL
     *
     * right to left Arabic
     *
     * ALM(见表2)，阿拉伯语(Arabic)、它拿字母(Thaana)、叙利亚字母，及大多数特定于这些文字的标点符号
     *
     * 弱字符(weak)
     *
     * EN
     *
     * 欧洲数字(European Number)
     *
     * 欧洲数字、东阿拉伯-印度数字，经常使用的数字1，2，3等就是属于EN类型
     *
     * ES
     *
     * 欧洲数字分隔符
     *
     * 加号，减号
     *
     * ET
     *
     * 欧洲数字终止符
     *
     * 度的符号，货币符号，比如，$(美元)，￥(人民币)等
     *
     * AN
     *
     * 阿拉伯数字
     *
     * 阿拉伯-印度数字，阿拉伯小数和千位分隔符，平时使用的数字虽然叫做阿拉伯数字，但阿拉伯拥有自已的数字，比如，4的阿拉伯数字字符为٤(u+0664)
     *
     * CS
     *
     * 普通数字分隔符
     *
     * 冒号，逗号，句点(即小数点)，不间断空格(no-break space)等，注意：单引号、双引号、分号不属于该类型，中文的句号也不属于该类型
     *
     * NSM
     *
     * 无间距标记(Nonspacing mark)
     *
     * 属性General_Category为以下值的字符：Mn(Nonspacing_Mark)和Me(Enclosing_Mark)比如，组合用发音字符的上左角 ̚ (u+031A)，西非书面文中的ࣾ (u+08FE) 。详见后文对组合字符的讲解
     *
     * BN
     *
     * 中性边界
     *
     * 不是明确给定类型的字符，比如：可忽略的默认值，非字符，控制字符等。比如，广义标点中的不可见乘号(u+2062)就是BN类型
     *
     * 中性字符(neutral)
     *
     * B
     *
     * 段落分隔符
     *
     * 段落分隔符(u+2029)，适当的换行符函数，高级别确定段落的协议
     *
     * S
     *
     * 节分隔符(Segment Separator)
     *
     * Tab
     *
     * WS
     *
     * 空白(Whitespace)
     *
     * 空格，图形空格，行分隔符，换页符，常用标点符号的空格等
     *
     * ON
     *
     * 其他中性符
     *
     * 所有其他字符，包括对象替换字符，比如，[、]、(、)、"、'、@、&、*、、<、>、|、{、}、;(分号)、!、?、~、=。注意：/ 属于CS类型、%、#属于ET类型
     *
     * 定向格式化字符
     *
     * 见后文
     * 返回对应的字符类型
     ** @see Character#DIRECTIONALITY_UNDEFINED
     *      * @see Character#DIRECTIONALITY_LEFT_TO_RIGHT
     *      * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT
     *      * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
     *      * @see Character#DIRECTIONALITY_EUROPEAN_NUMBER
     *      * @see Character#DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR
     *      * @see Character#DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR
     *      * @see Character#DIRECTIONALITY_ARABIC_NUMBER
     *      * @see Character#DIRECTIONALITY_COMMON_NUMBER_SEPARATOR
     *      * @see Character#DIRECTIONALITY_NONSPACING_MARK
     *      * @see Character#DIRECTIONALITY_BOUNDARY_NEUTRAL
     *      * @see Character#DIRECTIONALITY_PARAGRAPH_SEPARATOR
     *      * @see Character#DIRECTIONALITY_SEGMENT_SEPARATOR
     *      * @see Character#DIRECTIONALITY_WHITESPACE
     *      * @see Character#DIRECTIONALITY_OTHER_NEUTRALS
     *      * @see Character#DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING
     *      * @see Character#DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE
     *      * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING
     *      * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE
     *      * @see Character#DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
     */
    public void getDirectionality() {
        System.out.println(Character.getDirectionality('a')); //0
        System.out.println(Character.getDirectionality('ת')); //1 左向右的语言 （希伯来语）  // עִבְרִית
        System.out.println(Character.getDirectionality('ة')); //2 左向右的语言-阿拉伯语 // مجمع لغة عربية
        System.out.println(Character.getDirectionality('1')); //3 欧洲数字
        System.out.println(Character.getDirectionality('+')); //4 欧洲数字分隔符 加减号
        System.out.println(Character.getDirectionality('$')); //5 欧洲数字货币符号，比如，$(美元)，￥(人民币) ৳ 孟加拉元 等
        System.out.println(Character.getDirectionality('٤')); //6 阿拉伯数字 阿拉伯-印度数字，阿拉伯小数和千位分隔符，平时使用的数字虽然叫做阿拉伯数字，但阿拉伯拥有自已的数字，比如，4的阿拉伯数字字符为٤
        // 阿拉伯自己的数字 1 ١ 2 ٢ 3 ٣ 4 ٤ 5 ٥ 6 ٦ 7 ٧ 8 ٨ 9 ٩ 0 ٠
        System.out.println(Character.getDirectionality(',')); //7 普通数字分隔符 逗号
        /**
         * Such characters may be large enough to affect the placement of their base character relative to preceding and succeeding base characters. For example, a circumflex applied to an “i” may affect spacing (“î”), as might the character U+20DD combining enclosing circle.
         * 正如他的名字一样，看不到的 ̚ (u+031A)
         */
        System.out.println(Character.getDirectionality('̚')); //8 无间距标记
        /**
         * '\u0002' '\u0003'
         */
        System.out.println(Character.getDirectionality('\u2062')); //9 中性边界 可忽略的默认值，非字符，控制字符等 ，比如广义标点中的不可见乘号(u+2062)⁢就是BN类型
        System.out.println(Character.getDirectionality('\r')); //10 段落分隔符
        System.out.println(Character.getDirectionality('§')); // 11
        System.out.println(Character.getDirectionality('`')); // 13 分割号;
        System.out.println(Character.getDirectionality('`')); // 13 分割号;
        System.out.println(Character.getDirectionality('汉'));
    }

    //作者：圓月亮
    //链接：https://www.zhihu.com/question/42176549/answer/93852738
    //来源：知乎
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public void toCodePoint() {
        int toCodePoint = Character.toCodePoint('a', 'b');
        System.out.println(toCodePoint);
    }

    public void getName() {
        String result = Character.getName(100);
        int a = Character.codePointAt("abcd", 3);
        System.out.println(a);
        System.out.println(result);
    }

    /**
     * 逆方法参考 Character.codePointAt();
     */
    public void toChars() {
        char[] a = Character.toChars(0x10FFFF);// 0x10FFFF是最大值
        System.out.println(a);

        char[] b = Character.toChars(0x11FFFF);
        System.out.println(b);
    }

    /**
     *   U+D800 至 U+DBFF 的規定為「High Surrogates」，共1024個。
     */
    public void isHighSurrogate() {
        char lowerThenHigh = '\uD799';
        char inHigh = '\uD800';
        char upperThenHigh = '\uDC00';
        boolean b1 = Character.isHighSurrogate(lowerThenHigh);// 0x10FFFF是最大值
        boolean b2 = Character.isHighSurrogate(inHigh);// 0x10FFFF是最大值
        boolean b3 = Character.isHighSurrogate(upperThenHigh);// 0x10FFFF是最大值
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

    /**
     * 編號為 U+DC00 至 U+DFFF 的規定為「Low Surrogates」
     * @param
     */
    public void isLowSurrogate() {
        char lowerThenLow = '\uDBff';
        char inLow = '\uDC00';
        char upperThenLow = '\uE000';
        boolean b1 = Character.isLowSurrogate(lowerThenLow);
        boolean b2 = Character.isLowSurrogate(inLow);
        boolean b3 = Character.isLowSurrogate(upperThenLow);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }

    public static void main(String[] args) {
        CharacterDemo characterDemo = new CharacterDemo();
        characterDemo.isDefined();
    }

}
