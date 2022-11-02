package controller;

import entity.movie.Movie;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieManager implements Serializable {

	private ArrayList<Movie> movies;

	public MovieManager() {
		movies = new ArrayList<Movie>();
	}

	/**
	 * 
	 * @param movie
	 */
	public void addMovie(Movie movie) {
		movies.add(movie);
	}

	/**
	 * 
	 * @param id
	 */
	public boolean removeMovie(int id) {
		// TODO - implement MovieManager.removeMovie
		throw new UnsupportedOperationException();
	}

	/**
	 * 
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
	 * 
	 * @param movie
	 */
	public void searchMovie(String movie) {
		// TODO - implement MovieManager.searchMovie
		throw new UnsupportedOperationException();
	}

}