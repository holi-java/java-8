package com.holi.java8.functions;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import static com.holi.java8.functions.Job.*;
import static com.holi.java8.functions.Lazy.lazy;
import static java.util.Collections.singleton;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class LazyInitializingTest {

    private final Supplier<Integer> initializer = new AtomicInteger(0)::incrementAndGet;

    @Test
    public void initializingOnce() throws Throwable {
        Supplier<Integer> it = lazy(initializer);

        assertThat(it.get(), equalTo(1));
        assertThat(it.get(), equalTo(1));
    }


    @Test
    public void avoidingDoubleLazying() throws Throwable {
        Supplier<Integer> it = lazy(initializer);

        assertThat(lazy(it), sameInstance(it));
    }

    @Test
    public void initializingOnceInConcurrent() throws Throwable {
        Supplier<Integer> it = lazy(initializer).lock();

        assertThat(spawn(delay(10, it)::get, MAX_THREADS * 2), equalTo(singleton(1)));
    }

    @Test
    public void synchronizingInitializerOnce() throws Throwable {
        AtomicInteger synchronizations = new AtomicInteger(0);
        Supplier<Integer> it = lazy(initializer).lock(onLock(synchronizations::incrementAndGet));

        spawn(it::get, MAX_THREADS * 100);
        assertThat(synchronizations.get(), lessThanOrEqualTo(MAX_THREADS));

        int last = synchronizations.get();
        it.get();
        assertThat(synchronizations.get(), equalTo(last));
    }

    private Lock onLock(Runnable action) {
        return new ReentrantLock() {
            @Override
            public void lock() {
                action.run();
                super.lock();
            }
        };
    }

    @Test(expected = NullPointerException.class)
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void throwsNPExceptionWhenLazyNull() throws Throwable {
        lazy(null);
    }


}
