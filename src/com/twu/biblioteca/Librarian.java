package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class Librarian {

    private Library library;
    private Map<String, String> bookCheckOutRecord;

    public Librarian() {
        this.library = new Library();
        bookCheckOutRecord = new HashMap<>();
    }

    public Library getLibrary() {
        return library;
    }

    public Map<String, String> getBookCheckOutRecord() {
        return bookCheckOutRecord;
    }

    public boolean checkOutBook(Book book){
        if(library.getBookList().contains(book)) {
            library.getBookList().remove(book);
            library.setBookList(library.getBookList());
            bookCheckOutRecord.put(book.getName(),UserAccounts.currentLoginUser.getLibraryNumber());
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(!library.getBookList().contains(book)) {
            library.getBookList().add(book);
            library.setBookList(library.getBookList());
            bookCheckOutRecord.remove(book.getName(),UserAccounts.currentLoginUser.getLibraryNumber());
            return true;
        }
        return false;
    }

    public boolean checkOutMovie(Movie movie) {
        if(library.getMovieList().contains(movie)) {
            library.getMovieList().remove(movie);
            library.setMovieList(library.getMovieList());
            return true;
        }
        return false;
    }

    public String showBookCheckOutRecord() {
        StringBuilder builder = new StringBuilder();
        bookCheckOutRecord.forEach((book,user)->builder.append("book: " + book).append(", user: " + user + "\n"));
        return builder.toString();
    }

}
