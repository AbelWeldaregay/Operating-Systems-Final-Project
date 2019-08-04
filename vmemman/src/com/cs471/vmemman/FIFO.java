package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Represents a First In First Out Algorithm
 * @author Abel Weldaregay
 *
 */
public class FIFO {
	/**
	 * Holds the pages in memory, in first in first out order
	 */
	static Queue<Integer> inMemoryPages = new LinkedList<Integer>();
	/**
	 * Holds the frame size
	 */
	private int frameSize;
	/**
	 * Holds the pages
	 */
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	
	/**
	 * Creates a FIFO instance with given
	 * frameSize and pages
	 * @param frameSize
	 * @param pages
	 */
	FIFO(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}

	/**
	 * Performs FIFO algorithm with the
	 * given page frame size and pages
	 * @return page fault in %
	 */
	public String FIFOPageReplacmentAlgo() {
		
		HashSet<Integer> frameSet = new HashSet<>(this.frameSize);
		
		int pageFaults = 0;
		int hits = 0;
		int numberOfPages = this.pages.size();
		Queue<Integer> inMemoryPages = new LinkedList<Integer>();
		for (int i = 0; i < numberOfPages; i++) {
			// check if the current frame has space
			if (frameSet.size() < frameSize) {
				/*
				 *Insert it into set if not present
				 *already which represents page fault 
				 */
				if (!frameSet.contains(pages.get(i))) {
					frameSet.add(pages.get(i));
//					System.out.println("PAGE FAULT AT PAGE #: " + this.pages.get(i));
					pageFaults++;
					/*
					 * Push the current page into the queue
					 */
					inMemoryPages.add(pages.get(i));
				}
				
			} 
			/*
			 * If the current frame if full then perform FIFO to remove the first
			 * page of the queue from set and queue both and insert the current page
			 */
			else {
				
				if (!frameSet.contains(pages.get(i))) {
					//System.out.println("PAGE FAULT AT PAGE #: " + this.pages.get(i));
					int firstPage = inMemoryPages.peek();
					inMemoryPages.poll();
					/*
					 * Remove it from the frame
					 */
					frameSet.remove(firstPage);
					/*
					 *Insert the current page 
					 */
					frameSet.add(pages.get(i));
					/*
					 * Insert the current page to in memory pages
					 */
					inMemoryPages.add(pages.get(i));
					pageFaults++;
				}
			}
		}
		/*
		 * Calculating the percentage of page faults and
		 * returning the percentage of page faults
		 */
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / numberOfPages;
		percentage = percentage * 100;
		
		return df2.format(percentage);
		
	}
	
}
