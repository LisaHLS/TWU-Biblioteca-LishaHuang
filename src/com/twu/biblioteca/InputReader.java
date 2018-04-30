package com.twu.biblioteca;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {

    private Scanner scanner;
    private static final String CHECK_OPTION_INVALID_REG = "[1-4]";
    private static final String CHECK_BOOK_INVALID_REG = "<(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*>,(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*,[1-9]\\d*";
    private static final String CHECK_USER_LOGIN_INFO_INVALID_REG = "\\d{3}-\\d{4},\\d*";

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public String readOption() {
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_OPTION_INVALID_REG) ? input : "Select a valid option!";
    }

    public String readBook() {
        scanner.useDelimiter("\n");
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_BOOK_INVALID_REG) ? input : "That book information is invalid";
    }

    public boolean isInputInvalid(String input, String reg) {
        return Pattern.compile(reg).matcher(input).matches();
    }

    public String readLibraryNumberAndPassword() {
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_USER_LOGIN_INFO_INVALID_REG) ? input : "That libraryNumber or password is invalid";
    }

    public String readChooseUserOrLibrarian(){
        return null;
    }
}
