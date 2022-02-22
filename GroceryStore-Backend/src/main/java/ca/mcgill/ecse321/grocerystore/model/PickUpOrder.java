package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
public class PickUpOrder extends Order {
    private Account user;

    @OneToOne(optional = false)
    public Account getUser() {
        return this.user;
    }

    public void setUser(Account users) {
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
