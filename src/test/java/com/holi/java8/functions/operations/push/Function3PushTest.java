package com.holi.java8.functions.operations.push;

import com.holi.java8.functions.Operation.BiFunction;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Function3.push;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Function3PushTest {

    @Test
    public void fillsTheLastArgument() throws Throwable {
        BiFunction<String, String, String> it = push(Ops::concat3, "1");

        assertThat(it.apply("2", "3"), equalTo("2:3:1"));
    }

    @Test
    public void chain() throws Throwable {
        BiFunction<String, String, String> it = push(Ops::concat3, "1");

        assertThat(it.push("2").apply("3"), equalTo("3:2:1"));
    }

}
