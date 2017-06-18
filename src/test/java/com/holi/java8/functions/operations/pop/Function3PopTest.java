package com.holi.java8.functions.operations.pop;

import com.holi.java8.functions.Operation.BiFunction;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Function3.pop;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Function3PopTest {

    @Test
    public void dropsTheLastParameter() throws Throwable {
        BiFunction<String, String, String> it = pop(Ops::concat3);

        assertThat(it.apply("2", "3"), equalTo("2:3:null"));
    }

    @Test
    public void chain() throws Throwable {
        BiFunction<String, String, String> it = pop(Ops::concat3);

        assertThat(it.pop().apply("3"), equalTo("3:null:null"));
    }

}
