package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        book = new Book("Head First Java","Kent Belt",2003);
    }

    @Test
    public void should_book_have_name_author_publishedYear() {
        assertEquals(book.toString(), String.format("%-40s%-40s%-40s\n","Head First Java","Kent Belt",2003));
    }
}
