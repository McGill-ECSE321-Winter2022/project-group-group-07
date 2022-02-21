package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class DeliveryOrder extends Order {
    private User user;

    @OneToOne(optional = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    private TimeSlot timeSlot;

    @OneToOne(optional = false)
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlots) {
        this.timeSlot = timeSlots;
    }

}
