import model.Movie;
import service.MovieLibrary;
import service.impl.MovieLibraryImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MovieLibrary movieLibrary = new MovieLibraryImpl();

        while(true) {
            try {
                printMainMenu();

                int option = isANumber(scanner.nextLine());

                if (option == 7) {
                    System.out.println("Have a nice day");
                    break;
                }

                chooseOptions(movieLibrary, option);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private static void printMainMenu() {
        String[] messages = {
                "Menu",
                "1.Add a movie",
                "2.Remove a movie",
                "3.Show all movies",
                "4.Sort movies by title",
                "5.Sort movies by actor",
                "6.Sort movies by genre",
                "7.Exit"
        };

        for(String message : messages) {
            System.out.println(message);
        }
    }

    private static void chooseOptions(MovieLibrary movieLibrary, int ans) {
        switch (ans) {
            //Add a movie
            case 1 -> {
                Movie movie = createMovie();

                if (movieLibrary.addMovie(movie)) {
                    System.out.println("Your movie was added");
                } else {
                    throw new RuntimeException("There is already a movie with the title \"" + movie.title() + "\"");
                }
            }
            //Remove a movie
            case 2 -> {
                System.out.println("What is the title of the movie you want to remove: ");
                String title = scanner.nextLine();

                if (movieLibrary.removeMovie(title)) {
                    System.out.println("Your movie was removed");
                } else {
                    throw new RuntimeException("Movie with title \"" + title + "\" wasn't found");
                }
            }
            //Get all movies
            case 3 -> {
                System.out.println("Here are all the movies: ");
                printMovieDetails(movieLibrary.getAllMovies());
            }
            //Get all movies sorted by title
            case 4 -> {
                System.out.println("Here are all the movies sorted by title: ");
                printMovieDetails(movieLibrary.getMoviesSortedByTitle());
            }
            //Get all movies sorted by an actor
            case 5 -> {
                System.out.println("Which actor do you want to sort by: ");
                String actor = scanner.nextLine();

                List<Movie> moviesSortedByActor = movieLibrary.getMoviesSortedByActor(actor);

                if(moviesSortedByActor.size() != 0) {
                    System.out.println("Here are all the movies sorted by the actor \"" + actor + "\": ");
                    printMovieDetails(moviesSortedByActor);
                } else {
                    throw new RuntimeException("No movies were found with the actor \"" + actor + "\"");
                }

            }
            //Get all movies sorted by genre
            case 6 -> {
                System.out.println("Here are all the movies sorted by the genre: ");
                printMovieDetails(movieLibrary.getMoviesSortedByGenre());
            }
            default -> System.out.println("Choose from the menu");
        }
    }

    //Create a movie by input
    private static Movie createMovie() {
        System.out.println("What is the title of the movie: ");
        String title = scanner.nextLine();

        System.out.println("What is the genre of the movie: ");
        String genre = scanner.nextLine();

        System.out.println("What is the release date of the movie: ");
        String releaseDate = scanner.nextLine();

        System.out.println("Who are the main actors of the movie (separate them with a comma): ");
        List<String> mainActors = Arrays.stream(scanner.nextLine().split(",")).toList();

        return new Movie(title, genre, releaseDate, mainActors);
    }

    //Prints details about a movie
    private static void printMovieDetails(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    //Check if input is a number
    private static int isANumber(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("You must enter a number");
        }
    }
}