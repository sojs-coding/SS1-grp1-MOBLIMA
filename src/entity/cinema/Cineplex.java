package entity.cinema;
/**
 Represents Cineplex
 @author Samuel Ong
 @version 1.0
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
	private Cinema[] cinemas;
}

//SOLID
//Single Responsibility
//Open Closed Principle
//Liskov Substitution
//Interface Segregation
//Dependency Inversion