package controller;

import entity.cinema.Cineplex;

import java.io.Serializable;
import java.util.ArrayList;
/**
 Represents a Manager for all the Cineplexes
 @author Samuel Ong
 @version 1.1
 @since 2022-11-13
 */
public class CineplexManager implements Serializable {
	/**
	 * Represents the cineplexes in the Manager
	 */
    ArrayList<Cineplex> cineplexes;

	/**
	 * The Constructor for the manager
	 * Instantiates an ArrayList
	 */
    public CineplexManager() {
        cineplexes = new ArrayList<Cineplex>();
    }

	/**
	 * Adds the cineplex into the manager
	 * @param cineplex Cineplex Object to be added
	 */
    public void addCineplex(Cineplex cineplex) {
        cineplexes.add(cineplex);
    }

	/**
	 * Get the list of Cineplex
	 * @return ArrayList<Cineplex>
	 */
    public ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }

	/**
	 * Get the Cineplex based on it's Company name
	 * @param company Company name for the Cineplex
	 * @return The Cineplex
	 */
    public Cineplex searchCompany(String company) {
		try{
			for(int i = 0; i < cineplexes.size(); i++) {
				Cineplex targetCineplex = cineplexes.get(i);
				if(targetCineplex.getCompany().equals(company)) {
					return targetCineplex;
				}
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.printf("Cineplex cannot be found....");
		}
		return null;
	}

    
}
