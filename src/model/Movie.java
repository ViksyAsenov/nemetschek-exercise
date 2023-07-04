package model;

import java.util.List;
import java.util.Objects;

public record Movie(String title, String genre, String releaseDate, List<String> mainActors) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;

        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Movie: Title - \"" +
                title + "\" ; Genre - \"" +
                genre + "\" ; Release Date - \"" +
                releaseDate + "\" ; Main Actors - \"" +
                mainActors.toString().replace("[", "").replace("]", "") + "\"";
    }
}
