package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class DeliveryOrder extends Order {

	// association attributes
	private TimeSlot timeSlot;

	// getters and setters
	@OneToOne(optional = false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlots) {
		this.timeSlot = timeSlots;
	}

}
