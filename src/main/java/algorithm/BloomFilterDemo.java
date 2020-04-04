package algorithm;


import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/*布隆过滤器*/
public class BloomFilterDemo {
    BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 200000, 1E-7);

    void test() {
        for (int i = 0; i < 2000; i++) {
            String testString = "name" + i;
            if (i % 48 == 0) {
                testString = "bingo";
            }
            boolean abc = bloomFilter.put(testString);
            if(abc){
               //System.out.println(testString);
            }else{
               System.out.println("bingo:"+i );
            }
        }
    }

    public static void main(String[] args) {
        BloomFilterDemo bloomFilterDemo = new BloomFilterDemo();
        bloomFilterDemo.test();
    }
}
