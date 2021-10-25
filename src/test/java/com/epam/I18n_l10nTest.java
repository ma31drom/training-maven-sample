package com.epam;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n_l10nTest {


    @Test
    public void testEnTranslations() {
        Locale locale = Locale.getDefault();

        ResourceBundle rs = ResourceBundle.getBundle("messages", locale);

        System.out.println(rs.getString("main.hello"));
        System.out.format(rs.getString("main.lang"), locale.toString());
        System.out.println();
        System.out.println();
    }

    @Test
    public void testBYTranslations() {
        Locale locale = new Locale("be", "BY");
        System.out.println(locale);

        ResourceBundle rs = ResourceBundle.getBundle("messages", locale);

        System.out.println(rs.getString("main.hello"));
        System.out.format(rs.getString("main.lang"), locale.toString());
        System.out.println();
        System.out.println();
    }

    @Test
    public void testRuTranslations() {
        Locale locale = new Locale("ru", "RU");
        System.out.println(locale);

        ResourceBundle rs = ResourceBundle.getBundle("messages", locale);

        System.out.println(rs.getString("main.hello"));
        System.out.format(rs.getString("main.lang"), locale.toString());
        System.out.println();
        System.out.println();
    }
}
