package com.cs471.prodcons;

/**
 * 
 * @author Abel Weldaregay
 *
 */
public class Producer implements Runnable {
	/**
	 * the local bounded buffer
	 */
	BoundedBuffer localBuffer;
	/**
	 * Unique producer Id
	 */
	int producerId;
	/**
	 * Represents a sale
	 */
	SalesRecord sale;

	/**
	 * 
	 * @param buffer
	 * @param producerId
	 */
	public Producer(BoundedBuffer buffer, int producerId) {
		this.localBuffer = buffer;
		this.producerId = producerId;
	}

	@Override
	public void run() {
		while (this.localBuffer.getProducedCount() <= BoundedBuffer.MAX_BUFFER_SIZE) {
			this.localBuffer.updateProduced();
			
			sale = new SalesRecord(producerId);
			this.localBuffer.produce(sale);
 			System.out.println(
 					"Produced " + this.localBuffer.getProducedCount() + " - " +
					Thread.currentThread().getName() + ".....storeID: " +
					sale.getStoreId() + ", Amount: " + sale.getSaleAmount()
			);
			
			UtilityClass.nap();
			
		}
	}
}

