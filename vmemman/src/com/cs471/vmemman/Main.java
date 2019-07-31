package com.cs471.vmemman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static ArrayList<Integer> virtualAddresses = new ArrayList<Integer>();
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("./src/com/cs471/vmemman/inputfile.txt"); 
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			virtualAddresses.add(Integer.parseInt(sc.nextLine()));	
		}
	}
}
