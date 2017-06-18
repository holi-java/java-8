package com.holi.java8.functions.operations.push;

import com.holi.java8.functions.Operation.Predicate3;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Predicate3.push;
import static com.holi.java8.functions.util.Ops.match3;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class Predicate3PushTest {

    private final Predicate3<String, String, String> matcher = match3("3", "2", "1");

    @Test
    public void fillsTheLastArgument() throws Throwable {
        assertThat(push(matcher, "1").test("3", "2"), equalTo(true));
        assertThat(push(matcher, "1").test("2", "3"), equalTo(false));
    }

    @Test
    public void chain() throws Throwable {
        assertThat(push(matcher, "1").push("2").test("3"), equalTo(true));
        assertThat(push(matcher, "1").push("3").test("2"), equalTo(false));
    }

}
