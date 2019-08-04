package com.cs471.vmemman;

import java.text.DecimalFormat;
import java.util.*;

public class MRU {

	private int frameSize;
	private ArrayList<Integer> pages = new ArrayList<Integer>();
	
	public MRU(int frameSize, ArrayList<Integer> pages) {
		this.frameSize = frameSize;
		this.pages = pages;
	}
	
	public String MRUPageReplacmentAlgo() {
		
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
	
}
