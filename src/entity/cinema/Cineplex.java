package entity.cinema;

import java.util.ArrayList;

/**
 Represents Cineplex
 @author Samuel Ong
 @version 1.1
 @since 2022-10-31
 */
public class Cineplex {

	/**
	 * The name of the Cinema company
	 */
	private String company;

	/**
	 * A list to store the Cinemas under the Cineplex company
	 */
	private ArrayList<Cinema> cinemas;

	/**
	 * Cineplex constructor
	 */
	public Cineplex() {
		cinemas = new ArrayList<Cinema>();
	}

	/**
	 * To add cinema to the Cineplex
	 * @param cinema Cinema to be added to Cineplex
	 */
	public void addCinema(Cinema cinema) {
		cinemas.add(cinema);
	}
}

//SOLID
//Single Responsibility
//Open Closed Principle
//Liskov Substitution
//Interface Segregation
//Dependency Inversion