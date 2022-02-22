package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Order {

    private int orderID;
    private float totalValue;
    private Date date;
    private Time pruchaseTime;

    public void setOrderID(int aOrderID) {
        this.orderID = aOrderID;
    }

    public void setTotalValue(float aTotalValue) {
        this.totalValue = aTotalValue;
    }

    public void setDate(Date aDate) {
        date = aDate;
    }
    public void setPurchaseTime(Time aTime){
        this.pruchaseTime = aTime;
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

    public Time getPurchaseTime() {
        return pruchaseTime;
    }

    
}