package com.cs471.vmemman;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static ArrayList<Integer> virtualAddresses = new ArrayList<Integer>();
	public static int[] pageSizes = {512, 1024, 2048};
	public static int[] frames = {4, 8, 12};
	public static String[] algorithms = {"FIFO", "LRU", "MRU", "OPT"};
	public static ArrayList<Integer> pages = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException {
		readInputFile();
		
		for (int i = 0; i < virtualAddresses.size(); i++) {
			pages.add(virtualAddresses.get(i)/512);
		}
		
		System.out.println(" _______________________________________________________");
		System.out.println("| Page Size | # of Pages | Algorithm | % of Page Faults |");
		System.out.println("");
		for (int a = 0; a < algorithms.length; a++) {
			for (int p = 0; p < pageSizes.length; p++) {
				pages.clear();
				for (int i = 0; i < virtualAddresses.size(); i++) {
					pages.add(virtualAddresses.get(i)/pageSizes[p]);
				}
				
				for (int f = 0; f < frames.length; f++) {
					switch (algorithms[a]) {
						case "FIFO": {
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", FIFOPageReplacmentAlgo(frames[f], pages), "|\n");		
						break;
						}
						case "LRU": {
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", LRUPageReplacmentAlgo(frames[f], pages), "|\n");
						break;
						}
						case "MRU": {
						
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", MRUPageReplacmentAlgo(frames[f], pages), "|\n");
						break;
						}
						case "OPT": {
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", optimalPageReplacmentAlgo(frames[f], pages), "|\n");
						break;
						}
					}
				
				}
			}
			System.out.println("");
		}
		
	}
	
	/**
	 * Function to check if a page exists in a frame or not
	 * @param key
	 * @param frame
	 * @return
	 */
	static boolean search(int key, ArrayList<Integer> frame) {
		for (int i = 0; i < frame.size(); i++) {
			if (frame.get(i) == key) {
				return true;
			}
		}
		return false;
	}
	
	// Function to find the frame that will not be used 
	// recently in future after given index in pg[0..pn-1] 
	static int predict( ArrayList<Integer> pg, ArrayList<Integer> fr, int pn, int index) 
	{ 
	    // Store the index of pages which are going 
	    // to be used recently in future 
	    int res = -1, farthest = index; 
	    for (int i = 0; i < fr.size(); i++) { 
	        int j; 
	        for (j = index; j < pn; j++) { 
	            if (fr.get(i) == pg.get(j)) { 
	                if (j > farthest) { 
	                    farthest = j; 
	                    res = i; 
	                } 
	                break; 
	            } 
	        } 
	  
	        // If a page is never referenced in future, 
	        // return it. 
	        if (j == pn) 
	            return i; 
	    } 
	  
	    // If all of the frames were not in future, 
	    // return any of them, we return 0. Otherwise 
	    // we return res. 
	    return (res == -1) ? 0 : res; 
	}
	
	public static String optimalPageReplacmentAlgo(int frameSize, ArrayList<Integer> pg) 
	{ 
	    // Create an array for given number of 
	    // frames and initialize it as empty. 
	    ArrayList<Integer> fr = new ArrayList<>(); 
	  
	    // Traverse through page reference array 
	    // and check for miss and hit. 
	    int hit = 0;
	    for (int i = 0; i < pages.size(); i++) {
	  
	        // Page found in a frame : HIT 
	        if (search(pg.get(i), fr)) { 
	            hit++; 
	            continue; 
	        } 
	  
	        // Page not found in a frame : MISS 
	  
	        // If there is space available in frames. 
	        if (fr.size() < frameSize) 
	            fr.add(pg.get(i)); 
	  
	        // Find the page to be replaced. 
	        else { 
	            int j = predict(pg, fr, pages.size(), i + 1); 
	            fr.set(j, pg.get(i));
	        } 
	    }
	    int pageFaults = pages.size() - hit;
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage);
//	    return (pages.size() - hit);
	   
	} 
	  
	
	// Method to find page faults using indexes 
	static String LRUPageReplacmentAlgo(int frameSize, ArrayList<Integer> pages) 
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
	
	public static String FIFOPageReplacmentAlgo(int frameSize, ArrayList<Integer> pages) {
		
		HashSet<Integer> s = new HashSet<>();
		
		int pageFaults = 0;
		int hits = 0;
		int numberOfPages = pages.size();
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
	
	public static String MRUPageReplacmentAlgo(int frameSize, ArrayList<Integer> pages) {
		
		HashSet<Integer> s = new HashSet<>(frameSize);
		
		HashMap<Integer, Integer> indexes = new HashMap<>();
		
		int pageFaults = 0;
		int n = pages.size();
		for (int i = 0; i < n; i++) {
			
			if (s.size() < frameSize) {
			
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
		
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage);
	}
	
	
	public static void readInputFile() throws FileNotFoundException {
		File file = new File("./src/com/cs471/vmemman/inputfile.txt"); 
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			virtualAddresses.add(Integer.parseInt(sc.nextLine()));	
		}
	}
}
