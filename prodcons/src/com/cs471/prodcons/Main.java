package com.cs471.prodcons;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	/**
	 * The local stats are passed into this arraylist
	 */
	static List<SalesRecord> globalStats;
	
	static int producerCountArg;
	static int consumerCountArg;
	void insertRecords (SalesRecord records[]) {
		for(int i=0; i<records.length; i++) {
			globalStats.add(records[i]);
		}
	}
	
	void printRecords () {
		for(int i=0; i<globalStats.size(); i++) {
			System.out.println(globalStats.get(i));
		}
	}
	
	public static void main(String[] args) {
		
		globalStats = new ArrayList<SalesRecord>();
		
		producerCountArg = Integer.parseInt(args[0]);
		consumerCountArg = Integer.parseInt(args[1]);
			
		List<Thread> producerThreads = new ArrayList<>(producerCountArg);
		List<Thread> consumerThreads = new ArrayList<>(consumerCountArg);
		
		// Create the shared buffer to synchronize producer and consumer threads		
		final BoundedBuffer sharedBuffer = new BoundedBuffer(producerCountArg, consumerCountArg);
		System.out.println(producerCountArg + " Producers and " + consumerCountArg + " Consumers to be initilized \n");
		
			for (int i=0; i<producerCountArg; i++) {
				producerThreads.add(new Thread(new Producer(sharedBuffer, i)));
			} 
			for (int i=0; i<producerCountArg; i++) {
				producerThreads.get(i).start();
			}

			for (int i=0; i < consumerCountArg; i++) {
				consumerThreads.add(new Thread(new Consumer(sharedBuffer)));
			}
			for (int i=0; i<consumerCountArg; i++) {
				consumerThreads.get(i).start();
			}
			

			for (int i=0; i<producerCountArg; i++) {
				try {
					producerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			
			for (int i=0; i<consumerCountArg; i++) {
				try {
					consumerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			
		for (int i = 0; i < Main.globalStats.size(); i++) {
				System.out.println((i+1) + "         " + Main.globalStats.get(i));
		}	
	}
}

