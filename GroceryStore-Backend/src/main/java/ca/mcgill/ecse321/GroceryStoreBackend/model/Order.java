package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import java.util.Set;

public abstract class Order {

    private int orderID;
    private float totalValue;
    private Date date;
    private Time pruchaseTime;

    public boolean setOrderID(int aOrderID) {
        boolean wasSet = false;
        orderID = aOrderID;
        wasSet = true;
        return wasSet;
    }

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
    public boolean setPurchaseTime(Time aTime){
        boolean wasSet = false;
        pruchaseTime = aTime;
        wasSet = true;
        return wasSet;
    }


    private Set<Item> items;

    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Item> getItems() {
        return this.items;
    }

    public void setItems(Set<Item> itemss) {
        this.items = itemss;
    }

    @Id
    public int getOrderID() {
        return orderID;
    }

    public float getTotalValue() {
        return totalValue;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return pruchaseTime;
    }

    
}