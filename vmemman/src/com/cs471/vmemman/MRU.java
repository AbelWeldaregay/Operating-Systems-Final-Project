package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.*;

public class MRU {
	/**
	 * Holds the frame size
	 */
	private int frameSize;
	/**
	 * Holds the pages
	 * page = virtual address / page size
	 */
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	/**
	 * Creates an instance of MRU with given frameSize and
	 * pages
	 * @param frameSize
	 * @param pages
	 */
	public MRU(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}
	/**
	 * Performs the Most recently used page replacement algorithm
	 * @return page faults in %
	 */
	public String MRUPageReplacmentAlgo() {
		/*
		 *Represents the set of current pages in frame 
		 */
		HashSet<Integer> s = new HashSet<>(frameSize);
		/*
		 * Stores recently used indexes of pages
		 */
		HashMap<Integer, Integer> indexes = new HashMap<>();
		/*
		 * keeps track of page faults
		 */
		int pageFaults = 0;
		ArrayList<Integer> used = new ArrayList<Integer>();
		int n = pages.size();
		for (int i = 0; i < n; i++) {
			
			/*
			 * Check if the set, which is the frame
			 * can hold any more pages
			 */
			if (s.size() < frameSize) {
			
				if (!s.contains(pages.get(i))) {
					s.add(pages.get(i));
					// increment page fault
					pageFaults++;
				}
				/*
				 * Storing the recently used
				 * index of each page
				 */
				indexes.put(pages.get(i), i);
				used.add(pages.get(i));
			} 
			/*
			 *If the set is full, then that means the least
			 *recently used page needs to be removed,
			 *and the current page must be inserted into the
			 *set 
			 */
			else {
				/*
				 * Check if the current page is not already
				 * in the set
				 */
				if (!s.contains(pages.get(i))) {
					/*
					 * Now the most recently used page is removed,
					 * which is the previous one,
					 * 
					 */
					s.remove(used.get(used.size() - 2));
					s.add(pages.get(i));
					pageFaults++;
				}
				
				indexes.put(pages.get(i), i);
				used.add(pages.get(i));
				
			}
			
		}
		/*
		 * Calculate the percentage of page faults
		 */
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage);
	}
	
}
