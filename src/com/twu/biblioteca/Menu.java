package com.twu.biblioteca;

public class Menu {

    public final static String LIST_BOOKS = "1";

    public final static String CHECK_OUT = "2";

    public final static String RETURN_BOOK = "3";

    public final static String QUIT = "4";

    private InputReader reader;

    private Library library;

    public Menu(InputReader reader) {
        this.reader = reader;
        library = new Library();
    }

    public void printWelcomeMsg() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void printAllOptions() {
        System.out.print("1. List Books\n2. Check Out\n3. Return Book\n4. Quit\nPlease enter your choice(1～4):\n");
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
                System.out.print("Select a valid option! Please select again.\n");
                break;
        }
        return result;

    }

    public void printBooksList() {

    }

    public void checkOut() {

    }

    public void returnBook() {
    }

    public void printGoodByeMsg() {
        System.out.print("Goodbye! welcome to the next time!\n");
    }

    public void init() {
        printWelcomeMsg();
        while (processingBusinessAccordingToOption()) { }
        printGoodByeMsg();
    }
}
