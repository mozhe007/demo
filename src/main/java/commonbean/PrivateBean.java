package commonbean;

/**
 * 普通的Object
 */
public class PrivateBean {

    private String id;
    public PrivateBean() {
    }
    public PrivateBean(String id) {
        this.id = id;
    }

    private void privateMethod(){
        System.out.println("this is privateMethod()");
    }

    @Override
    public String toString(){
        return id;
    }

}
