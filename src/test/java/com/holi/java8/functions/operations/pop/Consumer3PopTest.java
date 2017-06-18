package com.holi.java8.functions.operations.pop;

import com.holi.java8.functions.Operation.BiConsumer;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.holi.java8.functions.Operation.Consumer3.pop;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Consumer3PopTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void dropsTheLastParameter() throws Throwable {
        BiConsumer<String, String> concat = pop(Ops.add3(list));
        assertThat(list, is(empty()));

        concat.accept("2", "3");

        assertThat(list, contains("2:3:null"));
    }

    @Test
    public void chain() throws Throwable {
        pop(Ops.add3(list)).pop().accept("1");


        assertThat(list, contains("1:null:null"));
    }

}
