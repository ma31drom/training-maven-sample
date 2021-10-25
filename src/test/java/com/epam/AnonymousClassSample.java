package com.epam;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AnonymousClassSample {

    @Test
    public void sample() {

        List<String> strings = Arrays.asList("one", "one two", "three");

        List<String> result = strings.stream()
                .filter(s -> {
                    System.out.println(s);
                    return s.contains("one");
                })
                .map(s -> s.concat(" result"))
                .collect(Collectors.toList());

        System.out.println(result);

    }


}
