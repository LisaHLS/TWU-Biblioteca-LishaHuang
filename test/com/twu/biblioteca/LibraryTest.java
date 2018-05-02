package com.twu.biblioteca;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LibraryTest {

    private Library library;
    private String bookListInfo;
    private String movieListInfo;

    @Before
    public void setUp() {
        library = new Library();
        String line = "=========================================================================================\n";
        bookListInfo = String.format("%-25s%-35s%-30s\n" + line,"Name","Author","PublishedYear")
            + String.format("%-40s%-40s%-40s\n","Head First Java","Kent Belt",2003)
            + String.format("%-40s%-40s%-40s\n","Test-Driven Development","Kent Belt",2004)
            + String.format("%-40s%-40s%-40s\n","Refactoring: Improving the Design","Martin Fowler",2010)
            + String.format("%-40s%-40s%-40s\n","Head First Servlets & JSP","O'Reilly",2010)
            + String.format("%-40s%-40s%-40s\n","Thinking in Java","Bruce Eckel ",2006)
            + String.format("%-40s%-40s%-40s\n","Effective Java","Joshua Bloch",2009);

        movieListInfo = String.format("%-30s%-30s%-20s%-30s\n" + line,"Name","Year","Director","MovieRating")
            + String.format("%-30s%-30s%-25s%-30s\n","The Great Buddha",2017,"Xinyao Huang","9")
            + String.format("%-30s%-30s%-25s%-30s\n","Wonder", 2017, "Stephen Jobo", "9")
            + String.format("%-30s%-30s%-25s%-30s\n","The Post", 2017, "Steven Spielberg", "8")
            + String.format("%-30s%-30s%-25s%-30s\n","Lady Bird", 2017, "Greta Geerwig", "8")
            + String.format("%-30s%-30s%-25s%-30s\n","The Faces of My Gene", 2018, "Degang Guo", "4");
    }

    @Test
    public void should_show_book_list_information() {
        assertEquals(library.toBooksString(), bookListInfo);
    }

    @Test
    public void should_show_movie_list_information() {
        assertEquals(library.toMoviesString(), movieListInfo);
    }
}
