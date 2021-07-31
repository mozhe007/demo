package algorithm;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;


/**
 * 数学里面自然数 e 的求和
 */
public class E {

    public void sum(int n) {
        BigDecimal sum = new BigDecimal(0);
        for (int i = 1; i <= n; i++) {
            BigDecimal a = new BigDecimal(1);
            BigDecimal b = new BigDecimal(0);
            for (int j = 1; j <= i; j++) {
                b = b.add(new BigDecimal(j));
            }
            sum = sum.add(a.divide(b,4,ROUND_HALF_UP));
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        E e = new E();
        e.sum(100);
    }
}
