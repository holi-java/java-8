package com.holi.java8.functions.operations.curly;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.holi.java8.functions.Operation.BiConsumer.curly;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class BiConsumerCurlyTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        Consumer<String> concat = curly(Ops.add2(list), "1");
        assertThat(list, is(empty()));

        concat.accept("2");

        assertThat(list, contains("1:2"));
    }

    @Test
    public void chain() throws Throwable {
        Runnable action = curly(Ops.add2(list), "1").curly("2");

        action.run();

        assertThat(list, contains("1:2"));
    }

}
