package com.cs471.prodcons;


public class Producer implements Runnable {
	// Variable Elements
	BoundedBuffer buffer;
	int producerId;
	SalesRecord oneSale;

	// Constructor
	public Producer(BoundedBuffer buffer, int i) {
		this.buffer = buffer;
		producerId = i;
	}

	@Override
	public void run() {
		while (this.buffer.getProducedCount() <= BoundedBuffer.MAX_BUFFER_SIZE) {
			this.buffer.updateProduced();
			
			oneSale = new SalesRecord(producerId);
			this.buffer.produce(oneSale);
 			System.out.println(
 					"Produced " + this.buffer.getProducedCount() + " - " +
					Thread.currentThread().getName() + ".....storeID: " +
					oneSale.getStoreId() + ", Amount: " + oneSale.getSaleAmount()
			);
			
			UtilityClass.nap();
			
		}
	}
}

