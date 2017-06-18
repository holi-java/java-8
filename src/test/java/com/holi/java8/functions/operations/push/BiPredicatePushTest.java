package com.holi.java8.functions.operations.push;

import org.junit.Test;

import static com.holi.java8.functions.Operation.BiPredicate.push;
import static com.holi.java8.functions.util.Ops.match2;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiPredicatePushTest {

    @Test
    public void fillsTheLastArgument() throws Throwable {
        assertThat(push(match2("foo", "bar"), "bar").test("foo"), equalTo(true));
        assertThat(push(match2("foo", "bar"), "foo").test("baz"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        assertThat(push(match2("foo", "bar"), "bar").push("foo").getAsBoolean(), equalTo(true));
        assertThat(push(match2("foo", "bar"), "foo").push("baz").getAsBoolean(), equalTo(false));
    }
}
