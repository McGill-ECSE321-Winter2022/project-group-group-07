package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DeliveryOrderStatus;

@Entity
public class DeliveryOrder extends Order {

	// attribute fields
	private DeliveryOrderStatus status;
	// association fields
	private TimeSlot timeSlot;

	// getters and setters
	@OneToOne(optional = false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlots) {
		this.timeSlot = timeSlots;
	}

	public DeliveryOrderStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryOrderStatus status) {
		this.status = status;
	}

}
