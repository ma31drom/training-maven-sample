package com.epam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


        StringBuffer stringBuffer = new StringBuffer("My concatenated string: ");

        String[] strings = {"Weather", "is", "wonderful", "today"};

        for (String part : strings) {
            stringBuffer.append(part);
            stringBuffer.append(' ');
        }
        String x = stringBuffer.toString();
        System.out.println(x);
    }


    @Test
    public void testPattern() {
        //GIVEN
        String matchesPattern = "Hello Pattern 789 \n password:skjdgh -aleiht !23!j\n ;aslfhgkfa ;asiodyufk";
        //String doesNotMatchPattern = "Hello Pattern";

        Pattern pattern = Pattern.compile("[^\n].*[\n$]");

        Matcher matcher = pattern.matcher(matchesPattern);

        matcher.results().forEach(group -> System.out.println(group.group()));

    }

    @Test
    public void dates() {
        Date currentDate = new Date();

        Locale locale = new Locale("be", "BY");
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

        Locale[] availableLocales = SimpleDateFormat.getAvailableLocales();

        Period d = Period.between(LocalDate.EPOCH, LocalDate.now());

        LocalDateTime now = LocalDateTime.now();

        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("America/Chicago"));

        String format = dateTimeInstance.format(currentDate);

        System.out.println(locale);
        System.out.println(format);
    }


}
