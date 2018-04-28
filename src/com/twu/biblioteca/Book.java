package com.twu.biblioteca;

public class Book {
    private String name;

    private String author;

    private int publishedYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Book(String name, String author, int publishedYear) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString(){
        return String.format("%-40s%-40s%-40s\n",name,author,publishedYear);
    }

    @Override
    public boolean equals(Object object){
        Book bookObject = (Book) object;
        if(null != object && this.name.equals(bookObject.getName())
            && this.author.equals(bookObject.getAuthor())
            && this.publishedYear == bookObject.getPublishedYear()) return true;
        return false;
    }
}
