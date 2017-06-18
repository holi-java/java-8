package com.holi.java8.functions.operations.push;

import com.holi.java8.functions.Operation.Function;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.BiFunction.push;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiFunctionPushTest {


    @Test
    public void fillsTheLastArgument() throws Throwable {
        Function<Integer, Integer> division = push(Ops::div, 3L);

        assertThat(division.apply(6), equalTo(2));
    }

    @Test
    public void chain() throws Throwable {
        Function<Integer, Integer> division = push(Ops::div, 3L);

        assertThat(division.push(6).get(), equalTo(2));
    }

}
