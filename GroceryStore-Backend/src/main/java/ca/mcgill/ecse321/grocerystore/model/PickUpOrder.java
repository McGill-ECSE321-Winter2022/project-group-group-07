package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.PickUpOrderStatus;

@Entity
public class PickUpOrder extends Order {
    //association fields
    private TimeSlot timeSlot;
    private PickUpOrderStatus status;

    @OneToOne(optional = false)
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlots) {
        this.timeSlot = timeSlots;
    }

	public PickUpOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PickUpOrderStatus status) {
		this.status = status;
	}
}
