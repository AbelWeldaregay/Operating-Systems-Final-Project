package com.cs471.prodcons;


class BoundedBuffer {
	/**
	 * Number of producers
	 */
	static int PRODUCERS;
	/**
	 * Number of consumers
	 */
	static int CONSUMERS;
	// Bounded Size of the buffer
	/**
	 * Size of bounded buffer
	 */
	private static final int BUFFER_SIZE = 5;
	/**
	 * Max buffer size
	 */
	static final int MAX_BUFFER_SIZE = 10_000;
	/**
	 * Number of items produced by all producers
	 */
	static volatile int producedCount;
	/**
	 * Items that have been removed (consumed) by all consumers
	 */
	static volatile int consumedCount;
	/**
	 * Items currently in the buffer
	 */
	volatile int count;
	/**
	 * Sale record generated
	 */
	SalesRecord sale;
	/**
	 * The next item to be produced
	 */
	private int in;
	/**
	 * next item to be consumed
	 */
	private int out;
	private SalesRecord[] buffer;

	BoundedBuffer(int P, int C) {
		BoundedBuffer.PRODUCERS = P;
		BoundedBuffer.CONSUMERS = C;
		BoundedBuffer.producedCount = 0; // initially there are 0 produced items
		BoundedBuffer.consumedCount = 0; // initially there are 0 consumed items
		this.in = 0;
		this.out = 0;
		this.count = 0; // initially the buffer is empty
		// Set the buffer to a bounded size (it can hold BUFFER_SIZE SalesRecords at a time)
		this.buffer = new SalesRecord[BUFFER_SIZE]; 
	}

	static int getProducers() {
		return PRODUCERS;
	}

	int getConsumers() {
		return CONSUMERS;
	}

	int checkCount() {
		return count;
	}
	public synchronized void produce(SalesRecord item) {
		while (count >= BUFFER_SIZE) { // wait till the buffer has an open space
			try {
				this.wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println("insert() waiting, count="+count);
		}
		buffer[in] = item;
		in = (in + 1) % BUFFER_SIZE;
		++count;
		this.notifyAll();
	}
	public synchronized SalesRecord consume() {
		while (count <= 0) { // wait till something appears in the buffer
			try {
				this.wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println("remove() waiting, count="+count);
		}
		SalesRecord saleRecord = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		--count;
		this.notifyAll();
		return saleRecord;
	}
	
	public synchronized int getProducedCount() {
			return this.producedCount;
	}
	
	public synchronized void incrementProducedCount() {
			this.producedCount++;
	}
	
	public synchronized int getConsumedCount() {
			return this.consumedCount;
	}
	
	public synchronized void incrementConsumedCount() {
			this.consumedCount++;
	}
	
}

