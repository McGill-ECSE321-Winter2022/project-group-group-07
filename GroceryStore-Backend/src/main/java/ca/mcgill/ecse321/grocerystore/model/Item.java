package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
    private Integer itemID;

    public void setItemID(Integer aItemID){
        this.itemID = aItemID;
    }

    @Id
    public Integer getItemID() {
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

    public void setAvailableOnline(boolean isAvailableOnline) {
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
