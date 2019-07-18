package com.cs471.prodcons;

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