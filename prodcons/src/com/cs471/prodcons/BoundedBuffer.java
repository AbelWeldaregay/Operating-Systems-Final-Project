package com.cs471.prodcons;

class BoundedBuffer<E> {
	// Number of Producers and Consumers
	static int P;
	static int C;
	// Bounded Size of the buffer
	private static final int BUFFER_SIZE = 5;
	static final int MAX_BUFFER_ITEMS = 20;

	// Items that have been generated (produced) by all producers
	static int countProduced;
	// Items that have been removed (consumed) by all consumers
	static int countConsumed;
	// Items currently in the buffer
	int count;

	SalesRecord salesRecord;
	private int in, out; // next item to be produced and consumed
	private SalesRecord[] buffer; // array type E called buffer

	BoundedBuffer(int P, int C) {
		BoundedBuffer.P = P;
		BoundedBuffer.C = C;
		BoundedBuffer.countProduced = 0; // initially there are 0 produced items
		BoundedBuffer.countConsumed = 0; // initially there are 0 consumed items
		this.in = 0;
		this.out = 0;
		this.count = 0; // initially the buffer is empty
		// Set the buffer to a bounded size (it can hold BUFFER_SIZE SalesRecords at a time)
		this.buffer = new SalesRecord[BUFFER_SIZE]; 
	}

	static int getProducers() {
		return P;
	}

	static int getConsumers() {
		return C;
	}

	int checkCount() {
		return count;
	}

	// producers call this method
	public synchronized void produce(SalesRecord sale)
			throws InterruptedException {
		while (count == BUFFER_SIZE) { // wait till the buffer has an open space
			try {
				wait();
			} catch (InterruptedException e) {
				throw e;
			}
		}
		buffer[in] = sale;
		in = (in + 1) % BUFFER_SIZE;
		++count;
		// System.out.println("Items in buffer: " + checkCount());
		notifyAll();
	}

	// Consumers call this method
	public synchronized SalesRecord consume() throws InterruptedException {
		while (count == 0) { // wait till something appears in the buffer
			try {
				wait();
			} catch (InterruptedException e) {
				throw e;
			}
		}
		SalesRecord anItem = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		--count;
		// System.out.println("Items in buffer: " + checkCount());
		notifyAll();
		return anItem;
	}
}
