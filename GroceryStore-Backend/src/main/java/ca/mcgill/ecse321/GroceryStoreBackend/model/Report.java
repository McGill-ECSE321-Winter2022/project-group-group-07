package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Date;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Report {

    private int reportID;
    private Date startDate;
    private Date endDate;
    private float totalValue;

    public boolean setReportID(int reportIdReplacement) {
        boolean wasSet = false;
        reportID = reportIdReplacement;
        wasSet = true;
        return wasSet;
    }

    public boolean setStartDate(Date aStartDate) {
        boolean wasSet = false;
        startDate = aStartDate;
        wasSet = true;
        return wasSet;
    }

    public boolean setEndDate(Date aEndDate) {
        boolean wasSet = false;
        endDate = aEndDate;
        wasSet = true;
        return wasSet;
    }

    @Id
    public int getReportID() {
        return reportID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getTotalValue() {
        return totalValue;
    }

    private Set<Order> orders;

    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> newOrders) {
        this.orders = newOrders;
    }

}