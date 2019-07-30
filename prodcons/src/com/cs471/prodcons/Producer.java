package com.cs471.prodcons;


public class Producer implements Runnable {
	// Variable Elements
	BoundedBuffer buffer;
	int producerID;
	private volatile static boolean exit = false;
	SalesRecord oneSale;

	// Constructor
	public Producer(BoundedBuffer buffer, int i) {
		this.buffer = buffer;
		producerID = i;
	}

	@Override
	public void run() {
		while (this.buffer.getCountProduced() <= BoundedBuffer.MAX_BUFFER_SIZE) {
			this.buffer.incrementCountProduced();
			
			oneSale = new SalesRecord(producerID);
			this.buffer.insert(oneSale);
 			System.out.println(
 					"Produced " + this.buffer.getCountProduced() + " - " +
					Thread.currentThread().getName() + ".....storeID: " +
					oneSale.getStoreId() + ", Amount: " + oneSale.getSaleAmount()
			);
			
			UtilityClass.nap();
			
			if (this.buffer.getCountProduced() >= BoundedBuffer.MAX_BUFFER_SIZE) {
				// Instructs all producers to stop
				stop();
			}
		}
	}
	public static void stop() {
		exit = true;
	}
}

