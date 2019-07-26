package com.cs471.prodcons;

public class Producer implements Runnable {
	// Variable Elements
	private BoundedBuffer<SalesRecord> localBuffer;
	private int producerId;
	private volatile static boolean exit = false;
	private SalesRecord sale;

	/**
	 * Create a producer with a given producer and producer id
	 * @param buffer
	 * @param i
	 */
	public Producer(BoundedBuffer<SalesRecord> buffer, int producerId) {
		this.localBuffer = buffer;
		this.producerId = producerId;
	}
	/**
	 * creates a producer thread
	 */
	@Override
	public void run() {
		while (!exit) {
			BoundedBuffer.countProduced++;
			try {
				sale = new SalesRecord(this.producerId);
				UtilityClass.nap();
				
				this.localBuffer.produce(sale);
   			System.out.println("Produced " + BoundedBuffer.countProduced + " - " +
				Thread.currentThread().getName() + ".....storeID: " +
				sale.getStoreId() + ", Amount: " + sale.getSaleAmount());
				 
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
