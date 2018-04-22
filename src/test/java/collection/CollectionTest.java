package collection;

import org.junit.Test;
import org.springframework.util.Assert;

public class CollectionTest {

    @Test
    public void HashMapTest(){
        Collection collection = new Collection();
        collection.HashMap();
    }


    @Test
    public void ArrayListTest(){
        Collection collection = new Collection();
        collection.ArrayList();
        Assert.isTrue(true,"true");
    }



}