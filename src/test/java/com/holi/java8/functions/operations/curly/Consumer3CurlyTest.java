package com.holi.java8.functions.operations.curly;

import com.holi.java8.functions.Operation;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.holi.java8.functions.Operation.Consumer3.curly;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Consumer3CurlyTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        Operation.BiConsumer<String, String> concat = curly(Ops.add3(list), "1");
        assertThat(list, is(empty()));

        concat.accept("2", "3");

        assertThat(list, contains("1:2:3"));
    }

    @Test
    public void chain() throws Throwable {
        Runnable action = curly(Ops.add3(list), "1").curly("2").curly("3");

        action.run();

        assertThat(list, contains("1:2:3"));
    }

}
