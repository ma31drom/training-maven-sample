package com.epam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ApplicationTest {

    @Test
    public void testMethod() {
        Assertions.assertEquals(4, Application.summ(2, 2));
    }

    @Test
    public void testFail() {
        //GIVEN
        Integer a = 5;
        Integer b = 0;

        //WHEN
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Application.div(a, b);
        });
        //THEN
        //exception
    }
}
