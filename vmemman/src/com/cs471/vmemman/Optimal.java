package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Optimal {

	int frameSize;
	ArrayList<Integer> pages = new ArrayList<Integer>();
	
	public Optimal(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
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
	
	public String optimalPageReplacmentAlgo() 
	{
	    // Create an array for given number of 
	    // frames and initialize it as empty. 
	    ArrayList<Integer> fr = new ArrayList<>(); 
	  
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
