package com.study.assignment4.lambda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@ExtendWith(SpringExtension.class)
public class lambdaExceptionTest {
    static void writeToFile(Integer integer) throws IOException {
        System.out.println(integer);
    }

    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.err.println("Arithmetic Exception occured : " + e.getMessage());
            }
        };
    }

    @FunctionalInterface
    public interface ThrwoingConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }

    static <T> Consumer<T> throwingConsumerWrapper(ThrwoingConsumer<T, Exception> thrwoingConsumer) {
        return i -> {
            try {
                thrwoingConsumer.accept(i);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    public void unCheckedExceptionHandle() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(i -> {
                    try {
                        System.out.println(50 / i);
                    } catch (ArithmeticException e) {
                        System.err.println("Arithmetic Exception occured : " + e.getMessage());
                    }
                }
        );
    }

    @Test
    public void unCheckedExceptionHandleWithWrapper() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(lambdaWrapper(integer -> System.out.println(50 / integer)));
    }


    @Test
    public void checkedExceptionHandle() {
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(throwingConsumerWrapper(i -> writeToFile(i)));
    }
}
