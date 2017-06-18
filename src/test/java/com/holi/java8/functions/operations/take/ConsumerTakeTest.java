package com.holi.java8.functions.operations.take;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.holi.java8.functions.Operation.Consumer.take;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ConsumerTakeTest {
    final List<String> list = new ArrayList<>();

    @Test
    public void dropsTheFirstParameter() throws Throwable {
        Runnable action = take((Consumer<String>) list::add);

        assertThat(list, is(empty()));

        action.run();
        assertThat(list, contains((String) null));
    }

}
