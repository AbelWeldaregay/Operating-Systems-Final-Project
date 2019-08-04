package com.cs471.prodcons;

import java.util.ArrayList;

public class Stats {
	
	ArrayList<SalesRecord> globalStats;
	/**
	 * Holds the algorithms to be conducted
	 */
	public static String[] statTypes = {"store-wide", "month-wise", "aggregate-sales"};
	private int numberOfProducers = 0;
	
	public Stats(ArrayList<SalesRecord> globalStats, int numOfProducers) {
		this.globalStats = globalStats;	
		this.numberOfProducers = numOfProducers;
	}
	
	public void calculateStats() {
		
		for (int s = 0; s < statTypes.length; s++) {
			
			switch(statTypes[s]) {
			case "month-wise": {
				System.out.println("*********Month**Wide**Sales*********");
				for (int i = 1; i <= 12; i++) {
					float monthlySales = 0;
					for (int j = 0; j < globalStats.size(); j++) {
						
						if (i == this.globalStats.get(j).getSaleMonth()) {
							monthlySales = monthlySales + this.globalStats.get(j).getSaleAmount();
						}
					}
					
					System.out.println("Month: " + i + " Total Sales: " + monthlySales);
				}
				
				break;
			}
			case "store-wide": {
				System.out.println("*********Store**Wide**Sales*********");
				for (int i = 0; i < this.numberOfProducers; i++) {
					float storeWideSales = 0;
					
					for (int j = 0; j < this.globalStats.size(); j++) {
						if (this.globalStats.get(j).getStoreId() == i) {
							storeWideSales = storeWideSales + this.globalStats.get(j).getSaleAmount();
						}
					}
					System.out.println("Store ID: " + i + " Total Sales: " + storeWideSales);
				}
				
				break;
			}
			case "aggregate-sales": {
				System.out.println("*********Aggregate**Of**Sales*********");
				float totalSales = 0;
				for (int i = 0; i < this.globalStats.size(); i++) {
					totalSales = totalSales + this.globalStats.get(i).getSaleAmount();
				}
				System.out.println(" Total Sales: " + totalSales);
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
