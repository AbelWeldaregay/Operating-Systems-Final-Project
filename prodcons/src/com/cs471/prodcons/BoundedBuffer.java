package com.cs471.prodcons;

/**
 * Represents a bounded buffer
 * @author Abel Weldaregay
 *
 */
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
	/**
	 * Shared buffer
	 */
	private SalesRecord[] buffer;
	/**
	 * Creates an instance of BoundedBuffer
	 * with given producers and consumers
	 * Produced count and consumed count are
	 * initially set to 0 since there is nothing
	 * created yet
	 * @param P
	 * @param C
	 */
	BoundedBuffer(int PRODUCERS, int CONSUMERS) {
		BoundedBuffer.PRODUCERS = PRODUCERS;
		BoundedBuffer.CONSUMERS = CONSUMERS;
		BoundedBuffer.producedCount = 0; 
		BoundedBuffer.consumedCount = 0;
		this.in = 0;
		this.out = 0;
		this.count = 0;
		this.buffer = new SalesRecord[BUFFER_SIZE]; 
	}
	/**
	 * Producers call this method,
	 * produces an item
	 * @param item
	 */
	public synchronized void produce(SalesRecord item) {
		/* Entry Section
		 * Waiting for when the buffer has an open
		 * slot
		 */
		while (count >= BUFFER_SIZE) {
			try {
				// waiting to enter the critical section 
				this.wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			// helps with debugging and reviewing results
			System.err.println("PRODUCE WAITING | ITEMS IN BUFFER -> " + count);
		}
		/*Critical Section
		 * Now entering the critical section,
		 * Adds the item to the buffer
		 */
		buffer[in] = item;
		// the index of the next position to be inserted
		in = (in + 1) % BUFFER_SIZE;
		// incrementing count since one item was just added
		++count;
		
		/*
		 * Now entering exit section, notifyAll lets
		 * all other semaphores know other threads waiting that
		 * it is done
		 */
		this.notifyAll();
	}
	/**
	 * Consumers call this method,
	 * consumes (removes) an item from
	 * the buffer
	 * @return item consumed
	 */
	public synchronized SalesRecord consume() {
		/* Entry Section
		 * For it to consume anything, there first
		 * needs to be something in the buffer, so it waits
		 * until something appears in the buffer
		 */
		while (count <= 0) {
			try {
				this.wait();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println("CONSUME WAITING | ITEMS IN BUFFER -> " + count);
		}
		/* Critical Section
		 * Consumes an item, and decrements
		 * count since there is now one less
		 * item in the buffer
		 */
		SalesRecord saleRecord = buffer[out];
		out = (out + 1) % BUFFER_SIZE;
		--count;
		
		/* Exit Section
		 * Once the critical section is finished,
		 * it notifies other threads that it has finished
		 * executing in it's critical section
		 */
		this.notifyAll();
		return saleRecord;
	}
	
	public synchronized int getProducedCount() {
			return this.producedCount;
	}
	
	public synchronized void updateProduced() {
			this.producedCount++;
	}
	
	public synchronized int getConsumedCount() {
			return this.consumedCount;
	}
	
	public synchronized void updateConsumed() {
			this.consumedCount++;
	}
	
}

