package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.Operation.BiFunction;
import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Function3.take;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Function3TakeTest {

    @Test
    public void dropsTheFistParameter() throws Throwable {
        BiFunction<String, String, String> it = take(Ops::concat3);

        assertThat(it.apply("2", "3"), equalTo("null:2:3"));
    }

    @Test
    public void chain() throws Throwable {
        BiFunction<String, String, String> it = take(Ops::concat3);

        assertThat(it.take().apply("3"), equalTo("null:null:3"));
    }

}
