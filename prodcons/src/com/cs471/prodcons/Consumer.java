package com.cs471.prodcons;
/**
 * Represents a consumer
 * @author Abel Weldaregay
 *
 */
public class Consumer implements Runnable {
	// Variable Elements
	private BoundedBuffer<SalesRecord> localBuffer;
	private int consumerId;
	private volatile static boolean exit = false;
	private SalesRecord sale;
	private SalesRecord[] consumedSales;

	// Constructor
	public Consumer(BoundedBuffer<SalesRecord> buffer) {
		this.localBuffer = buffer;
		consumedSales = new SalesRecord[BoundedBuffer.MAX_BUFFER_SIZE];
	}

	void print() {
		for (int i = 0; i < (consumedSales.length); i++) {
			System.out.println(consumedSales[i].toString() + "\n");
		}
	}

	@Override
	public void run() {
		while (this.localBuffer.getConsumedCount() < BoundedBuffer.MAX_BUFFER_SIZE) {
			// Increment number of items consumed, tracked by the sharedBuffer
			this.localBuffer.consumedCount++;
			
			try {
				sale = this.localBuffer.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.globalStats.add(sale);
 			System.out.println("Consumed " + this.localBuffer.getConsumedCount() + " - " +
 			Thread.currentThread().getName() + ".....storeID: " +
 			sale.getStoreId() + ", Amount: " + sale.getSaleAmount());
			
			UtilityClass.nap();

			if (this.localBuffer.getConsumedCount() >= BoundedBuffer.MAX_BUFFER_SIZE) {
				stop();
			}
		}


	}
	// Stop when TOT_SALES_RECORDS items are produced
	public static void stop() {
		exit = true;
	}
}
