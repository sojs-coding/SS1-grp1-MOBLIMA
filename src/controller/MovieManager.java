package controller;

import entity.cinema.Cinema;
import entity.movie.Movie;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieManager implements Serializable {

	private ArrayList<Movie> movies;

	public MovieManager() {
		movies = new ArrayList<Movie>();
	}

	public ArrayList<Movie> getMovies() {
		return movies;
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
		try {
			movies.remove(id);
			return true;
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("Movie does not exist at index %d", id);
			return false;
		}
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
	public Movie searchMovie(String movie) {
		try{
			for(int i = 0; i < movies.size(); i++) {
				Movie targetMovie = movies.get(i);
				if(targetMovie.getTitle() == movie) {
					return targetMovie;
				}
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.printf("Movie cannot be found....");
		}
		return null;
	}


}
