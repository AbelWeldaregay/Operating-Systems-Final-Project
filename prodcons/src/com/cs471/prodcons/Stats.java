package com.cs471.prodcons;

import java.util.ArrayList;

public class Stats {
	
	ArrayList<SalesRecord> globalStats;
	private float storeWideSales;
	/**
	 * Holds the algorithms to be conducted
	 */
	public static String[] statTypes = {"Store-wide", "month-wise", "aggregate-sales"};
	public Stats(ArrayList<SalesRecord> globalStats) {
		this.globalStats = globalStats;	
	}
	
	public void calculateStats() {
		
		for (int s = 0; s < statTypes.length; s++) {
			
			switch(statTypes[s]) {
			case "month-wise": {
				for (int i = 1; i <= 12; i++) {
					this.storeWideSales = 0;
					for (int j = 0; j < globalStats.size(); j++) {
						
						if (i == this.globalStats.get(j).getSaleMonth()) {
							storeWideSales = storeWideSales + this.globalStats.get(j).getSaleAmount();
						}
					}
					
					System.out.println("Month: " + i + " Sale Amount: " + storeWideSales);
				}
				
				break;
			}
			case "store-wide": {
				break;
			}
			case "aggregate-sales": {
				break;
			}
			}
			
		}
	}
	
	public int getMonthFromSalesDate(SalesRecord salesRecord) {
		System.out.println(salesRecord.getSalesDate());
//		String str = salesRecord.getSalesDate();
//		String kept = str.substring( 0, str.indexOf("/"));
//		System.out.println("month: " + kept);
		return 1;
	}
	
	
}
