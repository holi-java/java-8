package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.Operation.Predicate3;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Predicate3.take;
import static com.holi.java8.functions.util.Ops.match3;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Predicate3TakeTest {


    @Test
    public void dropsTheFirstParameter() throws Throwable {
        Predicate3<String, String, String> matcher = match3(null, "2", "3");

        assertThat(take(matcher).test("2", "3"), equalTo(true));
        assertThat(take(matcher).test("3", "2"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        Predicate3<String, String, String> matcher = match3(null, null, "3");

        assertThat(take(matcher).take().test("3"), equalTo(true));
        assertThat(take(matcher).take().test("2"), equalTo(false));
    }

}
