package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Time;

public class BusinessHourDto {

	private String dayOfWeek;
	private Time startTime;
	private Time endTime;
	
	/**
	 * @param dayOfWeek
	 * @param startTime
	 * @param endTime
	 */
	public BusinessHourDto(String dayOfWeek, Time startTime, Time endTime) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public Time getEndTime() {
		return endTime;
	}

	
}
