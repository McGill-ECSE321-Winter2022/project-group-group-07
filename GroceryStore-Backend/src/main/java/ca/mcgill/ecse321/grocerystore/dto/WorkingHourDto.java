package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Time;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;

public class WorkingHourDto {

	// attributes
	private Integer workingHourID;
	private DayOfWeek dayOfWeek;
	private Time startTime;
	private Time endTime;

	// constructor
	public WorkingHourDto() {
	}

	public WorkingHourDto(Integer workingHourID, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
		this.workingHourID = workingHourID;
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// getter
	public Integer getWorkingHourID() {
		return this.workingHourID;
	}

	public DayOfWeek getDayOfWeek() {
		return this.dayOfWeek;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public Time getEndTime() {
		return this.endTime;
	}

}