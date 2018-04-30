package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class Librarian {

    private Library library;
    private Map<Book, User> bookCheckOutRecord;

    public Librarian() {
        this.library = new Library();
        bookCheckOutRecord = new HashMap<>();
    }

    public Library getLibrary() {
        return library;
    }

    public Map<Book, User> getBookCheckOutRecord() {
        return bookCheckOutRecord;
    }

    public boolean checkOut(Book book){
        if(library.getBookList().contains(book)) {
            library.getBookList().remove(book);
            library.setBookList(library.getBookList());
            bookCheckOutRecord.put(book,UserAccounts.currentLoginUser);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(!library.getBookList().contains(book)) {
            library.getBookList().add(book);
            library.setBookList(library.getBookList());
            bookCheckOutRecord.remove(book,UserAccounts.currentLoginUser);
            return true;
        }
        return false;
    }

    public boolean checkOutMovie(Movie movie) {
        return false;
    }

}
