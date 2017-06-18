package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.holi.java8.functions.Operation.BiConsumer.take;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BiConsumerTakeTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void dropsTheFirstParameter() throws Throwable {
        Consumer<String> concat = take(Ops.add2(list));
        assertThat(list, is(empty()));

        concat.accept("foo");

        assertThat(list, contains("null:foo"));
    }

    @Test
    public void chain() throws Throwable {
        Runnable action = take(Ops.add2(list)).take();

        action.run();

        assertThat(list, contains("null:null"));
    }

}
