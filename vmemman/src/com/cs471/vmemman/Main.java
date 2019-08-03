package com.cs471.vmemman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static ArrayList<Integer> virtualAddresses = new ArrayList<Integer>();
	public static int[] pageSizes = {512, 1024, 2048};
	public static int[] frames = {4, 8, 12};
	public static String[] algorithms = {"FIFO", "LRU", "MRU"};
	public static void main(String[] args) throws FileNotFoundException {
		readInputFile();
	}
	
	public static void readInputFile() throws FileNotFoundException {
		File file = new File("./src/com/cs471/vmemman/inputfile.txt"); 
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			virtualAddresses.add(Integer.parseInt(sc.nextLine()));	
		}
		System.out.println(" _______________________________________________________");
		System.out.println("| Page Size | # of Pages | Algorithm | # of Page Faults |");
		for (int a = 0; a < algorithms.length; a++) {
			for (int p = 0; p < pageSizes.length; p++) {
				for (int f = 0; f < frames.length; f++) {
					
					if (algorithms[a].equals("FIFO")) {
						FIFO fifo = new FIFO(pageSizes[p], virtualAddresses, frames[f]);
						System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
				               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", Integer.toString(fifo.pageFaults()), "|\n");

					} else if (algorithms[a].equals("LRU")) {
						LRU lru = new LRU(pageSizes[p], virtualAddresses, frames[f]);
						System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
					               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", Integer.toString(lru.pageFaults()), "|\n");

					} else if (algorithms[a].equals("MRU")) {
						MRU mru = new MRU(pageSizes[p], virtualAddresses, frames[f]);
						System.out.format("%1s %5s %5s %5s %6s %6s %4s %10s %8s",
					               "|" ,Integer.toString(pageSizes[p]), "|", Integer.toString(frames[f]), "|" , algorithms[a], "|", Integer.toString(mru.pageFaults()), "|\n");

					}
				}
			}
		}
		
	}
}
