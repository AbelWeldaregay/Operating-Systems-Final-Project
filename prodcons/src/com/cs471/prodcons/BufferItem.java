package com.cs471.prodcons;

import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;

/**
 * @description represents a buffer item
 */
class BufferItem {
    /**
     * @description holds sales date
     */
    private String salesDate;
    /**
     * @description store id
     */
    private int storeId;
    /**
     * @description register id
     */
    private int registerId;
    /**
     * @description sale amount
     */
    private float saleAmount;

    public BufferItem() {
        this.salesDate = "EMPTY";
        this.storeId = 0;
        this.registerId = 0;
        this.saleAmount = 0;
    }
    
    public void generateAndSetRandomValues() {
    	String pattern = "yyyy-MM-dd";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	this.salesDate = simpleDateFormat.format(new Date());
    	this.storeId = 5; // for testing
    	Random r = new Random();
		this.registerId = r.nextInt((6 - 1) + 1) + 1;
		this.saleAmount = (float) ((float) 0.50 + Math.random() * (999.99 - 0.50));
    }

    public void setSaleAmount(float rhs) {
        this.saleAmount = rhs;
    }
    public void setRegisterId(int rhs) {
        this.registerId = rhs;
    }
    public void setStoreId(int rhs) {
        this.storeId = rhs;
    }
    public void setSalesDate(String rhs) {
        this.salesDate = rhs;
    }
    public String getSalesDate() {
        return this.salesDate;
    }

    public int getStoreId() {
        return this.storeId;
    }

    public int getRegisterId() {
        return this.registerId;
    }

    public float getSaleAmount() {
        return this.saleAmount;
    }

}