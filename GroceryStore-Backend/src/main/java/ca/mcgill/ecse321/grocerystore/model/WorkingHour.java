package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;

@Entity
public class WorkingHour {

	// attribute fields
	private Long workingHourID;
	private DayOfWeek dayOfWeek;
	private Time startTime;
	private Time endTime;

	// setters and getters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getWorkingHourID() {
		return workingHourID;
	}

	public void setWorkingHourID(Long workingHourId) {
		this.workingHourID = workingHourId;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

}
