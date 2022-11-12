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
	public Cineplex(String company) {
		this.company = company;
		cinemas = new ArrayList<Cinema>();
	}

	/**
	 * To add cinema to the Cineplex
	 * @param cinema Cinema to be added to Cineplex
	 */
	public void addCinema(Cinema cinema) {
		cinemas.add(cinema);
	}
	/**
	 * @returns cinema of the cineplex by a certain id
	 * @param id the index id of the cinema in the cineplex
	 */
	public Cinema getCinema(int id) {
		try {
			return cinemas.get(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.printf("No cinema at index %d\n", id);
		}
		return null;
	}
	/**
	* @returns cinema of cineplex based on the code of the cinema
	* @param code The cinema code
	 */
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
	/**
	 * @returns all the cinemas in cineplex
	 */
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}
	/**
	 * @returns company of cineplex
	 */
	public String getCompany() {
		return company;
	}

	
}

//SOLID
//Single Responsibility
//Open Closed Principle
//Liskov Substitution
//Interface Segregation
//Dependency Inversion