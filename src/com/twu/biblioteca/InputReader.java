package com.twu.biblioteca;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {

    private Scanner scanner;
    private static final String CHECK_USER_OPTION_INVALID_REG = "[1-8]";
    private static final String CHECK_BOOK_INVALID_REG = "<(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*>,(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*,[1-9]\\d*";
    private static final String CHECK_USER_LOGIN_INFO_INVALID_REG = "\\d{3}-\\d{4},\\d*";
    private static final String CHECK_CHOOSE_USER_OR_LIBRARIAN_INVALID_REG = "[1-3]";
    private static final String CHECK_LIBRARIAN_OPTION_INVALID_REG = "[1-3]";
    private static final String CHECK_MOVIE_INVALID_REG = "<(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*>,[1-9]\\d*,(([\\u4e00-\\u9fa5])|([a-zA-Z])|\\s)*";

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public String readUserOption() {
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_USER_OPTION_INVALID_REG) ? input : "Select a valid option!";
    }

    public String readBook() {
        String input = scanner.nextLine().trim();
        return isInputInvalid(input, CHECK_BOOK_INVALID_REG) ? input : "That book information is invalid";
    }

    public String readLibraryNumberAndPassword() {
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_USER_LOGIN_INFO_INVALID_REG) ? input : "That libraryNumber or password is invalid";
    }

    public String readChooseUserOrLibrarian(){
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_CHOOSE_USER_OR_LIBRARIAN_INVALID_REG) ? input : "That choose is invalid";
    }

    public String readLibrarianOption() {
        String input = scanner.next().trim();
        return isInputInvalid(input, CHECK_LIBRARIAN_OPTION_INVALID_REG) ? input : "Select a valid option!";
    }

    public String readMovie() {
        String input = scanner.nextLine().trim();
        return isInputInvalid(input, CHECK_MOVIE_INVALID_REG) ? input : "That movie information is invalid";
    }

    private boolean isInputInvalid(String input, String reg) {
        return Pattern.compile(reg).matcher(input).matches();
    }
}
