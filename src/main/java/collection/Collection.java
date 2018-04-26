package collection;

import com.sun.java.accessibility.AccessBridge;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/*包括
arrayList
LinkedList
hashSet
hashMap
LinkedHashSet
LinkedhashMap
TreeSet
TreeMap*/
public class Collection {

    public void arrayList() {
        ArrayList<String> arrayList = new ArrayList<String>();
    }

    public void linkedList() {
        List<String> list = new LinkedList<String>();
        list.add("");
    }

    public void hashMap() {
        // hashMap的容量总是2的n次方，
        HashMap defaultHashMap = new HashMap();
        HashMap sixHashMap = new HashMap(6);
        HashMap sevteenHashMap = new HashMap(17);
        System.out.println(defaultHashMap);
        defaultHashMap.put("String", "String");
        // debug  defaultHashMap.threshold  defaultHashMap.capacity()
        // capacity() 总是返回2的n次方。默认16
        // threshold 是resize的阈值，如果table array没有加载，就等于capacity,否则取(capacity * load factor)
        System.out.println(defaultHashMap);

        /* 增 */
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("141", "1");
        hashMap.put("342", "2");
        hashMap.put("231", "3");
        hashMap.put("631", "4");
        /* 查 */
        //  无须
        for (String value : hashMap.values()) {
            System.out.println(value);
        }

    }

    public void hashSet() {
        HashSet hashSet = new HashSet();
    }

    /*
    原理是继承了hashMap, 另额外维护 1.Entry<K,V> before, after;
    2.transient linkedHashMap.Entry<K,V> head;
    3. transient linkedHashMap.Entry<K,V> tail; 4个属性，实现链表*/
    public void linkedHashMap() {
        // 增
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("141", "1");
        linkedHashMap.put("342", "2");
        linkedHashMap.put("231", "3");
        linkedHashMap.put("631", "4");
        // 查
        // 有序
        for (String value : linkedHashMap.values()) {
            System.out.println(value);
        }
    }

    /*linkedHashSet 继承  HashSet,HashSet的构造方法调用 LinkedHashMap
    * 通过 HashSet 的构造方法，实现对HashSet一系列方法的复用，真是溜*/
    public void linkedHashSet(){
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(5);
        linkedHashSet.add(4);
        linkedHashSet.add(6);
        linkedHashSet.add(2);
    }


    /* TreeSet/TreeMap 关系 和 HashSet/HashMap的关系很像
     *
     */
    public void treeSet() {
        //增
        TreeSet treeSet = new TreeSet();
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(6);
        treeSet.add(7);
        treeSet.add(8);
        treeSet.add(5);
        //查
        for (Iterator iter = treeSet.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
        //可以获取指定元素的子集 headSet
        TreeSet headSet = (TreeSet) treeSet.headSet(5,true);
        for (Iterator iter = headSet.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
        //可以获取指定元素的子集 tailSet
        TreeSet tailSet = (TreeSet) treeSet.tailSet(5,true);
        for (Iterator iter = tailSet.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
        ReflectionToStringBuilder.toString(this);

    }

    public void treeMap(){
        TreeMap treeMap = new TreeMap();
    }

    /* 利用分段锁的 */
    public void concurrentMap(){
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("a","");
    }


}
