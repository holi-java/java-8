package com.holi.java8.functions.operations.curly;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.holi.java8.functions.Operation.Consumer.curly;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConsumerCurlyTest {
    final List<Integer> list = new ArrayList<>();

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        int[] addend = {0};
        Runnable action = curly((Integer it) -> list.add(it + addend[0]++), 3);

        assertThat(list, is(empty()));

        action.run();
        assertThat(list, contains(3));

        action.run();
        assertThat(list, contains(3, 4));
    }

}
