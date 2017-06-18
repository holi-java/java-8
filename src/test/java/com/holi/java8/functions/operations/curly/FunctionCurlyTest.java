package com.holi.java8.functions.operations.curly;

import org.junit.Test;

import java.util.function.Supplier;

import static com.holi.java8.functions.Operation.Function.curly;
import static com.holi.java8.functions.util.Ops.add1;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class FunctionCurlyTest {

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        Supplier<String> concat = curly(add1("bar"), "foo");

        assertThat(concat.get(), equalTo("foo:bar"));
    }

}
