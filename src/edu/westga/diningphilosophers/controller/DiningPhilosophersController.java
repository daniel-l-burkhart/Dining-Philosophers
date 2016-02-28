/**
 * 
 */
package edu.westga.diningphilosophers.controller;

import java.util.ArrayList;

import edu.westga.diningphilosophers.model.Butler;
import edu.westga.diningphilosophers.model.Fork;
import edu.westga.diningphilosophers.model.Philosopher;

/**
 * Controller class for application.
 * 
 * @author Daniel Burkhart
 * @version Spring 2016
 */
public class DiningPhilosophersController {

	/**
	 * Constant for the size of philosophers and forks.
	 */
	public static final int LIST_SIZE = 5;

	private ArrayList<Fork> forks;
	private ArrayList<Philosopher> philosophers;
	private ArrayList<Thread> threads;

	private Philosopher socrates;
	private Philosopher aristotle;
	private Philosopher plato;
	private Philosopher descartes;
	private Philosopher nietzsche;

	private Butler jarvis;

	private Thread socratesThread;
	private Thread aristotleThread;
	private Thread platoThread;
	private Thread descartesThread;
	private Thread nietzscheThread;

	/**
	 * Initializes and adds all variables to their collections.
	 */
	public DiningPhilosophersController() {

		this.initializeVariables();
		this.makeCollections();

	}

	/**
	 * Starts the dinner party of the philosophers
	 */
	public void startDinnerParty() {

		System.out.println("Begin");

		this.startThreads();

		this.sleepForTenSeconds();

		this.stopThreads();

		System.out.println("End");

	}

	private void makeCollections() {

		this.philosophers.add(this.aristotle);
		this.philosophers.add(this.descartes);
		this.philosophers.add(this.socrates);
		this.philosophers.add(this.plato);
		this.philosophers.add(this.nietzsche);

		this.threads.add(this.aristotleThread);
		this.threads.add(this.socratesThread);
		this.threads.add(this.descartesThread);
		this.threads.add(this.nietzscheThread);
		this.threads.add(this.platoThread);

	}

	private void initializeVariables() {

		this.forks = new ArrayList<Fork>(5);
		this.makeForks();

		this.philosophers = new ArrayList<Philosopher>(LIST_SIZE);
		this.threads = new ArrayList<Thread>(5);
		this.jarvis = new Butler();
		this.socrates = new Philosopher("Socrates", this.forks.get(0), this.forks.get(1), this.jarvis);
		this.aristotle = new Philosopher("Aristotle", this.forks.get(1), this.forks.get(2), this.jarvis);
		this.plato = new Philosopher("Plato", this.forks.get(2), this.forks.get(3), this.jarvis);
		this.descartes = new Philosopher("Descartes", this.forks.get(3), this.forks.get(4), this.jarvis);
		this.nietzsche = new Philosopher("Nietzsche", this.forks.get(4), this.forks.get(0), this.jarvis);

		this.socratesThread = new Thread(this.socrates);
		this.aristotleThread = new Thread(this.aristotle);
		this.platoThread = new Thread(this.plato);
		this.descartesThread = new Thread(this.descartes);
		this.nietzscheThread = new Thread(this.nietzsche);

	}

	private void makeForks() {
		for (int i = 0; i < LIST_SIZE; i++) {
			this.forks.add(new Fork());
		}
	}

	private void stopThreads() {
		for (Philosopher currPhil : this.philosophers) {
			currPhil.stop();
		}
	}

	private void sleepForTenSeconds() {
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	private void startThreads() {
		for (Philosopher philosopher : this.philosophers) {
			(new Thread(philosopher)).start();
		}
	}

}
