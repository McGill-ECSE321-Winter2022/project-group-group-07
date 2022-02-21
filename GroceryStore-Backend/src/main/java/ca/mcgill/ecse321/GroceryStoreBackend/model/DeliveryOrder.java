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

    private Set<TimeSlot> timeSlot;

    @OneToOne(optional = false)
    public Set<TimeSlot> getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(Set<TimeSlot> timeSlots) {
        this.timeSlot = timeSlots;
    }

}
