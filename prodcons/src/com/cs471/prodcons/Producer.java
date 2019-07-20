package com.cs471.prodcons;

import java.util.*;

/**
 * Producer thread for bounder buffer problem
 * @author Abel Weldaregay
 *
 */
public class Producer {

	private Buffer<BufferItem> buffer;
	
	public Producer(Buffer<BufferItem> buffer) {
		this.buffer = buffer;
	}
	
	/**
	 * Produce an item and enter it into the buffer
	 */
	public void run() {
		BufferItem message;
		while (true) {
			System.out.println("Producer napping");
			Sleep.nap();
			BufferItem bufferItem = new BufferItem();
			System.out.println("Producer produced: ");
			bufferItem.generateAndSetRandomValues();
			buffer.insert(bufferItem);
		}
	}
}
