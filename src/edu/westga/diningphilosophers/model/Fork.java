package edu.westga.diningphilosophers.model;

/**
 * Fork class for the dining philosophers problem.
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 * 
 */
public class Fork {

	private boolean availalbe;

	/**
	 * Fork class that initializes the availalbity to true by default
	 */
	public Fork() {

		this.availalbe = true;
	}

	/**
	 * Returns true if fork is available, false otherwise
	 * 
	 * @return true if fork is available, false otherwise
	 */
	public boolean isAvailable() {
		return this.availalbe;
	}

	/**
	 * Lifts a fork.
	 * 
	 * @Precondition: none
	 * @Postcondition: the fork is lifted.
	 */
	public synchronized void lift() {

		if (!this.availalbe) {
			this.makeThreadWait();
		}

		this.availalbe = false;
		this.sleepForFiftyMillis();
	}

	private void makeThreadWait() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sleepForFiftyMillis() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Puts a fork down.
	 * 
	 * @Precondition: fork is in use
	 * @Postcondition: fork is now available
	 */
	public synchronized void putDown() {

		if (this.availalbe) {
			throw new IllegalStateException("Fork is already available");
		}

		this.availalbe = true;
		this.notifyAll();
	}

}
