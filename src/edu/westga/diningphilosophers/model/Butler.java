package edu.westga.diningphilosophers.model;

import edu.westga.diningphilosophers.controller.DiningPhilosophersController;

/**
 * Butler class that acts as a semaphore of the dining philosophers problem
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class Butler {

	public static final int MAX_GUESTS_ALLOWED_AT_TABLE = DiningPhilosophersController.LIST_SIZE - 1;

	private int seats;

	/**
	 * The butler semaphore.
	 */
	public Butler() {
		this.seats = 0;
	}

	/**
	 * Called when one of the philosophers leaves the table.
	 */
	public synchronized void arise() {

		this.seats -= 1;
		this.notifyAll();
	}

	/**
	 * One of the philosophers sits down at the table.
	 */
	public synchronized void sitDown() {

		while (this.seats == MAX_GUESTS_ALLOWED_AT_TABLE) {
			try {
				this.wait();
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}

		this.seats += 1;
	}

}
