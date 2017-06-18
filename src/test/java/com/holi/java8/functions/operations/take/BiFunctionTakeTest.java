package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.Operation.Function;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.function.Supplier;

import static com.holi.java8.functions.Operation.BiFunction.take;
import static com.holi.java8.functions.Operation.Function.take;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiFunctionTakeTest {

    @Test
    public void dropsTheFirstParameter() throws Throwable {
        Supplier<String> concat = take(Ops.add1("bar"));

        assertThat(concat.get(), equalTo("null:bar"));
    }


    @Test
    public void chain() throws Throwable {
        Function<String, String> square = take(Ops::concat2);

        assertThat(square.take().get(), equalTo("null:null"));
    }

}
