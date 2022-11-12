package controller;

import entity.cinema.Cinema;
import entity.movie.Movie;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieManager implements Serializable {

	private ArrayList<Movie> movies;

	/**
	 * Constructor of Class MovieManager
	 */
	public MovieManager() {
		movies = new ArrayList<Movie>();
	}

	/**
	 * Overloading of the constructor of Class MovieManager with ArrayList parameter
	 * @param movies
	 */
	public MovieManager(ArrayList<Movie> movies) {
		this.movies = movies;
	}
	/**
	 * Accessor of the ArrayList of movie objects
	 * @return
	 */
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	/**
	 * Add a movie object into the ArrayList of movie objects
	 * @param movie
	 */
	public void addMovie(Movie movie) {
		movies.add(movie);
	}

	/**
	 * Remove a movie object from the ArrayList of movie objects according to the parameter id
	 * @param id
	 */
	public boolean removeMovie(int id) {
		// TODO - implement MovieManager.removeMovie
		try {
			movies.remove(id);
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("Movie does not exist at index %d", id);
			return false;
		}
	}

	/**
	 * Return a movie object from the ArrayList of movie objects according to the parameter id
	 * @param id
	 */
	public Movie getMovie(int id) {
		try {
			return movies.get(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("No movie at index %d", id);
		}
		return null;
	}

	/**
	 * Search a movie object from the ArrayList of movie objects according to the String parameter movie
	 * @param movie
	 */
	public Movie searchMovie(String movie) {
		try{
			for(int i = 0; i < movies.size(); i++) {
				Movie targetMovie = movies.get(i);
				if(targetMovie.getTitle().equals(movie)) {
					return targetMovie;
				}
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.printf("Movie cannot be found....");
		}

		return null;
	}


}
