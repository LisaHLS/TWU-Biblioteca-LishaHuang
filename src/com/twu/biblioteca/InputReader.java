package com.twu.biblioteca;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {

    private Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public String readOption() {
        String input = scanner.next().trim();
        return Pattern.compile("[1-4]").matcher(input).matches() ? input : "Select a valid option!";
    }

    public String readBook() {
        return null;
    }

}
