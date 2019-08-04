package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Iterator;

public class LRU {
	/**
	 * Holds the given frame size
	 */
	private int frameSize;
	/**
	 * Holds the pages,
	 * page = virtual address / page size
	 */
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	/**
	 * Creates LRU instance with given frame size and pages
	 * @param frameSize
	 * @param pages
	 */
	public LRU(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}
	
	// Method to find page faults using indexes 
	/**
	 * Calculates the percentage of page faults
	 * using Least Recently Used page replacement
	 * algorithm
	 * @return page faults in %
	 */
	public String LRUPageReplacmentAlgo() 
	{
		/*
		 *Represents the set of current pages in frame 
		 */
		HashSet<Integer> frameSet = new HashSet<>(frameSize); 
		/*
		 * Stores recently used indexes of pages
		 */
		HashMap<Integer, Integer> indexes = new HashMap<>(); 
	
		/*
		 * keeps track of page faults
		 */
		int page_faults = 0;
		
		for (int i=0; i<pages.size(); i++) 
		{ 
			/*
			 * Check if the set, which is the frame
			 * can hold any more pages
			 */
			if (frameSet.size() < frameSize) 
			{ 
				/*
				 * If it is not in the set, insert it into
				 * the set, this means the page was not there
				 * so increment pageFaults
				 */
				if (!frameSet.contains(pages.get(i))) 
				{ 
					frameSet.add(pages.get(i)); 
	
					// increment page fault 
					page_faults++; 
				} 
	
				/*
				 * Storing the recently used
				 * index of each page
				 */
				indexes.put(pages.get(i), i); 
			}
			/*
			 *If the set is full, then that means the least
			 *recently used page needs to be removed,
			 *and the current page must be inserted into the
			 *set 
			 */
			else
			{ 
				/*
				 * Check if the current page is not already
				 * in the set
				 */
				if (!frameSet.contains(pages.get(i))) 
				{ 
				
					int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE; 
					
					Iterator<Integer> itr = frameSet.iterator(); 
					/*
					 * Finding the least recently used value
					 * Basically finding the smallest index
					 */
					while (itr.hasNext()) { 
						int temp = itr.next(); 
						if (indexes.get(temp) < lru) 
						{ 
							lru = indexes.get(temp); 
							val = temp; 
						} 
					} 
				
					// Remove the least recently used page 
					frameSet.remove(val); 
	
					// insert the current page 
					frameSet.add(pages.get(i)); 
	
					// Increment page faults, because it was not in the set
					page_faults++; 
				} 
	
				// Update the current page index 
				indexes.put(pages.get(i), i); 
			} 
		} 
		/*
		 * Calculate the percentage of page faults
		 */
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) page_faults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage); 
	}
	
	
}
