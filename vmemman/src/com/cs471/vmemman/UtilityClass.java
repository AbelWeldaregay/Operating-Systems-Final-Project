package com.cs471.vmemman;

public class UtilityClass {

	public static int getPageNumber(int virtualAddress, int pageSize) {
		return virtualAddress / pageSize;
	}
	
}
