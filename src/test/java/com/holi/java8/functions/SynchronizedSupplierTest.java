package com.holi.java8.functions;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

import static com.holi.java8.functions.SynchronizedSupplier.sync;
import static com.holi.java8.functions.util.Job.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.sameInstance;

public class SynchronizedSupplierTest {

    @Test
    public void synchronizing() throws Throwable {
        int times = MAX_THREADS * 2;
        CountDownLatch blocking = new CountDownLatch(1);

        Supplier<Integer> it = sync(delay(10, this::next));

        blocking.countDown();
        assertThat(spawn(it::get, times), hasSize(times));
    }

    @Test(expected = NullPointerException.class)
    public void throwsNPExceptionWhenSyncNull() throws Throwable {
        sync(null);
    }

    @Test(expected = NullPointerException.class)
    public void throwsNPExceptionWhenSyncNullLock() throws Throwable {
        sync(null, this::next);
    }


    @Test
    public void avoidingDoubleSynchronization() throws Throwable {
        Supplier<Integer> it = sync(this::next);

        assertThat(sync(it), sameInstance(it));
    }

    int value;

    private Integer next() {
        return value++;
    }

}
