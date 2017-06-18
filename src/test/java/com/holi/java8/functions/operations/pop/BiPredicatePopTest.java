package com.holi.java8.functions.operations.pop;

import org.junit.Test;

import static com.holi.java8.functions.Operation.BiPredicate.pop;
import static com.holi.java8.functions.util.Ops.match2;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiPredicatePopTest {


    @Test
    public void dropsTheLastParameter() throws Throwable {
        assertThat(pop(match2("foo", null)).test("foo"), equalTo(true));
        assertThat(pop(match2(null, "foo")).test("foo"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        assertThat(pop(match2(null, null)).pop().getAsBoolean(), equalTo(true));
        assertThat(pop(match2("foo", null)).pop().getAsBoolean(), equalTo(false));
    }

}
