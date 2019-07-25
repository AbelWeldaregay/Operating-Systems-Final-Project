package com.cs471.prodcons;

import java.text.SimpleDateFormat;
import java.util.*;

//import sun.tools.jar.Main;

public class SalesRecord {
	// Variable Elements
	String salesDate;
	int storeID;
	int registerNo;
	float saleAmount;

	// Constructors
	public SalesRecord(int proID) {
		setRandDate();
		setRandReg();
		setRandSale();
		storeID=proID; // store ID = producerID
	}

	SalesRecord(String date, int store, int register, float sale) {
		@SuppressWarnings("deprecation")
		Date aDate = new Date(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(aDate);
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yy");
		String newDate = fm.format(aDate);

		this.salesDate = newDate;
		this.storeID = store;
		this.registerNo = register;
		this.saleAmount = sale;
	};

	// toString Function
	public String toString() {
		return "\tSales Date: " + salesDate + "\n\tStore ID: " + Integer.toString(storeID) + "\n\tRegister No.: "
				+ Integer.toString(registerNo) + "\n\tSale Amount: $" + Float.toString(saleAmount) + "\n";
	}

	// Gets
	String getSalesDate() {
		return this.salesDate;
	};

	int getStoreID() {
		return this.storeID;
	};

	int getRegisterNo() {
		return this.registerNo;
	};

	float getSaleAmount() {
		return this.saleAmount;
	};

	// Sets
	@SuppressWarnings("deprecation")
	void setSalesDate(String date) {
		Date aDate = new Date(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(aDate);
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yy");
		this.salesDate = fm.format(aDate);
	}

	void setStoreID(int store) {
		this.storeID = store;
	};

	void setRegisterNo(int register) {
		this.registerNo = register;
	};

	void setSaleAmount(float sale) {
		this.saleAmount = sale;
	};

	// Random Utilities
	int makeRandDay() {
		Random random = new Random();
		int day = random.nextInt(29) + 1;
		return day;
	}

	int makeRandMonth() {
		Random random = new Random();
		int month = random.nextInt(12);
		return month;
	}

	@SuppressWarnings("deprecation")
	void setRandDate() {
		int day = makeRandDay();
		int month = makeRandMonth();

		Date date = new Date();
		date.setMonth(month);
		date.setYear(2016);
		date.setDate(day);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yy");
		this.salesDate = fm.format(date);
	}

	void setRandStoreId() {
		Random random = new Random();
		int p = BoundedBuffer.getProducers();
		this.storeID = random.nextInt(p) + 1;
	}

	void setRandReg() {
		Random random = new Random();
		this.registerNo = random.nextInt(5) + 1;
	}

	void setRandSale() {
		Random random = new Random();
		this.saleAmount = 50 + (949) * random.nextFloat();
	}

}
