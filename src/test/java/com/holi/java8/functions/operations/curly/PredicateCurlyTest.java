package com.holi.java8.functions.operations.curly;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Predicate.curly;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PredicateCurlyTest {

    @Test
    public void fillsTheFirstArgument() throws Throwable {
        assertThat(curly(Ops.match1("foo"), "foo").getAsBoolean(), equalTo(true));
        assertThat(curly(Ops.match1("foo"), null).getAsBoolean(), equalTo(false));
    }

    @Test
    public void boxed() throws Throwable {
        assertThat(curly(Ops.match1("foo"), "foo").boxed().get(), equalTo(true));
        assertThat(curly(Ops.match1("foo"), null).boxed().get(), equalTo(false));
    }
}
