package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

    public static final String ISOLATING_LINE  = "=========================================================================================\n";
    private List<Book> bookList;

    private List<Movie> movieList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Library() {

        bookList = new ArrayList<Book>(){{
            add(new Book("Head First Java", "Kent Belt",2003));
            add(new Book("Test-Driven Development", "Kent Belt",2004));
            add(new Book("Refactoring: Improving the Design", "Martin Fowler",2010));
            add(new Book("Head First Servlets & JSP", "O'Reilly",2010));
            add(new Book("Thinking in Java", "Bruce Eckel",2006));
            add(new Book("Effective Java", "Joshua Bloch",2009));
        }};

        movieList = new ArrayList<Movie>() {{
            add(new Movie("The Great Buddha", 2017, "Xinyao Huang", "9"));
            add(new Movie("Wonder", 2017, "Stephen Jobo", "9"));
            add(new Movie("The Post", 2017, "Steven Spielberg", "8"));
            add(new Movie("Lady Bird", 2017, "Greta Geerwig", "8"));
            add(new Movie("The Faces of My Gene", 2018, "Degang Guo", "4"));
        }};
    }

    public String toBooksString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-25s%-35s%-30s\n" + ISOLATING_LINE,"Name","Author","PublishedYear"));
        bookList.forEach(item -> builder.append(item.toString()));
        return builder.toString();
    }

    public String toMoviesString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-30s%-30s%-20s%-30s\n" + ISOLATING_LINE,"Name","Year","Director","MovieRating"));
        movieList.forEach(item -> builder.append(item.toString()));
        return builder.toString();
    }
}
