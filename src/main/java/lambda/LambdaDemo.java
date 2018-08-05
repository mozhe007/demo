package lambda;

public class LambdaDemo {

    public void one(OneFunctionInterface oneFunctionInterface) {
        oneFunctionInterface.function1();
    }

    public void more(MoreFunctionInterface moreFunctionInterface) {

    }

    public void param(ParamInterface paramInterface) {

    }

    public void returnF(ReturnInterface returnInterface){

        System.out.println(returnInterface.function1("112323"));
    }

    public static void main(String[] args) {
        LambdaDemo lambdaDemo = new LambdaDemo();
        //----------------one function--------------
        //jdk 1.7
        lambdaDemo.one(new OneFunctionInterface() {
            @Override
            public void function1() {
                System.out.println("one");
            }
        });
        //lambda
        lambdaDemo.one(() -> System.out.println("one"));
        //----------------more function--------------
        // not work
        // lambdaDemo.more(()-> System.out.println("no fucntion"));
        // lambdaDemo.more(string->{} System.out.println("no fucntion"));

        //----------------with param function--------------
        //jdk 1.7
        lambdaDemo.param(new ParamInterface() {
            @Override
            public void function1(String string) {
                System.out.println(string);
            }
        });
        // lambda one line
        lambdaDemo.param(param ->
                System.out.println(param)
        );
        // lambda more lines
        lambdaDemo.param(param -> {
            System.out.println(param);
            System.out.println(param);
        });

        //----------------return  param function--------------

        // lambda more lines
        lambdaDemo.returnF(param -> {

            return Integer.parseInt(param);
        });
    }
}
