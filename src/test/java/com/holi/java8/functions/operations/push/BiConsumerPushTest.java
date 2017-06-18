package com.holi.java8.functions.operations.push;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.holi.java8.functions.Operation.BiConsumer.push;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BiConsumerPushTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void fillsTheLastArgument() throws Throwable {
        Consumer<String> concat = push(Ops.add2(list), "1");
        assertThat(list, is(empty()));

        concat.accept("2");

        assertThat(list, contains("2:1"));
    }

    @Test
    public void chain() throws Throwable {
        Runnable action = push(Ops.add2(list), "1").push("2");

        action.run();

        assertThat(list, contains("2:1"));
    }

}
