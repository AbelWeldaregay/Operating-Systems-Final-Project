package com.cs471.prodcons;

public class Consumer implements Runnable {
	// Variable Elements
	BoundedBuffer<SalesRecord> buffer;
	int consumerID;
	private volatile static boolean exit = false;
	SalesRecord oneSale;
	SalesRecord[] consumedSales;

	// Constructor
	public Consumer(BoundedBuffer<SalesRecord> buffer) {
		this.buffer = buffer;
		consumedSales = new SalesRecord[BoundedBuffer.MAX_BUFFER_ITEMS];
	}

	void print() {
		for (int i = 0; i < (consumedSales.length); i++) {
			System.out.println(consumedSales[i].toString() + "\n");
		}
	}

	@Override
	public void run() {
		while (!exit) {
			// Increment number of items consumed, tracked by the sharedBuffer
			BoundedBuffer.countConsumed++;
			try {
				//SleepUtil.sleep();
				oneSale = buffer.remove();
				Main.allRecords.add(oneSale);
   			System.out.println("Consumed " + BoundedBuffer.countConsumed + " - " +
   			Thread.currentThread().getName() + ".....storeID: " +
   			oneSale.storeID + ", Amount: " + oneSale.saleAmount);
   			
				 
				SleepUtil.sleep();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (BoundedBuffer.countConsumed == BoundedBuffer.MAX_BUFFER_ITEMS) {

				stop();
			}
		}
	}
	// Stop when TOT_SALES_RECORDS items are produced
	public static void stop() {
		exit = true;
	}
}
