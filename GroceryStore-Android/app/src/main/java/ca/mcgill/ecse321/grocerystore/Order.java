package ca.mcgill.ecse321.grocerystore;

public class Order {
    private String orderID;
    private String status;
    private TimeSlot timeSlot;

    public Order(String orderID, String status, TimeSlot timeSlot) {
        this.orderID = orderID;
        this.status = status;
        this.timeSlot = timeSlot;
    }

    public Order() {

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
