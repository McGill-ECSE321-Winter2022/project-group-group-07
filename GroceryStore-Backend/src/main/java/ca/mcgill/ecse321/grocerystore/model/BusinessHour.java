package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class BusinessHour {
	public enum DayOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	// attribute fields
	private Integer businessHourId;
	private DayOfWeek dayOfWeek;
	private Time startTime;
	private Time endTime;

	// association fields
	private Store store;

	// getters and setters
	@Id
	public int getBusinessHourId() {
		return businessHourId;
	}

	public void setBusinessHourId(Integer aBusinessHourId) {
		businessHourId = aBusinessHourId;
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
