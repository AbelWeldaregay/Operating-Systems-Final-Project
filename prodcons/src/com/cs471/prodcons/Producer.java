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
		while (this.localBuffer.getProducedCount() <= BoundedBuffer.MAX_BUFFER_SIZE) {
			this.localBuffer.producedCount++;
			
			sale = new SalesRecord(producerId);
			try {
				this.localBuffer.produce(sale);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			System.out.println(
 					"Produced " + this.localBuffer.getProducedCount() + " - " +
					Thread.currentThread().getName() + ".....storeID: " +
					sale.getStoreId() + ", Amount: " + sale.getSaleAmount()
			);
			
			UtilityClass.nap();
			
			if (this.localBuffer.getProducedCount() >= BoundedBuffer.MAX_BUFFER_SIZE) {
				// Instructs all producers to stop
				stop();
			}
		}

	}
	public static void stop() {
		exit = true;
	}
}
