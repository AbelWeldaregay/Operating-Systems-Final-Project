package com.cs471.prodcons;

public class Producer implements Runnable {
	// Variable Elements
	BoundedBuffer<SalesRecord> buffer;
	int producerID;
	private volatile static boolean exit = false;
	SalesRecord oneSale;

	// Constructor
	public Producer(BoundedBuffer<SalesRecord> buffer, int i) {
		this.buffer = buffer;
		producerID = i;
	}

	@Override
	public void run() {
		while (!exit) {
			BoundedBuffer.countProduced++;
			try {
				oneSale = new SalesRecord(producerID);
				SleepUtil.sleep();
				
				buffer.insert(oneSale);
   			System.out.println("Produced " + BoundedBuffer.countProduced + " - " +
				Thread.currentThread().getName() + ".....storeID: " +
				oneSale.storeID + ", Amount: " + oneSale.saleAmount);
				 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Increment number of items produced, tracked by the sharedBuffer
			if (BoundedBuffer.countProduced == BoundedBuffer.MAX_BUFFER_ITEMS) {
				stop();
			}
		}
	}
	public static void stop() {
		exit = true;
	}
}
