package com.cs471.vmemman;

import java.util.ArrayList;

public class LRU {

	private int pageSize;
	private ArrayList<Integer> virtualAddresses;
	private int frameSize;
	
	public LRU(int pageSize, ArrayList<Integer> virtualAddresses, int frameSize) {
		this.pageSize = pageSize;
		this.virtualAddresses = virtualAddresses;
		this.frameSize = frameSize;
	}
	
	
}
