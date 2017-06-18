package com.holi.java8.functions.operations.take;

import com.holi.java8.functions.util.Ops;
import org.junit.Test;

import static com.holi.java8.functions.Operation.Predicate.take;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class PredicateTakeTest {

    @Test
    public void dropsTheFirstParameter() throws Throwable {
        assertThat(take(Ops.match1(null)).getAsBoolean(), equalTo(true));
        assertThat(take(Ops.match1("foo")).getAsBoolean(), equalTo(false));
    }

}