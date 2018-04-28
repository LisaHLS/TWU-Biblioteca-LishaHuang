package com.twu.biblioteca;

public class Menu {

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
        System.out.print("1. List Books\n2. Checkout Book\n3. Return Book\n4. Quit\nPlease enter your choice(1～4):\n");
    }

    public boolean processingBusinessAccordingToOption () {
        return true;
    }

    public void printBooksList() {

    }

    public void printGoodByeMsg() {

    }

    public void checkOut() {

    }

    public void returnBook() {
    }

}
