package com.cs471.prodcons;

import java.time.Duration;
import java.time.Instant;
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
	public static String[] statTypes = {"store-wide", "month-wise", "aggregate-sales"};
	/**
	 * Driver method, flow of execution
	 * begins here	
	 * @param args
	 */
	public static void main(String[] args) {
		
		  Instant start = Instant.now();
		
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
			
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
			System.out.println("Length of Simulation (in ms): " + timeElapsed);
			
			
			
			
			for (int s = 0; s < statTypes.length; s++) {
				
				switch(statTypes[s]) {
				case "month-wise": {
					System.out.println("*********Month**Wide**Sales*********");
					for (int i = 1; i <= 12; i++) {
						float monthlySales = 0;
						for (int j = 0; j < globalStats.size(); j++) {
							
							if (i == globalStats.get(j).getSaleMonth()) {
								monthlySales = monthlySales + globalStats.get(j).getSaleAmount();
							}
						}
						
						System.out.println("Month: " + i + " Total Sales: " + monthlySales);
					}
					
					break;
				}
				case "store-wide": {
					System.out.println("*********Store**Wide**Sales*********");
					for (int i = 0; i < producerCountArg; i++) {
						float storeWideSales = 0;
						
						for (int j = 0; j < globalStats.size(); j++) {
							if (globalStats.get(j).getStoreId() == i) {
								storeWideSales = storeWideSales + globalStats.get(j).getSaleAmount();
							}
						}
						System.out.println("Store ID: " + i + " Total Sales: " + storeWideSales);
					}
					
					break;
				}
				case "aggregate-sales": {
					System.out.println("*********Aggregate**Of**Sales*********");
					float totalSales = 0;
					for (int i = 0; i < globalStats.size(); i++) {
						totalSales = totalSales + globalStats.get(i).getSaleAmount();
					}
					System.out.println(" Total Sales: " + totalSales);
					break;
				}
				}
				
			}
	}
}

