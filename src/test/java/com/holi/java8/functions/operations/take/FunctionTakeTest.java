package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.function.Supplier;

import static com.holi.java8.functions.Operation.Function.take;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FunctionTakeTest {

    @Test
    public void dropsTheFirstParameter() throws Throwable {
        Supplier<String> concat = take(Ops.add1("bar"));

        assertThat(concat.get(), equalTo("null:bar"));
    }

}
