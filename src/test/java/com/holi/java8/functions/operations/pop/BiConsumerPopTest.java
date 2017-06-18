package com.holi.java8.functions.operations.pop;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.holi.java8.functions.Operation.BiConsumer.pop;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BiConsumerPopTest {
    final List<String> list = new ArrayList<>();


    @Test
    public void dropsTheLastParameter() throws Throwable {
        Consumer<String> concat = pop(Ops.add2(list));
        assertThat(list, is(empty()));

        concat.accept("foo");

        assertThat(list, contains("foo:null"));
    }

    @Test
    public void chain() throws Throwable {
        Runnable action = pop(Ops.add2(list)).pop();

        action.run();

        assertThat(list, contains("null:null"));
    }

}
