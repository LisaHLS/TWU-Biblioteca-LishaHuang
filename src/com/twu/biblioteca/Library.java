package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

    public static final String ISOLATING_LINE  = "==========================================================\n";
    private List<Book> bookList;

    public Library() {

        bookList = new ArrayList<Book>(){{
            add(new Book("Head First Java", "Kent Belt",2003));
            add(new Book("Test-Driven Development", "Kent Belt",2004));
            add(new Book("Refactoring: Improving the Design", "Martin Fowler",2010));
            add(new Book("Head First Servlets & JSP", "O'Reilly",2010));
            add(new Book("Thinking in Java", "Bruce Eckel",2006));
            add(new Book("Effective Java", "Joshua Bloch",2009));
        }};
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-20s%-20s%-20s\n" + ISOLATING_LINE,"Name","Author","PublishedYear"));
        bookList.forEach(item -> builder.append(item.toString()));
        return builder.toString();
    }
}
