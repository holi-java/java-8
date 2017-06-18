package com.holi.java8.functions;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public interface Operation<F, R1, L, R2> {
    R1 curly(F first);

    R1 take();

    R2 push(L last);

    R2 pop();


    interface UnaryOperation<T, R> extends Operation<T, R, T, R> {
        @Override
        default R push(T last) {
            return curly(last);
        }

        default R pop() {
            return take();
        }
    }

    interface Predicate<T> extends java.util.function.Predicate<T>, UnaryOperation<T, BooleanSupplier> {
        static <T> BooleanSupplier curly(java.util.function.Predicate<T> condition, T value) {
            requireNonNull(condition, "Predicate is null");
            return () -> condition.test(value);
        }

        static <T> BooleanSupplier take(java.util.function.Predicate<T> condition) {
            requireNonNull(condition, "Predicate is null");
            return () -> condition.test(null);
        }

        @Override
        default BooleanSupplier curly(T value) {
            return () -> test(value);
        }

        @Override
        default BooleanSupplier take() {
            return take(this);
        }
    }

    interface BiPredicate<T, U> extends java.util.function.BiPredicate<T, U>, Operation<T, Predicate<U>, U, Predicate<T>> {

        static <T, U> Predicate<U> curly(java.util.function.BiPredicate<T, U> condition, T first) {
            requireNonNull(condition, "java.util.function.BiPredicate is null");
            return second -> condition.test(first, second);
        }

        static <T, U> Predicate<T> push(java.util.function.BiPredicate<T, U> condition, U last) {
            requireNonNull(condition, "java.util.function.BiPredicate is null");
            return first -> condition.test(first, last);
        }

        static <T, U> Predicate<U> take(java.util.function.BiPredicate<T, U> condition) {
            requireNonNull(condition, "Predicate is null");
            return second -> condition.test(null, second);
        }

        static <T, U> Predicate<T> pop(java.util.function.BiPredicate<T, U> condition) {
            requireNonNull(condition, "Predicate is null");
            return first -> condition.test(first, null);
        }

        @Override
        default Predicate<U> curly(T first) {
            return curly(this, first);
        }

        @Override
        default Predicate<U> take() {
            return take(this);
        }

        @Override
        default Predicate<T> push(U last) {
            return push(this, last);
        }

        @Override
        default Predicate<T> pop() {
            return pop(this);
        }
    }

    interface Predicate3<F, S, L> {
        static <F, S, L> BiPredicate<S, L> curly(Predicate3<F, S, L> condition, F first) {
            requireNonNull(condition, "Predicate3 is null");
            return (second, last) -> condition.test(first, second, last);
        }

        static <F, S, L> BiPredicate<F, S> push(Predicate3<F, S, L> condition, L last) {
            requireNonNull(condition, "Predicate3 is null");
            return (first, second) -> condition.test(first, second, last);
        }

        static <F, S, L> BiPredicate<S, L> take(Predicate3<F, S, L> condition) {
            requireNonNull(condition, "Predicate3 is null");
            return (second, last) -> condition.test(null, second, last);
        }

        static <F, S, L> BiPredicate<F, S> pop(Predicate3<F, S, L> condition) {
            requireNonNull(condition, "Predicate3 is null");
            return (first, second) -> condition.test(first, second, null);
        }

        boolean test(F first, S second, L last);
    }

    interface BooleanSupplier extends java.util.function.BooleanSupplier, Boxable<Supplier<Boolean>> {
        @Override
        default Supplier<Boolean> boxed() {
            return this::getAsBoolean;
        }
    }

    interface Function<T, R> extends java.util.function.Function<T, R>, UnaryOperation<T, Supplier<R>> {
        static <T, R> Supplier<R> curly(java.util.function.Function<T, R> function, T value) {
            requireNonNull(function, "Function is null");
            return () -> function.apply(value);
        }

        static <T, R> Supplier<R> take(java.util.function.Function<T, R> function) {
            requireNonNull(function, "Function is null");
            return () -> function.apply(null);
        }

        @Override
        default Supplier<R> curly(T value) {
            return curly(this, value);
        }

        @Override
        default Supplier<R> take() {
            return take(this);
        }
    }

    interface BiFunction<T, U, R> extends java.util.function.BiFunction<T, U, R>, Operation<T, Function<U, R>, U, Function<T, R>> {

        static <T, U, R> Function<U, R> curly(java.util.function.BiFunction<T, U, R> function, T first) {
            requireNonNull(function, "BiFunction is null");
            return second -> function.apply(first, second);
        }

        static <T, U, R> Function<T, R> push(java.util.function.BiFunction<T, U, R> function, U last) {
            requireNonNull(function, "BiFunction is null");
            return first -> function.apply(first, last);
        }

        static <T, U, R> Function<U, R> take(java.util.function.BiFunction<T, U, R> function) {
            requireNonNull(function, "BiFunction is null");
            return second -> function.apply(null, second);
        }

        static <T, U, R> Function<T, R> pop(java.util.function.BiFunction<T, U, R> function) {
            requireNonNull(function, "BiFunction is null");
            return first -> function.apply(first, null);
        }

        @Override
        default Function<U, R> curly(T first) {
            return curly(this, first);
        }

        @Override
        default Function<U, R> take() {
            return take(this);
        }

        @Override
        default Function<T, R> push(U last) {
            return push(this, last);
        }

        @Override
        default Function<T, R> pop() {
            return pop(this);
        }
    }

    interface Function3<F, S, L, R> {

        static <F, S, L, R> BiFunction<S, L, R> curly(Function3<F, S, L, R> function, F first) {
            requireNonNull(function, "Function3 is null");
            return (second, last) -> function.apply(first, second, last);
        }

        static <F, S, L, R> BiFunction<F, S, R> push(Function3<F, S, L, R> function, L last) {
            requireNonNull(function, "Function3 is null");
            return (first, second) -> function.apply(first, second, last);
        }

        static <F, S, L, R> BiFunction<S, L, R> take(Function3<F, S, L, R> function) {
            requireNonNull(function, "Function3 is null");
            return (second, last) -> function.apply(null, second, last);
        }

        static <F, S, L, R> BiFunction<F, S, R> pop(Function3<F, S, L, R> function) {
            requireNonNull(function, "Function3 is null");
            return (first, second) -> function.apply(first, second, null);
        }

        R apply(F first, S second, L last);

    }

    interface Consumer<T> extends java.util.function.Consumer<T>, UnaryOperation<T, Runnable> {

        static <T> Runnable curly(java.util.function.Consumer<T> action, T value) {
            requireNonNull(action, "Consumer is null");
            return () -> action.accept(value);
        }

        static <T> Runnable take(java.util.function.Consumer<T> action) {
            requireNonNull(action, "Consumer is null");
            return () -> action.accept(null);
        }

        @Override
        default Runnable curly(T value) {
            return curly(this, value);
        }

        @Override
        default Runnable take() {
            return take(this);
        }
    }

    interface BiConsumer<T, U> extends java.util.function.BiConsumer<T, U>, Operation<T, Consumer<U>, U, Consumer<T>> {

        static <T, U> Consumer<U> curly(java.util.function.BiConsumer<T, U> action, T first) {
            requireNonNull(action, "BiConsumer is null");
            return second -> action.accept(first, second);
        }

        static <T, U> Consumer<T> push(java.util.function.BiConsumer<T, U> action, U last) {
            requireNonNull(action, "BiConsumer is null");
            return first -> action.accept(first, last);
        }

        static <T, U> Consumer<U> take(java.util.function.BiConsumer<T, U> action) {
            requireNonNull(action, "BiConsumer is null");
            return second -> action.accept(null, second);
        }

        static <T, U> Consumer<T> pop(java.util.function.BiConsumer<T, U> action) {
            requireNonNull(action, "BiConsumer is null");
            return first -> action.accept(first, null);
        }

        @Override
        default Consumer<U> curly(T first) {
            return curly(this, first);
        }

        @Override
        default Consumer<U> take() {
            return take(this);
        }

        @Override
        default Consumer<T> push(U last) {
            return push(this, last);
        }

        @Override
        default Consumer<T> pop() {
            return pop(this);
        }
    }

    interface Consumer3<F, S, L> {
        static <F, S, L> BiConsumer<S, L> curly(Consumer3<F, S, L> action, F first) {
            requireNonNull(action, "Consumer3 is null");
            return (second, last) -> action.accept(first, second, last);
        }

        static <F, S, L> BiConsumer<F, S> push(Consumer3<F, S, L> action, L last) {
            requireNonNull(action, "Consumer3 is null");
            return (first, second) -> action.accept(first, second, last);
        }

        static <F, S, L> BiConsumer<S, L> take(Consumer3<F, S, L> action) {
            requireNonNull(action, "Consumer3 is null");
            return (second, last) -> action.accept(null, second, last);
        }

        static <F, S, L> BiConsumer<F, S> pop(Consumer3<F, S, L> action) {
            requireNonNull(action, "Consumer3 is null");
            return (first, second) -> action.accept(first, second, null);
        }

        void accept(F first, S second, L last);
    }

    interface Boxable<T> {
        T boxed();
    }
}

