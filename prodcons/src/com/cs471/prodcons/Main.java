package com.cs471.prodcons;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	// For Global Statistics
	// Pass local statistics from each producer to allRecords
	static List<SalesRecord> allRecords;
	
	void insertRecords (SalesRecord records[]) {
		for(int i=0; i<records.length; i++) {
			allRecords.add(records[i]);
		}
	}
	
	void printRecords () {
		for(int i=0; i<allRecords.size(); i++) {
			System.out.println(allRecords.get(i));
		}
	}
	
	
	public static void main(String[] args) {
		allRecords = new ArrayList<SalesRecord>();
		
		int p_Producers = Integer.parseInt(args[0]);
		int c_Consumers = Integer.parseInt(args[1]);
			
		List<Thread> producerThreads = new ArrayList<>(p_Producers);
		List<Thread> consumerThreads = new ArrayList<>(c_Consumers);
		
		// Create the shared buffer to synchronize producer and consumer threads		
		final BoundedBuffer<SalesRecord> sharedBuffer = new BoundedBuffer<SalesRecord>(p_Producers, c_Consumers);
		System.out.println(p_Producers + " Producers and " + c_Consumers + " Consumers to be initilized \n");
		
			for (int i=0; i<p_Producers; i++) {
				producerThreads.add(new Thread(new Producer(sharedBuffer, i)));
			} 
			for (int i=0; i<p_Producers; i++) {
				producerThreads.get(i).start();
			}

			for (int i=0; i < c_Consumers; i++) {
				consumerThreads.add(new Thread(new Consumer(sharedBuffer)));
			}
			for (int i=0; i<c_Consumers; i++) {
				consumerThreads.get(i).start();
			}
			
			for (int i=0; i<p_Producers; i++) {
				try {
					producerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			
			for (int i=0; i<c_Consumers; i++) {
				try {
					consumerThreads.get(i).join();
				} catch (InterruptedException e) {}
			}
			
			try {
				Thread.sleep(2000);
				for (int i = 0; i < Main.allRecords.size(); i++) {
					System.out.println((i+1) + "         " + Main.allRecords.get(i));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		return;
	}
}
