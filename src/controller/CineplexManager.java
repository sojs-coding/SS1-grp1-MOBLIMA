package controller;

import entity.cinema.Cineplex;

import java.io.Serializable;
import java.util.ArrayList;
/**
 Represents a Manager for all the Cineplexes
 @author Samuel Ong
 @version 1.0
 @since 2022-11-2
 */
public class CineplexManager implements Serializable {
    ArrayList<Cineplex> cineplexes;

    public CineplexManager() {
        cineplexes = new ArrayList<Cineplex>();
    }

    public void addCineplex(Cineplex cineplex) {
        cineplexes.add(cineplex);
    }

    public ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }

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
