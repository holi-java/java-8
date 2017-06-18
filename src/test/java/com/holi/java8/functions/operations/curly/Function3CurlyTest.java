package com.holi.java8.functions.operations.curly;

import com.holi.java8.functions.Operation.BiFunction;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Function3.curly;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Function3CurlyTest {

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        BiFunction<String, String, String> it = curly(Ops::concat3, "1");

        assertThat(it.apply("2", "3"), equalTo("1:2:3"));
    }

    @Test
    public void chain() throws Throwable {
        BiFunction<String, String, String> it = curly(Ops::concat3, "1");

        assertThat(it.curly("2").apply("3"), equalTo("1:2:3"));
    }

}
