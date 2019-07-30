package com.cs471.prodcons;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	// For Global Statistics
	// Pass local statistics from each producer to allRecords
	static List<SalesRecord> globalStats;
	static int producersCountArg;
	static int consumersCountArg;
	static ArrayList<Thread> producerThreads;
	static ArrayList<Thread> consumerThreads;
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
		
		producersCountArg = Integer.parseInt(args[0]);
		consumersCountArg = Integer.parseInt(args[1]);
			
		producerThreads = new ArrayList<>(producersCountArg);
		
		consumerThreads = new ArrayList<>(consumersCountArg);
		
		// Create the shared buffer to synchronize producer and consumer threads		
		final BoundedBuffer<SalesRecord> sharedBuffer = new BoundedBuffer<SalesRecord>(producersCountArg, consumersCountArg);
		System.out.println(producersCountArg + " Producers and " + consumersCountArg + " Consumers to be initilized \n");
		
			for (int i=0; i<producersCountArg; i++) {
				producerThreads.add(new Thread(new Producer(sharedBuffer, i)));
			} 
			for (int i=0; i<producersCountArg; i++) {
				producerThreads.get(i).start();
			}

			for (int i=0; i < consumersCountArg; i++) {
				consumerThreads.add(new Thread(new Consumer(sharedBuffer)));
			}
			for (int i=0; i<consumersCountArg; i++) {
				consumerThreads.get(i).start();
			}
			
			for (int i=0; i<producersCountArg; i++) {
				try {
					producerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			
			for (int i=0; i<consumersCountArg; i++) {
				try {
					consumerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			
			try {
				Thread.sleep(2000);
				for (int i = 0; i < Main.globalStats.size(); i++) {
					System.out.println((i+1) + "         " + Main.globalStats.get(i));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		return;
	}
}
