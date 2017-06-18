package com.holi.java8.functions;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public interface Lazy<T> extends Supplier<T> {
    static <T> Lazy<T> lazy(Supplier<T> initializer) {
        requireNonNull(initializer, "Initializer is null");
        if (initializer instanceof Lazy) {
            return (Lazy<T>) initializer;
        }
        return new Lazy<T>() {
            private Supplier<T> value = () -> {
                T initialized = initializer.get();
                value = () -> initialized;
                return initialized;
            };

            @Override
            public T get() {
                return value.get();
            }
        };
    }

    default Supplier<T> lock() {
        return lock(new ReentrantLock());
    }

    default Supplier<T> lock(Lock lock) {
        return lazy(SynchronizedSupplier.sync(lock, this));
    }
}

