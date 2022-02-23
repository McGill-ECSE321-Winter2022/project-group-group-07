package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class DeliveryOrder extends Order {

	private TimeSlot timeSlot;

	@OneToOne(optional = false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlots) {
		this.timeSlot = timeSlots;
	}

}
