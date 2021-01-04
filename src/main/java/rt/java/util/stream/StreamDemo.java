package rt.java.util.stream;

import com.alibaba.fastjson.JSON;
import commonbean.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


//stream

// filter
// map
// toXXmap  XX是8个基本类型


public class StreamDemo {


    //
    //

    /**
     * Stream<T> filter(Predicate<? super T> predicate);
     *
     *  其过滤的作用   根据代码，会有 ReferrencePipeline调用
     */
    public void filter() {
        List<Bean> list = new ArrayList();
        list.add(new Bean("1"));
        list.add(new Bean("2"));
        list.stream().filter(e -> e.getId().equals(2));

        List<Bean> filtedList = list.stream().filter(e -> e.getId().equals("2")).collect(Collectors.toList());
        assert filtedList.size()==1;
        assert filtedList.get(0).getId().equals("2");

        // 匿名表达式等同于
        list.stream().filter(new Predicate(){

            @Override
            public boolean test(Object o) {
                return false;
            }

            @Override
            public Predicate and(Predicate other) {
                return null;
            }

            @Override
            public Predicate negate() {
                return null;
            }

            @Override
            public Predicate or(Predicate other) {
                return null;
            }
        });
    }

    // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
    // 根据代码，会有 ReferrencePipeline调用
    public void map() {
        List<Bean> list = new ArrayList();
        list.add(new Bean("2"));
        list.stream().map(e -> e.getId().equals(2));
        list.stream().map(new Function<Bean, String>() {
            @Override
            public String apply(Bean bean) {
                bean.getId().equals(2);
                return null;
            }

            @Override
            public <V> Function<V, String> compose(Function<? super V, ? extends Bean> before) {
                return null;
            }

            @Override
            public <V> Function<Bean, V> andThen(Function<? super String, ? extends V> after) {
                return null;
            }
        });
        System.out.println(1);

    }


    public void mapToXXX(){
        List<Bean> list = new ArrayList();
        list.add(new Bean("2"));
        list.add(new Bean("6"));
        int iSum = list.stream().mapToInt(s -> Integer.parseInt(s.getId())).sum();

        System.out.println(iSum);

    }

    //sum
    //reduce
    //
    public void operation(){
        List<Bean> list = new ArrayList();
        list.add(new Bean("2"));
        list.add(new Bean("6"));
        // sum
        int iSum = list.stream().mapToInt(s -> Integer.parseInt(s.getId())).sum();
        // reduce， 其实不是减，自定义操作
        // OptionalInt reduce(IntBinaryOperator op)
        int reduce1 = list.stream().mapToInt(s -> Integer.parseInt(s.getId())).reduce( (a, b) -> a -b).getAsInt();
        // int reduce(int identity, IntBinaryOperator op);  identity 代表 第一个 “a” 这里是0
        int reduce2 = list.stream().mapToInt(s -> Integer.parseInt(s.getId())).reduce(0, (a, b) -> a -b);
        System.out.println(iSum);
        System.out.println(reduce1);
        System.out.println(reduce2);
    }


    // forEach的返回值是void ，意味后面不能再接代码了。
    public void forEach() {
        List<Bean> list = new ArrayList();
        list.add(new Bean("1"));
        list.add(new Bean("2"));
        list.stream().filter(e -> e.getId().equals(2));

       list.stream().forEach(e -> {
            if(e.getId().equals("2")){
               //  list.remove(e);  remove也会报 ConcurrentModificationException
            }
        });
        assert list.size()==1;
        System.out.println(1);
    };


    public void collect() {
        List<Bean> list = new ArrayList();
        list.add(new Bean("1"));
        list.add(new Bean("2"));
        list.stream().filter(e -> e.getId().equals(2));

        List<Bean> filtedList = list.stream().collect(Collectors.toList());
        Map<String,Bean> filtedMap = list.stream().collect(Collectors.toMap(Bean::getId,p->p));
        System.out.println(JSON.toJSONString(filtedMap));// {"1":{"id":"1"},"2":{"id":"2"}}

        //
        filtedMap = list.stream().collect(Collectors.toMap(Bean::getId,p->p));
        filtedMap = list.stream().collect(Collectors.toMap(item -> item.getId(), p->p));
    };


    public static void main(String[] args) {
        StreamDemo streamDemo = new StreamDemo();
        streamDemo.operation();
    }

}
