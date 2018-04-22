package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Collection {

    public void ArrayList(){
        ArrayList<String> arrayList = new ArrayList<String>();
    }

    public void HashMap(){
        // hashMap的容量总是2的n次方，测试6会发生什么
        HashMap defaultHashMap = new HashMap();
        HashMap sixHashMap = new HashMap(6);
        HashMap sevteenHashMap = new HashMap(17);
        System.out.println(defaultHashMap);
        defaultHashMap.put("String","String");
        //debug  defaultHashMap.threshold  defaultHashMap.capacity()
        //capacity() 总是返回2的n次方。默认16
        //threshold 是resize的阈值，如果table array没有加载，就等于capacity,否则取(capacity * load factor)
        System.out.println(defaultHashMap);

    }

    public void HashSet(){
        HashSet hashSet = new HashSet();
    }

    /*
    原理是继承了hashMap, 另额外维护 1.Entry<K,V> before, after;
    2.transient LinkedHashMap.Entry<K,V> head;
    3. transient LinkedHashMap.Entry<K,V> tail; 4个属性，实现链表*/
    public void LinkedHashMap(){
        LinkedHashMap<String,String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("","");

    }
}
