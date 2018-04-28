package com.twu.biblioteca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(librarian.checkOut(book));
    }

    @Test
    public void should_return_that_book_is_not_available_when_check_out_fail() {
        librarian.checkOut(book);
        assertFalse(librarian.checkOut(book));
    }

    @Test
    public void should_return_thank_you_for_returning_the_book_when_return_book_success() {
        librarian.checkOut(book);
        assertTrue(librarian.returnBook(book));
    }

    @Test
    public void should_return_that_is_not_a_valid_book_to_return_when_return_book_fail() {
        assertFalse(librarian.returnBook(book));
    }

}
