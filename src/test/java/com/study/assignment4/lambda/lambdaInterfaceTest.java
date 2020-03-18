package com.study.assignment4.lambda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.function.*;

@ExtendWith(SpringExtension.class)
public class lambdaInterfaceTest {
    @Test
    public void runnalbe() {
        Runnable runnable = () -> System.out.println("run");
        runnable.run();
    }

    @Test
    public void supplier() {
        Supplier<String> getString = () -> "Supplier";
        String test = getString.get();
        System.out.println(test);
    }

    @Test
    public void consumer() {
        Consumer<String> stringConsumer = e -> System.out.println("e는 " + e + "다");
        Consumer<String> andThenConsumer = e -> System.out.println("e는 " + e + "아니다");
        stringConsumer.accept("테스트");
        stringConsumer.andThen(andThenConsumer).accept("테스트");
    }

    @Test
    public void biConsumer() {
        BiConsumer<String, String> biConsumer = (t, u) -> System.out.println(t + " 와 " + u);
        biConsumer.accept("t", "u");
    }

    @Test
    public void function() {
        Function<Integer, Integer> mutiple = (e) -> e * 2;
        Function<Integer, Integer> add = (e) -> e + 2;

        Function<Integer, Integer> compose = mutiple.compose(add);
        Integer result = compose.apply(3);
    }

    @Test
    public void biFunction() {
        BiFunction<String, Integer, String> biFunction = (name, num) -> String.valueOf(num) + "살" + name;
        String name = biFunction.apply("로미", 4);
    }

    @Test
    public void unaryOperator() {
        UnaryOperator<String> unaryOperator = e -> e + "하세요";
        String result = unaryOperator.apply("안녕");
    }

    @Test
    public void binaryOperator() {
        BinaryOperator<String> binaryOperator = (e, u) -> e + u;
        String result = binaryOperator.apply("안녕", "하세요");
    }

    @Test
    public void predicate() {
        Predicate<Integer> predicate = num -> num > 5;
        predicate.test(5);
    }

    @Test
    public void BiPredicate() {
        BiPredicate<Integer, Integer> biPredicate = (e, u) -> e == u;
        biPredicate.test(3, 3);
    }
}
