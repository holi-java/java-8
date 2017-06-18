package com.holi.java8.functions.operations.pop;

import com.holi.java8.functions.Operation.Predicate3;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Predicate3.pop;
import static com.holi.java8.functions.util.Ops.match3;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Predicate3PopTest {


    @Test
    public void dropsTheLastParameter() throws Throwable {
        Predicate3<String, String, String> matcher = match3("3", "2", null);

        assertThat(pop(matcher).test("3", "2"), equalTo(true));
        assertThat(pop(matcher).test("2", "3"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        Predicate3<String, String, String> matcher = match3("3", null, null);

        assertThat(pop(matcher).pop().test("3"), equalTo(true));
        assertThat(pop(matcher).pop().test("2"), equalTo(false));
    }

}
