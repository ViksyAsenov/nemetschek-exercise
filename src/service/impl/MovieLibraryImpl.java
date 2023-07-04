package service.impl;

import model.Movie;
import service.MovieLibrary;

import java.util.*;
import java.util.stream.Collectors;

public class MovieLibraryImpl implements MovieLibrary {
    private final Set<Movie> movies;
    private final String titleToBeFirst = "Matricata";

    public MovieLibraryImpl() {
        this.movies = new HashSet<>();
    }

    @Override
    public boolean addMovie(Movie movie) {
        if(movies.size() >= 132) {
            throw new RuntimeException("You have reached the limit of 132 movies, remove some before adding a new one");
        }

        return movies.add(movie);
    }

    @Override
    public boolean removeMovie(String title) {
        return movies.remove(new Movie(title, "", "", List.of()));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movies.stream().toList();
    }

    @Override
    public List<Movie> getMoviesSortedByTitle() {
        return movies
                .stream()
                .sorted(getTitleComparator())
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesSortedByActor(String actor) {
        return movies
                .stream()
                .filter(movie -> movie.mainActors().contains(actor))
                .sorted(getTitleComparator())
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesSortedByGenre() {
        return movies
                .stream()
                .sorted((movie1, movie2) ->
                        movie1.title().equals(titleToBeFirst) ? -1
                                : movie2.title().equals(titleToBeFirst) ? 1
                                : movie1.genre().compareTo(movie2.genre()))
                .collect(Collectors.toList());
    }

    private Comparator<Movie> getTitleComparator() {
        return Comparator.comparing(movie -> {
            if(movie.title().equals(titleToBeFirst)) {
                return "";
            } else {
                return movie.title();
            }
        });
    }
}
