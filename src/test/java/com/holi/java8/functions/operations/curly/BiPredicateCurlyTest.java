package com.holi.java8.functions.operations.curly;

import org.junit.Test;

import static com.holi.java8.functions.Operation.BiPredicate.curly;
import static com.holi.java8.functions.util.Ops.match2;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BiPredicateCurlyTest {

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        assertThat(curly(match2("foo", "bar"), "foo").test("bar"), equalTo(true));
        assertThat(curly(match2("foo", "bar"), "foo").test("baz"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        assertThat(curly(match2("foo", "bar"), "foo").curly("bar").getAsBoolean(), equalTo(true));
        assertThat(curly(match2("foo", "bar"), "foo").curly("baz").getAsBoolean(), equalTo(false));
    }

}
