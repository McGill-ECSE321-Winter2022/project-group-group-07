package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Date;

// line 10 "model.ump"
// line 32 "model.ump"
public class Order {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // Order Attributes
    private int orderID;
    //private OrderType orderType;
    private float totalValue;
    private Date date;

    // ------------------------
    // CONSTRUCTOR
    // ------------------------

    public Order(int aOrderID, float aTotalValue, Date aDate) {
        orderID = aOrderID;
        //orderType = aOrderType;
        totalValue = aTotalValue;
        date = aDate;
    }

    // ------------------------
    // INTERFACE
    // ------------------------

    public boolean setOrderID(int aOrderID) {
        boolean wasSet = false;
        orderID = aOrderID;
        wasSet = true;
        return wasSet;
    }

    // public boolean setOrderType(OrderType aOrderType) {
    //     boolean wasSet = false;
    //     orderType = aOrderType;
    //     wasSet = true;
    //     return wasSet;
    // }

    public boolean setTotalValue(float aTotalValue) {
        boolean wasSet = false;
        totalValue = aTotalValue;
        wasSet = true;
        return wasSet;
    }

    public boolean setDate(Date aDate) {
        boolean wasSet = false;
        date = aDate;
        wasSet = true;
        return wasSet;
    }

    public int getOrderID() {
        return orderID;
    }

    // public OrderType getOrderType() {
    //     return orderType;
    // }

    public float getTotalValue() {
        return totalValue;
    }

    public Date getDate() {
        return date;
    }

    public void delete() {
    }

    // public String toString() {
    //     return super.toString() + "[" +
    //             "orderID" + ":" + getOrderID() + "," +
    //             "totalValue" + ":" + getTotalValue() + "]" + System.getProperties().getProperty("line.separator") +
    //             "  " + "orderType" + "="
    //             + (getOrderType() != null
    //                     ? !getOrderType().equals(this) ? getOrderType().toString().replaceAll("  ", "    ") : "this"
    //                     : "null")
    //             + System.getProperties().getProperty("line.separator") +
    //             "  " + "date" + "="
    //             + (getDate() != null ? !getDate().equals(this) ? getDate().toString().replaceAll("  ", "    ") : "this"
    //                     : "null");
    // }
}