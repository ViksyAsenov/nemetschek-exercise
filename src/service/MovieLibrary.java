package service;

import model.Movie;

import java.util.List;

public interface MovieLibrary {
    boolean addMovie(Movie movie);
    boolean removeMovie(String title);
    List<Movie> getAllMovies();
    List<Movie> getMoviesSortedByTitle();
    List<Movie> getMoviesSortedByActor(String actor);
    List<Movie> getMoviesSortedByGenre();
}
