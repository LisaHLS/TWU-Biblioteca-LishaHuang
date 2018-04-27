package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LibrarianTest {

    private Librarian librarian;
    private Book book;

    @Before
    public void setUp() {
        librarian = new Librarian();
        book = new Book("Head First Java","Kent Belt",2003);
    }

    @Test
    public void should_return_thank_you_enjoy_the_book_when_check_out_success() {
        assertEquals(librarian.checkOut(book), "Thank you! Enjoy the book");
    }

    @Test
    public void should_return_that_book_is_not_available_when_check_out_fail() {
        librarian.checkOut(book);
        assertEquals(librarian.checkOut(book), "That book is not available.");
    }
}
