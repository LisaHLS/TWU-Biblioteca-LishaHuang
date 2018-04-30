package com.twu.biblioteca;

public class Movie {

    private String name;

    private int year;

    private String director;

    private String movieRating;

    public Movie(String name, int year, String director, String movieRating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    @Override
    public String toString(){
        return String.format("%-40s%-40s%-40s%-40s\n",name,year,director,movieRating);
    }

    @Override
    public boolean equals(Object object){
        Movie movieObject = (Movie) object;
        if(null != object && this.name.equals(movieObject.getName())
            && this.year == movieObject.getYear()
            && this.director.equals(movieObject.getDirector())) return true;
        return false;
    }
}
