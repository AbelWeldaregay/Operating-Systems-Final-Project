package com.cs471.prodcons;

public interface Buffer {
	
	
	// Add a buffer item to the shared buffer (called by producer)
	public abstract void insert(Object item); //date message

	
	// Remove an item from the shared buffer (called by consumer)
	public abstract Object remove(); //date remove

}
