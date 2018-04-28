package com.twu.biblioteca;

public class Menu {

    public final static String LIST_BOOKS = "1";

    public final static String CHECK_OUT = "2";

    public final static String RETURN_BOOK = "3";

    public final static String QUIT = "4";

    private InputReader reader;

    private Librarian librarian;

    public Menu(InputReader reader) {
        this.reader = reader;
        librarian = new Librarian();
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
        System.out.println(librarian.getLibrary().toString());
    }

    public void checkOut() {
        while (true) {
            System.out.println("Please input the book name you want to check out:");
            String[] bookInfo = reader.readBook().split(",");
            Book book = new Book(bookInfo[0].substring(1,bookInfo[0].length()-1), bookInfo[1], Integer.valueOf(bookInfo[2]));
            if (librarian.checkOut(book).equals("Thank you! Enjoy the book")) {
                System.out.println("Thank you! Enjoy the book");
                break;

            }else{
                System.out.print("That book is not available.\n");
            }
        }
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
