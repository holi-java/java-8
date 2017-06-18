package com.holi.java8.functions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Job {
    public static final int MAX_THREADS = 20;

    public static <T> Supplier<T> delay(long millis, Supplier<T> target) {
        return () -> {
            try {
                Thread.sleep(millis);
                return target.get();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        };
    }

    public static <T> Set<T> spawn(Callable<T> task, int times) throws InterruptedException, ExecutionException {
        return distinction(startRepeating(task, times));
    }

    private static <T> Set<T> distinction(List<Future<T>> futures) throws InterruptedException, ExecutionException {
        Set<T> result = new HashSet<>();
        for (Future<T> future : futures) {
            result.add(future.get());
        }
        return result;
    }

    private static <T> List<Future<T>> startRepeating(Callable<T> task, int times) {
        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        return blocking(it -> IntStream.range(0, times).mapToObj(i -> executor.submit(await(it, task))).collect(toList()));
    }

    private static <R> R blocking(Function<CountDownLatch, R> action) {
        CountDownLatch blocking = new CountDownLatch(1);
        R result = action.apply(blocking);
        blocking.countDown();
        return result;
    }

    private static <T> Callable<T> await(CountDownLatch blocking, Callable<T> target) {
        return () -> {
            blocking.await();
            return target.call();
        };
    }
}
