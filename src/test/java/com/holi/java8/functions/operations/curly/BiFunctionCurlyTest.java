package com.holi.java8.functions.operations.curly;

import com.holi.java8.functions.Operation.Function;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.BiFunction.curly;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiFunctionCurlyTest {

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        Function<Long, Integer> division = curly(Ops::div, 6);

        assertThat(division.apply(3L), equalTo(2));
    }

    @Test
    public void chain() throws Throwable {
        Function<Long, Integer> division = curly(Ops::div, 6);

        assertThat(division.curly(3L).get(), equalTo(2));
    }

}
