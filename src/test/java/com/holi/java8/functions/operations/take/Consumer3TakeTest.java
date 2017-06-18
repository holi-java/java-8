package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.Operation.BiConsumer;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.holi.java8.functions.Operation.Consumer3.take;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Consumer3TakeTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void dropsTheFirstParameter() throws Throwable {
        BiConsumer<String, String> concat = take(Ops.add3(list));
        assertThat(list, is(empty()));

        concat.accept("2", "3");

        assertThat(list, contains("null:2:3"));
    }

    @Test
    public void chain() throws Throwable {
        take(Ops.add3(list)).take().accept("1");


        assertThat(list, contains("null:null:1"));
    }

}
