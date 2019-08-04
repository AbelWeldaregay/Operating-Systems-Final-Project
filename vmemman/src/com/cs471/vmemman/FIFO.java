package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO {

	static Queue<Integer> inMemoryPages = new LinkedList<Integer>();
	private int frameSize;
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	
	FIFO(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}

	
	public String FIFOPageReplacmentAlgo() {
		
		HashSet<Integer> s = new HashSet<>();
		
		int pageFaults = 0;
		int hits = 0;
		int numberOfPages = this.pages.size();
		Queue<Integer> inMemoryPages = new LinkedList<Integer>();
		for (int i = 0; i < numberOfPages; i++) {
			// check if the current frame has space
			if (s.size() < frameSize) {
				/*
				 *Insert it into set if not present
				 *already which represents page fault 
				 */
				if (!s.contains(pages.get(i))) {
					s.add(pages.get(i));
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
				
				if (!s.contains(pages.get(i))) {
					//System.out.println("PAGE FAULT AT PAGE #: " + this.pages.get(i));
					int firstPage = inMemoryPages.peek();
					inMemoryPages.poll();
					/*
					 * Remove it from the frame
					 */
					s.remove(firstPage);
					/*
					 *Insert the current page 
					 */
					s.add(pages.get(i));
					/*
					 * Insert the current page to in memory pages
					 */
					inMemoryPages.add(pages.get(i));
					pageFaults++;
				}
			}
		}
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / numberOfPages;
		percentage = percentage * 100;
		
		return df2.format(percentage);
		
	}
	
}
