package com.cs471.prodcons;
/**
 * Represents a Consumer thread
 * @author Abel Weldaregay
 *
 */
public class Consumer implements Runnable {
	/**
	 * the local bounded buffer
	 */
	BoundedBuffer localBuffer;
	/**
	 * Unique consumer Id
	 */
	int consumerId;
	/**
	 * Represents a sale
	 */
	SalesRecord sale;
	/**
	 * Creates an instance of a Consumer thread
	 * with the given buffer
	 * @param buffer
	 */
	public Consumer(BoundedBuffer buffer) {
		this.localBuffer = buffer;
	}
	
	@Override
	public void run() {
		while (this.localBuffer.getConsumedCount() < BoundedBuffer.MAX_BUFFER_SIZE) {
			/*
			 * Since an item has been consumed,
			 * the consumed count in the buffer needs
			 * to be updated
			 */
			this.localBuffer.updateConsumed();
			
			sale = this.localBuffer.consume();
			/*
			 * Keeping track of the global stats
			 */
			Main.globalStats.add(sale);
 			System.out.println("Consumed " + this.localBuffer.getConsumedCount() + " - " +
 			Thread.currentThread().getName() + ".....storeID: " +
 			sale.getStoreId() + ", Amount: " + sale.getSaleAmount());
			/*
			 * Randomly sleeps for 5-40 milliseconds
			 */
			UtilityClass.nap();

		}
	}
}

