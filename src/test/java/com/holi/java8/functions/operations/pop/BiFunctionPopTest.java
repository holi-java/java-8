package com.holi.java8.functions.operations.pop;

import com.holi.java8.functions.Operation.Function;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.BiFunction.pop;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiFunctionPopTest {

    @Test
    public void dropsTheLastParameter() throws Throwable {
        Function<String, String> concat = pop(Ops::concat2);

        assertThat(concat.apply("bar"), equalTo("bar:null"));
    }

    @Test
    public void chain() throws Throwable {
        Function<String, String> square = pop(Ops::concat2);

        assertThat(square.pop().get(), equalTo("null:null"));
    }

}
