package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Optimal {
	/**
	 * Holds the frame size
	 */
	int frameSize;
	/**
	 * Holds the pages
	 * page = virtual address / page size
	 */
	ArrayList<Integer> pages = new ArrayList<Integer>();
	/**
	 * Creates an instance of Optimal algorithm with
	 * given frameSize and pages
	 * @param frameSize
	 * @param pages
	 */
	public Optimal(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}
	
	/**
	 * Function to find the frame that will not be used in
	 * the longest time after the given index
	 * @param pg
	 * @param fr
	 * @param pn
	 * @param index
	 * @return
	 */
	static int predict( ArrayList<Integer> pages, ArrayList<Integer> frame, int pn, int index) 
	{
	    // Store the index of pages which are going 
	    // to be used recently in future 
	    int res = -1, farthest = index; 
	    for (int i = 0; i < frame.size(); i++) { 
	        int j; 
	        for (j = index; j < pn; j++) { 
	            if (frame.get(i) == pages.get(j)) { 
	                if (j > farthest) { 
	                    farthest = j; 
	                    res = i; 
	                } 
	                break; 
	            } 
	        } 

	        /*
	         * If the page is never again referenced,
	         * return it
	         */
	        if (j == pn) 
	            return i; 
	    } 

	    /*
	     * If all the frames were not present in the future
	     * pages, return 0, else return the result
	     */
	    return (res == -1) ? 0 : res; 
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
	/*
	 * Performs optimal page replacement algorithm
	 */
	public String optimalPageReplacmentAlgo() 
	{
	    // Create an array for given number of 
	    // frames and initialize it as empty.
	    ArrayList<Integer> fr = new ArrayList<>(this.frameSize); 
	  
	    // Traverse through page reference array 
	    // and check for miss and hit. 
	    int hit = 0;
	    for (int i = 0; i < pages.size(); i++) {
	  
	        // Page found in a frame : HIT 
	        if (search(pages.get(i), fr)) { 
	            hit++; 
	            continue; 
	        } 
	  
	        // Page not found in a frame : MISS 
	  
	        // If there is space available in frames. 
	        if (fr.size() < frameSize) 
	            fr.add(pages.get(i)); 
	  
	        // Find the page to be replaced. 
	        else { 
	            int j = predict(pages, fr, pages.size(), i + 1); 
	            fr.set(j, pages.get(i));
	        } 
	    }
	    
	    int pageFaults = pages.size() - hit;
		DecimalFormat df2 = new DecimalFormat("0.00");
		float percentage = ((float) pageFaults) / pages.size();
		percentage = percentage * 100;
		
		return df2.format(percentage);
	   
	} 
	
}
