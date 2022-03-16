package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;

@Entity
public class BusinessHour {

	// attribute fields
	private Long businessHourID;
	private DayOfWeek dayOfWeek;
	private Time startTime;
	private Time endTime;

	// association fields
	private Store store;

	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getBusinessHourID() {
		return businessHourID;
	}

	public void setBusinessHourID(Long businessHourID) {
		this.businessHourID = businessHourID;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek aDayOfWeek) {
		dayOfWeek = aDayOfWeek;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time aStartTime) {
		startTime = aStartTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time aEndTime) {
		endTime = aEndTime;
	}

	@ManyToOne(optional = false)
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
}
