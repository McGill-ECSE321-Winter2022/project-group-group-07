package ca.mcgill.ecse321.GroceryStoreBackend.model;

public class Cart {

    // ------------------------
    // MEMBER VARIABLES
    // ------------------------

    // Cart Attributes
    private int cartID;
    // private OrderType orderType;
    private float totalValue;
    private int numOfItems;

    // ------------------------
    // CONSTRUCTOR
    // ------------------------

    public Cart(int aCartID, float aTotalValue, int aNumOfItems) {
        cartID = aCartID;
        // orderType = aOrderType;
        totalValue = aTotalValue;
        numOfItems = aNumOfItems;
    }

    // ------------------------
    // INTERFACE
    // ------------------------

    public boolean setCartID(int aCartID) {
        boolean wasSet = false;
        cartID = aCartID;
        wasSet = true;
        return wasSet;
    }

    // public boolean setOrderType(OrderType aOrderType) {
    // boolean wasSet = false;
    // orderType = aOrderType;
    // wasSet = true;
    // return wasSet;
    // }

    public boolean setTotalValue(float aTotalValue) {
        boolean wasSet = false;
        totalValue = aTotalValue;
        wasSet = true;
        return wasSet;
    }

    public boolean setNumOfItems(int aNumOfItems) {
        boolean wasSet = false;
        numOfItems = aNumOfItems;
        wasSet = true;
        return wasSet;
    }

    public int getCartID() {
        return cartID;
    }

    // public OrderType getOrderType() {
    // return orderType;
    // }

    public float getTotalValue() {
        return totalValue;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void delete() {
    }

    // public String toString() {
    // return super.toString() + "[" +
    // "cartID" + ":" + getCartID() + "," +
    // "totalValue" + ":" + getTotalValue() + "," +
    // "numOfItems" + ":" + getNumOfItems() + "]" +
    // System.getProperties().getProperty("line.separator") +
    // " " + "orderType" + "="
    // + (getOrderType() != null
    // ? !getOrderType().equals(this) ? getOrderType().toString().replaceAll(" ", "
    // ") : "this"
    // : "null");
    // }
}