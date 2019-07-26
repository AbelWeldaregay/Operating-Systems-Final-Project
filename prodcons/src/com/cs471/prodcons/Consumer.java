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
				sale = localBuffer.consume();
				Main.allRecords.add(sale);
   			System.out.println("Consumed " + BoundedBuffer.countConsumed + " - " +
   			Thread.currentThread().getName() + ".....storeID: " +
   			sale.getStoreId() + ", Amount: " + sale.getSaleAmount());
   
				UtilityClass.nap();

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
