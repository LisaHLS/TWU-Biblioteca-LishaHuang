package com.twu.biblioteca;

import java.util.List;

public class Librarian {

    private Library library;

    public Librarian() {
        this.library = new Library();
    }

    public String checkOut(Book book){
        if(library.getBookList().contains(book)) {
            library.getBookList().remove(book);
            library.setBookList(library.getBookList());
            return "Thank you! Enjoy the book";
        }
        return "That book is not available.";
    }

    public String returnBook(Book book){
        if(!library.getBookList().contains(book)) {
            library.getBookList().add(book);
            library.setBookList(library.getBookList());
            return "Thank you for returning the book.";
        }
        return "That is not a valid book to return.";
    }
}
