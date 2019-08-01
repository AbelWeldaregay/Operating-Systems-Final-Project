package com.cs471.vmemman;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO {

	static Queue<Integer> inMemoryPages = new LinkedList<Integer>();
	private int pageSize;
	private ArrayList<Integer> virtualAddresses;
	private int frameSize;
	ArrayList<Integer> pages = new ArrayList<Integer>();
	
	public FIFO(int pageSize, ArrayList<Integer> virtualAddresses, int frameSize) {
		this.pageSize = pageSize;
		this.frameSize = frameSize;
		this.virtualAddresses = virtualAddresses;
		for (int i = 0; i < this.virtualAddresses.size(); i++) {
			this.pages.add(UtilityClass.getPageNumber(this.virtualAddresses.get(i), this.pageSize));
		}
	}
	
	int pageFaults() {
		
		HashSet<Integer> s = new HashSet<>(frameSize);
		
		int pageFaults = 0;
		int numberOfPages = this.pages.size();
		for (int i = 0; i < numberOfPages; i++) {
			// check if the current frame has space
			if (s.size() < frameSize) {
				/*
				 *Insert it into set if not present
				 *already which represents page fault 
				 */
				if (!s.contains(this.pages.get(i))) {
					s.add(this.pages.get(i));
					System.out.println("PAGE FAULT AT PAGE #: " + this.pages.get(i));
					pageFaults++;
					/*
					 * Push the current page into the queue
					 */
					inMemoryPages.add(this.pages.get(i));
				} else {
					System.out.println("PAGE HIT PAGE #: " + this.pages.get(i));
				}
				
			} 
			/*
			 * If the current frame if full then perform FIFO to remove the first
			 * page of the queue from set and queue both and insert the current page
			 */
			else {
				
				if (!s.contains(this.pages.get(i))) {
					System.out.println("PAGE FAULT AT PAGE #: " + this.pages.get(i));
					int firstPage = inMemoryPages.peek();
					inMemoryPages.poll();
					/*
					 * Remove it from the frame
					 */
					s.remove(firstPage);
					/*
					 *Insert the current page 
					 */
					s.add(this.pages.get(i));
					/*
					 * Insert the current page to in memory pages
					 */
					inMemoryPages.add(this.pages.get(i));
					pageFaults++;
				} else {
					System.out.println("PAGE HIT AT PAGE #: " + this.pages.get(i));
				}
				
				
			}
		}
		
		return pageFaults;
		
	}
	
}
