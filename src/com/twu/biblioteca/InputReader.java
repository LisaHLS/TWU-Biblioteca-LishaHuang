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
        scanner.useDelimiter("\n");
        String input = scanner.next().trim();
        String reg = "<(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*>,(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*,[1-9]\\d*";
        return Pattern.compile(reg).matcher(input).matches() ? input : "That book information is invalid";
    }

}
