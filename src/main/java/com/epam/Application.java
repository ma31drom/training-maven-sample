package com.epam;


import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello java!");
        Logger.getLogger("basic").info("Hello logger!");
    }

    public static Integer summ(Integer a, Integer b) {
        return a + b;
    }
}