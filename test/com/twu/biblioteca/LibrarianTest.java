package com.twu.biblioteca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LibrarianTest {

    private Librarian librarian;
    private Book book;
    private UserAccounts userAccounts;
    private Movie movie;

    @Before
    public void setUp() {
        librarian = new Librarian();
        book = new Book("Head First Java","Kent Belt",2003);
        userAccounts = new UserAccounts();
        userAccounts.login("110-1234", "123456");
        movie = new Movie("The Great Buddha", 2017, "Xinyao Huang", "9");

    }

    @Test
    public void should_return_true_and_add_check_out_record_when_check_out_book_success() {
        assertTrue(librarian.checkOutBook(book));
        assertTrue(librarian.getBookCheckOutRecord().containsKey("Head First Java")
            && librarian.getBookCheckOutRecord().containsValue("110-1234"));
    }

    @Test
    public void should_return_false_when_check_out_book_fail() {
        librarian.checkOutBook(book);
        assertFalse(librarian.checkOutBook(book));
    }

    @Test
    public void should_return_true_and_remove_check_out_record_when_return_book_success() {
        librarian.checkOutBook(book);
        assertTrue(librarian.returnBook(book));
        assertTrue(!librarian.getBookCheckOutRecord().containsKey("Head First Java")
            && !librarian.getBookCheckOutRecord().containsValue("110-1234"));
    }

    @Test
    public void should_return_false_when_return_book_fail() {
        assertFalse(librarian.returnBook(book));
    }

    @Test
    public void should_return_true_when_return_book_succeed_and_check_out_again() {
        librarian.checkOutBook(book);
        librarian.returnBook(book);
        assertTrue(librarian.checkOutBook(book));
        assertTrue(librarian.getBookCheckOutRecord().containsKey("Head First Java")
            && librarian.getBookCheckOutRecord().containsValue("110-1234"));
    }

    @Test
    public void should_return_false_when_return_book_succeed_and_return_book_again() {
        librarian.checkOutBook(book);
        librarian.returnBook(book);
        assertFalse(librarian.returnBook(book));
        assertTrue(!librarian.getBookCheckOutRecord().containsKey("Head First Java")
            && !librarian.getBookCheckOutRecord().containsValue("110-1234"));
    }

    @Test
    public void should_return_true_when_check_out_movie_succeed() {
        assertTrue(librarian.checkOutMovie(movie));
    }

    @Test
    public void should_return_false_when_check_out_movie_fail() {
        librarian.checkOutMovie(movie);
        assertFalse(librarian.checkOutMovie(movie));
    }

    @Test
    public void should_show_book_check_out_record_when_books_checked_out() {
        librarian.checkOutBook(book);
        assertTrue(librarian.showBookCheckOutRecord().equals("book: Head First Java, user: 110-1234\n"));
    }

}
