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
