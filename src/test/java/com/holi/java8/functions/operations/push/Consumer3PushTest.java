package com.holi.java8.functions.operations.push;

import com.holi.java8.functions.Operation.BiConsumer;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.holi.java8.functions.Operation.Consumer3.push;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Consumer3PushTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void fillsTheLastArgument() throws Throwable {
        BiConsumer<String, String> concat = push(Ops.add3(list), "1");
        assertThat(list, is(empty()));

        concat.accept("2", "3");

        assertThat(list, contains("2:3:1"));
    }

    @Test
    public void chain() throws Throwable {
        Runnable action = push(Ops.add3(list), "1").push("2").push("3");

        action.run();

        assertThat(list, contains("3:2:1"));
    }

}
