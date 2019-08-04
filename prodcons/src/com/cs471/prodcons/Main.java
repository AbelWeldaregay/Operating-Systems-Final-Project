package com.cs471.prodcons;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	/**
	 * The local stats are passed into this arraylist
	 */
	static ArrayList<SalesRecord> globalStats;
	/**
	 * Command line argument for the number of
	 * producers to be created
	 */
	static int producerCountArg;
	/**
	 * Command line argument for the number
	 * of consumers to be created
	 */
	static int consumerCountArg;
	/**
	 * Driver method, flow of execution
	 * begins here	
	 * @param args
	 */
	public static void main(String[] args) {
		
		globalStats = new ArrayList<SalesRecord>();
		
		producerCountArg = Integer.parseInt(args[0]);
		consumerCountArg = Integer.parseInt(args[1]);
			
		ArrayList<Thread> producerThreads = new ArrayList<>(producerCountArg);
		ArrayList<Thread> consumerThreads = new ArrayList<>(consumerCountArg);
			
		/*
		 * Creating the shared buffer that
		 * will synchronize the producer and consumer threads
		 */
		final BoundedBuffer sharedBuffer = new BoundedBuffer(producerCountArg, consumerCountArg);
		System.out.println(producerCountArg + " Producers and " + consumerCountArg + " Consumers to be initilized \n");
		
			/*
			 * Creating instances of the producer threads
			 * and adding them to a list of producer
			 * threads
			 */
			for (int i=0; i<producerCountArg; i++) {
				producerThreads.add(new Thread(new Producer(sharedBuffer, i)));
			} 
			/*
			 * Starting all of the producer threads
			 */
			for (int i=0; i<producerCountArg; i++) {
				producerThreads.get(i).start();
			}
			/*
			 * Creating instances of the consumer threads
			 * and adding them to a list of consumer
			 * threads
			 */
			for (int i=0; i < consumerCountArg; i++) {
				consumerThreads.add(new Thread(new Consumer(sharedBuffer)));
			}
			/*
			 * Starting all of the consumer threads
			 */
			for (int i=0; i<consumerCountArg; i++) {
				consumerThreads.get(i).start();
			}
			
			/*
			 * allows one thread to wait until another thread
			 * completes its execution. 
			 */
			for (int i=0; i<producerCountArg; i++) {
				try {
					producerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			/*
			 * allows one thread to wait until another thread
			 * completes its execution. 
			 */
			for (int i=0; i<consumerCountArg; i++) {
				try {
					consumerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			Stats stats = new Stats(globalStats);
			stats.calculateStats();
	}
}

