package edu.westga.diningphilosophers.controller;

/**
 * Application class that contains main method.
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class Application {

	/**
	 * Main method that serves as the entry point of the program
	 * 
	 * @param args
	 *            The arguments from command line
	 */
	public static void main(String[] args) {
		DiningPhilosophersController controller = new DiningPhilosophersController();
		controller.startDinnerParty();
	}

}
