package com.holi.java8.functions.operations.curly;

import com.holi.java8.functions.Operation.Predicate3;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Predicate3.curly;
import static com.holi.java8.functions.util.Ops.match3;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Predicate3CurlyTest {

    private final Predicate3<String, String, String> matcher = match3("1", "2", "3");

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        assertThat(curly(matcher, "1").test("2", "3"), equalTo(true));
        assertThat(curly(matcher, "1").test("3", "2"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        assertThat(curly(matcher, "1").curly("2").test("3"), equalTo(true));
        assertThat(curly(matcher, "1").curly("3").test("2"), equalTo(false));
    }

}
