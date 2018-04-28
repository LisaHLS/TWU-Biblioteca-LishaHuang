package com.twu.biblioteca;

public class Librarian {

    private Library library;

    public Librarian() {
        this.library = new Library();
    }

    public Library getLibrary() {
        return library;
    }

    public boolean checkOut(Book book){
        if(library.getBookList().contains(book)) {
            library.getBookList().remove(book);
            library.setBookList(library.getBookList());
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(!library.getBookList().contains(book)) {
            library.getBookList().add(book);
            library.setBookList(library.getBookList());
            return true;
        }
        return false;
    }
}
