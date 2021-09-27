package com.epam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    public void testMethod() {
        Assertions.assertEquals(4, Application.summ(2, 2));
    }

}
