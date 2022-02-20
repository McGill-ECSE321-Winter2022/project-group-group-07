package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Date;
import java.util.*;

public class Report {

    private int reportID;
    private Date startDate;
    private Date endDate;
    private float totalValue;
    private List<Order> orders;

    public Report(Date aStartDate, Date aEndDate) {
        generateReportID();
        startDate = aStartDate;
        endDate = aEndDate;
        orders = new ArrayList<Order>();
        generateTotalValue();
    }

    public boolean generateReportID() {
        boolean wasSet = false;
        // reportID = //replace//;
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

    private boolean generateTotalValue() {
        boolean wasSet = false;
        // totalValue = ///replace//;
        wasSet = true;
        return wasSet;
    }

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

    public Order getOrder(int index) {
        Order aOrder = orders.get(index);
        return aOrder;
    }

    public List<Order> getOrders() {
        List<Order> newOrders = Collections.unmodifiableList(orders);
        return newOrders;
    }

    public int numberOfOrders() {
        int number = orders.size();
        return number;
    }

    public boolean hasOrders() {
        boolean has = orders.size() > 0;
        return has;
    }

    public int indexOfOrder(Order aOrder) {
        int index = orders.indexOf(aOrder);
        return index;
    }

    /* Code from template association_MinimumNumberOfMethod */
    public static int minimumNumberOfOrders() {
        return 0;
    }

    /* Code from template association_AddUnidirectionalMany */
    public boolean addOrder(Order aOrder) {
        boolean wasAdded = false;
        if (orders.contains(aOrder)) {
            return false;
        }
        orders.add(aOrder);
        wasAdded = true;
        return wasAdded;
    }

    public boolean removeOrder(Order aOrder) {
        boolean wasRemoved = false;
        if (orders.contains(aOrder)) {
            orders.remove(aOrder);
            wasRemoved = true;
        }
        return wasRemoved;
    }

    /* Code from template association_AddIndexControlFunctions */
    public boolean addOrderAt(Order aOrder, int index) {
        boolean wasAdded = false;
        if (addOrder(aOrder)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfOrders()) {
                index = numberOfOrders() - 1;
            }
            orders.remove(aOrder);
            orders.add(index, aOrder);
            wasAdded = true;
        }
        return wasAdded;
    }

    public boolean addOrMoveOrderAt(Order aOrder, int index) {
        boolean wasAdded = false;
        if (orders.contains(aOrder)) {
            if (index < 0) {
                index = 0;
            }
            if (index > numberOfOrders()) {
                index = numberOfOrders() - 1;
            }
            orders.remove(aOrder);
            orders.add(index, aOrder);
            wasAdded = true;
        } else {
            wasAdded = addOrderAt(aOrder, index);
        }
        return wasAdded;
    }

    public void delete() {
        orders.clear();
    }

    public String toString() {
        return super.toString() + "[" +
                "reportID" + ":" + getReportID() + "," +
                "totalValue" + ":" + getTotalValue() + "]" + System.getProperties().getProperty("line.separator") +
                "  " + "startDate" + "="
                + (getStartDate() != null
                        ? !getStartDate().equals(this) ? getStartDate().toString().replaceAll("  ", "    ") : "this"
                        : "null")
                + System.getProperties().getProperty("line.separator") +
                "  " + "endDate" + "="
                + (getEndDate() != null
                        ? !getEndDate().equals(this) ? getEndDate().toString().replaceAll("  ", "    ") : "this"
                        : "null");
    }
}