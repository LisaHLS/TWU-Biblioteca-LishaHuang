package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LibraryTest {

    private Library library;
    private String bookListInfo;

    @Before
    public void setUp() {
        library = new Library();
        String line = "==========================================================\n";
        bookListInfo = String.format("%-20s%-20s%-20s\n" + line,"Name","Author","PublishedYear")
           + String.format("%-20s%-20s%-20s\n","Head First Java","Kent Belt",2003)
            + String.format("%-20s%-20s%-20s\n","Test-Driven Development","Kent Belt",2004)
            + String.format("%-20s%-20s%-20s\n","Refactoring: Improving the Design","Martin Fowler",2010)
            + String.format("%-20s%-20s%-20s\n","Head First Servlets & JSP","O'Reilly",2010)
            + String.format("%-20s%-20s%-20s\n","Thinking in Java","Bruce Eckel ",2006)
        + String.format("%-20s%-20s%-20s\n","Effective Java","Joshua Bloch",2009);
    }

    @Test
    public void should_show_book_list_information() {
        assertEquals(library.toString(), bookListInfo);
    }
}
