package com.cs471.prodcons;

/**
 * Interface for shared memory.
 * @author Abel Weldaregay
 */
public interface Buffer <E>
{
	/**
	 * @description Producers call this method
	 * @param item
	 */
	public void insert(E item);
	/**
	 * @description Consumers call this method
	 * @return
	 */
	public E remove();
}