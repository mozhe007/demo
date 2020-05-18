package rt.java.invoke;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

import static rt.java.invoke.MutableInteger.findMinValue;

public class AbstractValidatingLambdaMetafactoryDemo {

    public static void main(String[] args) {

        final List< MutableInteger > values = Arrays.asList(new MutableInteger(10), new MutableInteger(20));
        final int mv = findMinValue(values).orElse(Integer.MIN_VALUE);
        System.out.println(mv);
    }
}


final class MutableInteger extends Number implements IntSupplier, IntConsumer { //mutable container of int value
    private int value;
    public MutableInteger(final int v) {
        value = v;
    }
    @Override
    public int intValue() {
        return value;
    }
    @Override
    public long longValue() {
        return value;
    }
    @Override
    public float floatValue() {
        return value;
    }
    @Override
    public double doubleValue() {
        return value;
    }
    @Override
    public int getAsInt() {
        return intValue();
    }
    @Override
    public void accept(final int value) {
        this.value = value;
    }
    static < T extends Number & IntSupplier > OptionalInt findMinValue(final Collection< T > values) {
        return values.stream().mapToInt(IntSupplier::getAsInt).min();
    }
}
