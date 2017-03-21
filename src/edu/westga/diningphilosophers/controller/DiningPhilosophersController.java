package edu.westga.diningphilosophers.controller;

import java.util.ArrayList;

import edu.westga.diningphilosophers.model.Butler;
import edu.westga.diningphilosophers.model.Chopstick;
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

	private ArrayList<Chopstick> forks;
	private ArrayList<Philosopher> philosophers;
	private ArrayList<Thread> threads;

	private Philosopher socrates, aristotle, plato, descartes, nietzsche;

	private Thread socratesThread, aristotleThread, platoThread, descartesThread, nietzscheThread;

	private Butler jarvis;

	/**
	 * Initializes and adds all variables to their collections.
	 */
	public DiningPhilosophersController() {

		this.philosophers = new ArrayList<Philosopher>(LIST_SIZE);
		this.threads = new ArrayList<Thread>(LIST_SIZE);
		this.forks = new ArrayList<Chopstick>(LIST_SIZE);
		this.jarvis = new Butler();

		this.buildForks();
		this.createPhilosophers();
		this.makeThreads();
		this.populateCollections();
	}

	private void buildForks() {

		for (int i = 0; i < LIST_SIZE; i++) {
			this.forks.add(new Chopstick());
		}
	}

	private void createPhilosophers() {

		int currFork = 0;

		this.socrates = new Philosopher("Socrates", this.forks.get(currFork),
				this.forks.get((currFork + 1) % LIST_SIZE), this.jarvis);
		currFork++;

		this.aristotle = new Philosopher("Aristotle", this.forks.get(currFork),
				this.forks.get((currFork + 1) % LIST_SIZE), this.jarvis);
		currFork++;

		this.plato = new Philosopher("Plato", this.forks.get(currFork), this.forks.get((currFork + 1) % LIST_SIZE),
				this.jarvis);
		currFork++;

		this.descartes = new Philosopher("Descartes", this.forks.get(currFork),
				this.forks.get((currFork + 1) % LIST_SIZE), this.jarvis);
		currFork++;

		this.nietzsche = new Philosopher("Nietzsche", this.forks.get(currFork),
				this.forks.get((currFork + 1) % LIST_SIZE), this.jarvis);
	}

	private void makeThreads() {

		this.socratesThread = new Thread(this.socrates);
		this.aristotleThread = new Thread(this.aristotle);
		this.platoThread = new Thread(this.plato);
		this.descartesThread = new Thread(this.descartes);
		this.nietzscheThread = new Thread(this.nietzsche);
	}

	private void populateCollections() {

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

	/**
	 * Starts the dinner party of the philosophers
	 */
	public void startDinnerParty() {

		System.out.println("Begin");

		this.startThreads();
		this.sleepForXSeconds(10);
		this.stopThreads();

		System.out.println("End");
	}

	private void startThreads() {
		for (Thread currentThread : this.threads) {
			currentThread.start();
		}
	}

	private void sleepForXSeconds(int x) {
		try {
			Thread.sleep(x * 1000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	private void stopThreads() {
		for (Philosopher currPhil : this.philosophers) {
			currPhil.stop();
		}
	}

}
