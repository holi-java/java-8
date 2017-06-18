package com.holi.java8.functions;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public interface SynchronizedSupplier<T> extends Supplier<T> {
    static <T> Supplier<T> sync(Supplier<T> target) {
        return sync(new ReentrantLock(), target);
    }

    static <T> Supplier<T> sync(Lock lock, Supplier<T> target) {
        if (target instanceof SynchronizedSupplier) {
            return target;
        }
        requireNonNull(lock, "Lock is null");
        requireNonNull(target, "Supplier is null");
        return (SynchronizedSupplier<T>) () -> {
            lock.lock();
            try {
                return target.get();
            } finally {
                lock.unlock();
            }
        };
    }
}
