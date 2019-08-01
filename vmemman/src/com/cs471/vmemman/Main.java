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
		
		for (int i = 0; i < algorithms.length; i++) {
			for (int j = 0; j < pageSizes.length; j++) {
				for (int x = 0; x < frames.length; x++) {
					
				}
			}
		}
		
		
	}
}
