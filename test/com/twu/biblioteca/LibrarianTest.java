package com.twu.biblioteca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LibrarianTest {

    private Librarian librarian;
    private Book book;
    private UserAccounts userAccounts;

    @Before
    public void setUp() {
        librarian = new Librarian();
        book = new Book("Head First Java","Kent Belt",2003);
        userAccounts = new UserAccounts();
        userAccounts.login("110-1234", "123456");

    }

    @Test
    public void should_return_true_and_add_check_out_record_when_check_out_book_success() {
        assertTrue(librarian.checkOut(book));
        assertTrue(librarian.getBookCheckOutRecord().containsKey(book)
            && librarian.getBookCheckOutRecord().containsValue(UserAccounts.currentLoginUser));
    }

    @Test
    public void should_return_false_when_check_out_fail() {
        librarian.checkOut(book);
        assertFalse(librarian.checkOut(book));
    }

    @Test
    public void should_return_true_and_remove_check_out_record_when_return_book_success() {
        librarian.checkOut(book);
        assertTrue(librarian.returnBook(book));
        assertTrue(!librarian.getBookCheckOutRecord().containsKey(book)
            && !librarian.getBookCheckOutRecord().containsValue(UserAccounts.currentLoginUser));
    }

    @Test
    public void should_return_false_when_return_book_fail() {
        assertFalse(librarian.returnBook(book));
    }

    @Test
    public void should_return_true_when_return_book_succeed_and_check_out_again() {
        librarian.checkOut(book);
        librarian.returnBook(book);
        assertTrue(librarian.checkOut(book));
        assertTrue(librarian.getBookCheckOutRecord().containsKey(book)
            && librarian.getBookCheckOutRecord().containsValue(UserAccounts.currentLoginUser));
    }

    @Test
    public void should_return_false_when_return_book_succeed_and_return_book_again() {
        librarian.checkOut(book);
        librarian.returnBook(book);
        assertFalse(librarian.returnBook(book));
        assertTrue(!librarian.getBookCheckOutRecord().containsKey(book)
            && !librarian.getBookCheckOutRecord().containsValue(UserAccounts.currentLoginUser));
    }
}
