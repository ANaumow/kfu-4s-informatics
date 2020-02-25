package ru.naumow.task.interpretator.core.util;

public class Commands {
    public static void requireCount(String[] args, int i) {
        if (args.length != i)
            throw new IllegalArgumentException("Not enough arguments: expected " + i + ", found: " + args.length);
    }

    public static void requireCountOrGreater(String[] args, int i) {
        if (args.length < i)
            throw new IllegalArgumentException("Not enough arguments: expected greater than " + i + ", found: " + args.length);
    }



}
