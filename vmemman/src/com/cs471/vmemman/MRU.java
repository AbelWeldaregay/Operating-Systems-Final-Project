package com.cs471.vmemman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class MRU {

	private int pageSize;
	private ArrayList<Integer> virtualAddresses;
	private int frameSize;
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	public MRU(int pageSize, ArrayList<Integer> virtualAddresses, int frameSize) {
		this.pageSize = pageSize;
		this.virtualAddresses = virtualAddresses;
		this.frameSize = frameSize;
		for (int i = 0; i < this.virtualAddresses.size(); i++) {
			this.pages.add(UtilityClass.getPageNumber(this.virtualAddresses.get(i), this.pageSize));
		}

	}
	
	public int pageFaults() {
		
		HashSet<Integer> s = new HashSet<>(this.frameSize);
		
		HashMap<Integer, Integer> indexes = new HashMap<>();
		
		int pageFaults = 0;
		int n = this.pages.size();
		for (int i = 0; i < n; i++) {
			
			if (s.size() < this.frameSize) {
			
				if (!s.contains(pages.get(i))) {
					s.add(pages.get(i));
					pageFaults++;
				}
				
				indexes.put(pages.get(i), i);
			
			} 
			
			else {
				
				if (!s.contains(pages.get(i))) {
				
					s.remove(pages.get(i - 2));
					s.add(pages.get(i));
					pageFaults++;
				}
				
				indexes.put(pages.get(i), i);
				
			}
			
		}
		
		return pageFaults;
	}
	
}
