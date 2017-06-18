package com.holi.java8.functions.operations.take;

import org.junit.Test;

import static com.holi.java8.functions.Operation.BiPredicate.take;
import static com.holi.java8.functions.util.Ops.match2;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiPredicateTakeTest {


    @Test
    public void dropsTheFirstParameter() throws Throwable {
        assertThat(take(match2(null, "foo")).test("foo"), equalTo(true));
        assertThat(take(match2("foo", null)).test("foo"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        assertThat(take(match2(null, null)).take().getAsBoolean(), equalTo(true));
        assertThat(take(match2("foo", null)).take().getAsBoolean(), equalTo(false));
    }

}
