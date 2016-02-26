/**
 * 
 */
package edu.westga.diningphilosophers.model;

/**
 * Butler class that acts as a semaphore of the dining philosophers problem
 * 
 * @author danielburkhart
 *
 */
public class Butler {

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

		if (this.seats == 4) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.seats += 1;

	}

}
