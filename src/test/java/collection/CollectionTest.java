package collection;

import org.junit.Test;
import org.springframework.util.Assert;

public class CollectionTest {

    @Test
    public void ArrayListTest(){
        Collection collection = new Collection();
        collection.arrayList();
        Assert.isTrue(true,"true");
    }

    @Test
    public void HashMapTest(){
        Collection collection = new Collection();
        collection.hashMap();
    }


    @Test
    public void linkedHashMapTest(){
        Collection collection = new Collection();
        collection.linkedHashMap();
    }

    @Test
    public void linkedHashSetTest(){
        Collection collection = new Collection();
        collection.linkedHashSet();
    }



    @Test
    public void treeSetTest(){
        Collection collection = new Collection();
        collection.treeSet();
    }

}