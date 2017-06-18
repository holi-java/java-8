package com.holi.java8.functions.util;

import com.holi.java8.functions.Operation.Consumer3;
import com.holi.java8.functions.Operation.Predicate3;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Ops {
    public static int div(int left, long right) {
        return (int) (left / right);
    }

    public static Predicate<String> match1(String value) {
        return candidate -> Objects.equals(candidate, value);
    }

    public static BiPredicate<String, String> match2(String first, String second) {
        return (left, right) -> Objects.equals(left, first) && Objects.equals(right, second);
    }

    public static Predicate3<String, String, String> match3(String first, String second, String last) {
        return (left, mid, right) -> Objects.equals(left, first) && Objects.equals(mid, second) && Objects.equals(right, last);
    }

    public static String concat2(String first, String last) {
        return first + ":" + last;
    }

    public static String concat3(String first, String second, String last) {
        return concat2(first, concat2(second, last));
    }

    public static UnaryOperator<String> add1(String suffix) {
        return prefix -> concat2(prefix, suffix);
    }

    public static BiConsumer<String, String> add2(List<String> out) {
        return (first, last) -> out.add(concat2(first, last));
    }

    public static Consumer3<String, String, String> add3(List<String> out) {
        return (first, second, last) -> out.add(concat3(first, second, last));
    }
}
