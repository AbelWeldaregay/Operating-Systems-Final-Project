package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;

public class LRU {

	private int frameSize;
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	public LRU(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}
	
	// Method to find page faults using indexes 
	public String LRUPageReplacmentAlgo() 
	{
		// To represent set of current pages. We use 
		// an unordered_set so that we quickly check 
		// if a page is present in set or not 
		HashSet<Integer> s = new HashSet<>(frameSize); 
	
		// To store least recently used indexes 
		// of pages. 
		HashMap<Integer, Integer> indexes = new HashMap<>(); 
	
		// Start from initial page 
		int page_faults = 0; 
		for (int i=0; i<pages.size(); i++) 
		{ 
			// Check if the set can hold more pages 
			if (s.size() < frameSize) 
			{ 
				// Insert it into set if not present 
				// already which represents page fault 
				if (!s.contains(pages.get(i))) 
				{ 
					s.add(pages.get(i)); 
	
					// increment page fault 
					page_faults++; 
				} 
	
				// Store the recently used index of 
				// each page 
				indexes.put(pages.get(i), i); 
			} 
	
			// If the set is full then need to perform lru 
			// i.e. remove the least recently used page 
			// and insert the current page 
			else
			{ 
				// Check if current page is not already 
				// present in the set 
				if (!s.contains(pages.get(i))) 
				{ 
					// Find the least recently used pages 
					// that is present in the set 
					int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE; 
					
					Iterator<Integer> itr = s.iterator(); 
					
					while (itr.hasNext()) { 
						int temp = itr.next(); 
						if (indexes.get(temp) < lru) 
						{ 
							lru = indexes.get(temp); 
							val = temp; 
						} 
					} 
				
					// Remove the indexes page 
					s.remove(val); 
	
					// insert the current page 
					s.add(pages.get(i)); 
	
					// Increment page faults 
					page_faults++; 
				} 
	
				// Update the current page index 
				indexes.put(pages.get(i), i); 
			} 
		} 
	
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) page_faults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage); 
	}
	
	
}
