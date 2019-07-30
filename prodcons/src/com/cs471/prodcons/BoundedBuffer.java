package com.cs471.prodcons;

class BoundedBuffer<E> {
	/**
	 * Number of producers
	 */
	static int PRODUCERS;
	/**
	 * Number of Consumers
	 */
	static int CONSUMERS;
	/**
	 * size of bounded buffer
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
	 * Number of items consumed by all consumers
	 */
	static volatile int consumedCount;
	/**
	 * The number of items currently in the bounded buffer
	 */
	volatile int count;
	/**
	 * Next item to be produced
	 */
	private int in;
	/**
	 * Next item to be consumed
	 */
	private int out;
	/**
	 * shared buffer
	 */
	private SalesRecord[] buffer;
	
	BoundedBuffer(int producers, int consumers) {
		BoundedBuffer.PRODUCERS = producers;
		BoundedBuffer.CONSUMERS = consumers;
		BoundedBuffer.producedCount = 0;
		BoundedBuffer.consumedCount = 0;
		this.in = 0;
		this.out = 0;
		this.count = 0; 
		this.buffer = new SalesRecord[BUFFER_SIZE]; 
	}

	static int getProducers() {
		return PRODUCERS;
	}

	static int getConsumers() {
		return CONSUMERS;
	}

	int checkCount() {
		return count;
	}
	/**
	 * Called by producers
	 * @param sale
	 * @throws InterruptedException
	 */
	public synchronized void produce(SalesRecord sale)
			throws InterruptedException {
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
		buffer[in] = sale;
		in = (in + 1) % BUFFER_SIZE;
		++count;
		// System.out.println("Items in buffer: " + checkCount());
		this.notifyAll();

	}
	/**
	 * Called by consumers
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized SalesRecord consume() throws InterruptedException {
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
	
	public synchronized int getProducedCount() {
		return this.producedCount;
	}
	
	public synchronized int getConsumedCount() {
		return this.consumedCount;
	}
}
