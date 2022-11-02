package entity.movie;

import java.io.Serializable;

public class Movie implements Serializable {

	public Movie(String title, ShowingStatus status, String synopsis, float overallRating, MovieType type, String director, String[] casts) {
		this.title = title;
		this.status = status;
		this.synopsis = synopsis;
		this.overallRating = overallRating;
		this.type = type;
		this.director = director;
		this.casts = casts;
	}

	private String title;
	private ShowingStatus status;
	private String synopsis;
	private float overallRating;
	private MovieType type;
	private String director;
	private String[] casts;

}