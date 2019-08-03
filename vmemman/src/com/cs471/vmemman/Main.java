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
		
		FIFO fifo = new FIFO(512 ,virtualAddresses, 4 );
		int fifoPageFaults = fifo.pageFaults();
		System.out.println("# OF PAGE FAULTS FIFO: " + fifoPageFaults);
		LRU lru = new LRU(512, virtualAddresses, 4);
		int lruPageFaults = lru.pageFaults();
		System.out.println("# OF PAGE FAULTS LRU: " + lruPageFaults);
		MRU mru = new MRU(512, virtualAddresses, 4);
		int mruPageFaults = mru.pageFaults();
		System.out.println("# of PAGE FAULTS MRU: " + mruPageFaults );
	}
}
