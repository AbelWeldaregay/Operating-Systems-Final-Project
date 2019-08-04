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
							FIFO fifo = new FIFO(frames[f], pages);
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", fifo.FIFOPageReplacmentAlgo(), "|\n");		
						break;
						}
						case "LRU": {
							LRU lru = new LRU(frames[f], pages);
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", lru.LRUPageReplacmentAlgo(), "|\n");
						break;
						}
						case "MRU": {
							MRU mru = new MRU(frames[f], pages);
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", mru.MRUPageReplacmentAlgo(), "|\n");
						break;
						}
						case "OPT": {
							Optimal optimal = new Optimal(frames[f], pages);
							System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
						               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", optimal.optimalPageReplacmentAlgo(), "|\n");
						break;
						}
					}
				
				}
			}
			System.out.println("");
		}
		
	}
	
	public static void readInputFile() throws FileNotFoundException {
		File file = new File("./src/com/cs471/vmemman/inputfile.txt"); 
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			virtualAddresses.add(Integer.parseInt(sc.nextLine()));	
		}
	}
}
