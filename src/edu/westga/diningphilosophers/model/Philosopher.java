package edu.westga.diningphilosophers.model;

/**
 * Philosopher class that thinks and eats
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class Philosopher implements Runnable {

	private Chopstick firstChopstick;
	private Chopstick secondChopstick;
	private String name;
	private boolean keepWorking;
	private Butler butler;

	private Philosopher() {
		this.keepWorking = true;
	}

	/**
	 * Philosopher constructor that initializes everything.
	 * 
	 * @param name
	 *            The name of the philosopher
	 * @param first
	 *            The first chopstick
	 * @param second
	 *            The second chopstick
	 * @param butler
	 *            The butler to manage the dinner party
	 */
	public Philosopher(String name, Chopstick first, Chopstick second, Butler butler) {

		this();

		if (first == null) {
			throw new IllegalArgumentException("First fork is null");
		} else if (second == null) {
			throw new IllegalArgumentException("Second fork is null");
		} else if (name == null) {
			throw new IllegalArgumentException("Name is null");
		} else if (butler == null) {
			throw new IllegalArgumentException("Butler is null");
		}

		this.firstChopstick = first;
		this.secondChopstick = second;
		this.name = name;
		this.butler = butler;
	}

	/**
	 * Puts a philosopher into an eat -> think -> eat loop until told to stop
	 * working.
	 */
	@Override
	public void run() {

		while (this.keepWorking) {

			this.arriveAtTable();
			this.pickUpForks();
			this.eat();
			this.putDownForks();
			this.leaveTable();
			this.think();
		}

	}

	private void arriveAtTable() {

		this.butler.sitDown();
		System.out.println(this.name + " sits down");
	}

	private void leaveTable() {

		this.butler.arise();
		System.out.println(this.name + " rises");
	}

	private void pickUpForks() {

		this.firstChopstick.lift();
		System.out.println(this.name + " picks up first chopstick.");

		this.secondChopstick.lift();
		System.out.println(this.name + " picks up second chopstick.");

	}

	private void eat() {

		try {
			Thread.sleep(500);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}

		System.out.println(this.name + " finished eating.");
	}

	private void putDownForks() {

		this.secondChopstick.putDown();
		System.out.println(this.name + " puts down second chopstick");

		this.firstChopstick.putDown();
		System.out.println(this.name + " puts down first chopstick");

	}

	private void think() {

		try {
			Thread.sleep(500);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		System.out.println(this.name + " has finished thinking");

	}

	/**
	 * Stop method for thread.
	 * 
	 * @Precondition: none
	 * @Postcondition: thread is stopped
	 */
	public void stop() {
		this.keepWorking = false;
	}

}
