package com.twu.biblioteca;

public class Menu {

    private final static String WELCOME_MSG = "Welcome to Biblioteca!";
    private final static String OPTIONS_MENU = "1. List Books\n2. Check Out\n3. Return Book\n4. Quit\nPlease enter your choice(1～4):\n";
    private final static String OPTION_INVALID_PROMPT_MSG = "Select a valid option! Please select again.\n";
    private final static String OPTION_CHECK_OUT_PROMPT_MSG = "Please input book you want to check out,the format as:<book name>,author,publishedYear";
    private final static String CHECK_OUT_SUCCESS_MSG = "Thank you! Enjoy the book.\n";
    private final static String CHECK_OUT_FAIL_MSG = "That book is not available.\n";
    private final static String OPTION_RETURN_BOOK_PROMPT_MSG = "Please input book you want to return,the format as:<book name>,author,publishedYear";
    private final static String RETURN_BOOK_SUCCESS_MSG = "Thank you for returning the book.\n";
    private final static String RETURN_BOOK_FAIL_MSG = "That is not a valid book to return.\n";
    private final static String BOOK_INVALID_MSG = "That book information is invalid";
    private final static String OPTION_QUIT_MSG = "Goodbye! welcome to the next time!\n";

    private final static String LIST_BOOKS = "1";
    private final static String CHECK_OUT = "2";
    private final static String RETURN_BOOK = "3";
    private final static String QUIT = "4";

    private final static int INDEX_BOOK_NAME = 0;
    private final static int INDEX_AUTHOR = 1;
    private final static int INDEX_PUBLISH_YEAR = 2;

    private InputReader reader;
    private Librarian librarian;

    public Menu(InputReader reader) {
        this.reader = reader;
        librarian = new Librarian();
    }

    public void printWelcomeMsg() {
        System.out.println(WELCOME_MSG);
    }

    public void printAllOptions() {
        System.out.print(OPTIONS_MENU);
    }

    public boolean processingBusinessAccordingToOption () {
        printAllOptions();
        boolean result = true;
        switch (reader.readOption()) {
            case LIST_BOOKS:
                printBooksList();
                break;

            case CHECK_OUT:
                checkOut();
                break;

            case RETURN_BOOK:
                returnBook();
                break;

            case QUIT:
                result = false;
                break;

            default:
                System.out.print(OPTION_INVALID_PROMPT_MSG);
                break;
        }
        return result;

    }

    public void printBooksList() {
        System.out.println(librarian.getLibrary().toString());
    }

    public void checkOut() {
        while (true) {
            System.out.println(OPTION_CHECK_OUT_PROMPT_MSG);
            String bookInfo = reader.readBook();
            if(!bookInfo.equals(BOOK_INVALID_MSG)
                && librarian.checkOut(transformBookInfoToObjectBook(bookInfo))) {
                System.out.print(CHECK_OUT_SUCCESS_MSG);
                break;

            } else {
                System.out.print(CHECK_OUT_FAIL_MSG);
            }
        }
    }

    public void returnBook() {
        System.out.println(OPTION_RETURN_BOOK_PROMPT_MSG);
        String bookInfo = reader.readBook();
        if(!bookInfo.equals(BOOK_INVALID_MSG) && librarian.returnBook(transformBookInfoToObjectBook(bookInfo))) {
            System.out.print(RETURN_BOOK_SUCCESS_MSG);

        } else {
            System.out.print(RETURN_BOOK_FAIL_MSG);
        }
    }

    public void printGoodByeMsg() {
        System.out.print(OPTION_QUIT_MSG);
    }

    public void init() {
        printWelcomeMsg();
        while (processingBusinessAccordingToOption()) { }
        printGoodByeMsg();
    }

    public Book transformBookInfoToObjectBook(String bookInfo) {
        String[] bookInfoArray = bookInfo.split(",");
        return new Book(bookInfoArray[INDEX_BOOK_NAME].substring(1, bookInfoArray[INDEX_BOOK_NAME].length() - 1),
            bookInfoArray[INDEX_AUTHOR], Integer.valueOf(bookInfoArray[INDEX_PUBLISH_YEAR]));
    }

}
