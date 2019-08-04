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
			System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
		               "|" , "Produced ->" + Integer.toString(this.localBuffer.getConsumedCount()), "|", Thread.currentThread().getName(), "|" , "Store Id ->" + Integer.toString(sale.getStoreId()), "|", "Sale Amt. ->" + Float.toString(sale.getSaleAmount()), "\n");
//			System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
//		               "|" ,Integer.toString(this.localBuffer.getProducedCount()), "|", Thread.currentThread().getName(), "|" , Integer.toString(sale.getStoreId()), "|", Float.toString(sale.getSaleAmount()), "|\n");
//			
			UtilityClass.nap();
			
		}
	}
}

