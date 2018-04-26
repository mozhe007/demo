package jvm;

public class PartionOnStack {
    class User{
        public int id;
        public String name;
    }
    public  void foo() {
        User user=new User();
        user.id=1;
        user.name="sixtrees";
    }
    public static void main(String[] args) {
        System.out.println("start-----------");
        long beginTime=System.currentTimeMillis();
        PartionOnStack pos=new PartionOnStack();
        for(int i=0;i<100000000;i++)
        {
            pos.foo();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("总共运行----"+(endTime-beginTime)+"ms");
    }

}