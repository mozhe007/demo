package rt.java.lang;


public class ClassLoaderDemo extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //  偷偷地替换对String的加载。
        if (name.equals("java.lang.String")) {
            return loadClass("commonbean.Fake", false);
        }
        return loadClass(name, false);
    }

    // 尝试破环双亲加载机制
    public void brokeParentsLoading() {
        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();
        Thread.currentThread().setContextClassLoader(classLoaderDemo);
        try {
            Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("java.lang.String");
            System.out.println(aClass.newInstance().toString());
            String a = new String();
            System.out.println("1"+a.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClassLoaderDemo classLoaderDemo = new ClassLoaderDemo();
        classLoaderDemo.brokeParentsLoading();
    }

}
