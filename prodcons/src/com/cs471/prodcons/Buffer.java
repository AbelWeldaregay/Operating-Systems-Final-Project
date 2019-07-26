package com.cs471.prodcons;
/**
 * 
 * @author Abel Weldaregay
 *
 */
public interface Buffer {
	/**
	 * Adds a buffer item to the shared buffer
	 * Called by producer
	 * @param item
	 */
	public abstract void produce(Object item);
	/**
	 * Consumes (removes) an item from the shared buffer
	 * Called by consumer
	 * @return
	 */
	public abstract Object consume();

}
