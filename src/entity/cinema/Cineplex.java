package entity.cinema;

import java.io.Serializable;
import java.util.ArrayList;

/**
 Represents a Cineplex that has multiple Cinemas
 @author Samuel Ong
 @version 1.2
 @since 2022-10-31
 */
public class Cineplex implements Serializable {

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

	public Cinema getCinema(int id) {
		try {
			return cinemas.get(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("No cinema at index %d\n", id);
		}
		return null;
	}

	public Cinema findCinema(char[] code) {
		for (int i = 0; i < cinemas.size(); i++) {
			String strCode = new String(cinemas.get(i).getCode());
			String other = new String(code);
			if (strCode.equals((other))) {
				return cinemas.get(i);
			}
		}
		return null;
	}

	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
}

//SOLID
//Single Responsibility
//Open Closed Principle
//Liskov Substitution
//Interface Segregation
//Dependency Inversion