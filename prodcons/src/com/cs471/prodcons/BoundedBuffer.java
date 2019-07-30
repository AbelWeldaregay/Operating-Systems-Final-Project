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
	static volatile int countProduced;
	/**
	 * Items that have been removed (consumed) by all consumers
	 */
	static volatile int countConsumed;
	/**
	 * Items currently in the buffer
	 */
	volatile int count;
	/**
	 * 
	 */
	SalesRecord sale;
	private int in;
	private int out; // next item to be produced and consumed
	private SalesRecord[] buffer; // array type E called buffer

	BoundedBuffer(int P, int C) {
		BoundedBuffer.PRODUCERS = P;
		BoundedBuffer.CONSUMERS = C;
		BoundedBuffer.countProduced = 0; // initially there are 0 produced items
		BoundedBuffer.countConsumed = 0; // initially there are 0 consumed items
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

	// producers call this method
	public synchronized void insert(SalesRecord item) {
		while (count >= BUFFER_SIZE) { // wait till the buffer has an open space
			//SleepUtil.sleep();
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
		// System.out.println("Items in buffer: " + checkCount());
		this.notifyAll();
	}

	// Consumers call this method
	public synchronized SalesRecord remove() {
		while (count <= 0) { // wait till something appears in the buffer
			//SleepUtil.sleep();
			try {
				this.wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println("remove() waiting, count="+count);
		}
		SalesRecord anItem = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		--count;
		// System.out.println("Items in buffer: " + checkCount());
		this.notifyAll();
		return anItem;
	}
	
	public synchronized int getCountProduced() {
			return this.countProduced;
	}
	
	public synchronized void incrementCountProduced() {
			this.countProduced++;
	}
	
	public synchronized int getCountConsumed() {
			return this.countConsumed;
	}
	
	public synchronized void incrementCountConsumed() {
			this.countConsumed++;
	}
	
}

