package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;

public class LRU {

	private int pageSize;
	private ArrayList<Integer> virtualAddresses;
	private int frameSize;
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	public LRU(int pageSize, ArrayList<Integer> virtualAddresses, int frameSize) {
		this.pageSize = pageSize;
		this.virtualAddresses = virtualAddresses;
		this.frameSize = frameSize;
		for (int i = 0; i < this.virtualAddresses.size(); i++) {
			this.pages.add(UtilityClass.getPageNumber(this.virtualAddresses.get(i), this.pageSize));
		}

	}
	
	public String pageFaults() {
		
		/*
		 * Represents the set of current pages
		 */
		HashSet<Integer> s = new HashSet<>(this.frameSize);
		/*
		 * Stores the least recently used indexes of pages
		 */
		HashMap<Integer, Integer> indexes = new HashMap<>();
		
		// start from initial page
		int pageFaults = 0;
		int hits = 0;
		int n = this.pages.size();
		for (int i = 0; i < n; i++) {
			
			//check if the set (frame) can hold more pages
			if (s.size() < this.frameSize) {
				if (!s.contains(this.pages.get(i))) {
					s.add(this.pages.get(i));
					pageFaults++;
				} else {
					hits++;
				}
				//store the recently used index of each page
				indexes.put(pages.get(i), i);
			}
			/*
			 * If the set is full, then LRU algorithm 
			 * is used to replace the least recently used
			 * page with the current page
			 */
			else {
				if (!s.contains(pages.get(i))) {
					/*
					 * Finding the least recently used pages that
					 * is present in the set
					 */
					int LRU = Integer.MAX_VALUE;
					int val = Integer.MIN_VALUE;
					Iterator<Integer> itr = s.iterator();
					
					while (itr.hasNext()) {
						int temp = itr.next();
						if (indexes.get(temp) < LRU) {
							LRU = indexes.get(temp);
							val = temp;
						}
					}
					/*
					 * remove the least recently used page
					 */
					s.remove(val);
					/*
					 * Insert the current page to the set
					 */
					s.add(pages.get(i));
					// Increase the number of page faults
					pageFaults++;
				} else {
					hits++;
				}
				indexes.put(pages.get(i), i);
			}

			
		}
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage);
	}
	
	
}
