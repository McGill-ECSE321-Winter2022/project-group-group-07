package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Item {
    private int itemID;

    public void setItemID(int aItemID){
        this.itemID = aItemID;
    }
    
    @Id
    public int getItemID() {
        return this.itemID;
    }

    private String productName;

    public void setProductName(String aProductName) {
        this.productName = aProductName;
    }

    public String getProductName() {
        return productName;
    }

    private float price;

    public void setPrice(float aPrice) {
        this.price = aPrice;
    }

    public float getPrice() {
        return price;
    }

    private boolean availableOnline;

    public void availableOnline(boolean isAvailableOnline) {
        this.availableOnline = isAvailableOnline;
    }

    public boolean getAvailableOnline() {
        return availableOnline;
    }

    private int numInStock;

    public void setNumInStock(int numInStock) {
        this.numInStock = numInStock;
    }

    public int getNumInStock() {
        return numInStock;
    }
    
    private int pointPerItem;

    public void setPointPerItem(int somePointPerItem) {
        this.pointPerItem = somePointPerItem;
    }
    public int getPointPerItem() {
        return pointPerItem;
    }
}
