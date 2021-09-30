package com.epam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ParametrizedApplicationTest {

    @ParameterizedTest
    @MethodSource("summParameters")
    public void multiSummTest(Integer a, Integer b, Integer result) {
        //GIVEN
        Integer firstArg = a;
        Integer secondArg = b;
        Integer resultingValue = result;

        //WHEN
        Integer summ = Application.summ(firstArg, secondArg);

        //THEN
        Assertions.assertEquals(result, summ);
    }

    public static Stream summParameters() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(2, 3, 5),
                Arguments.of(2765, 235, 3000)
        );
    }

}
