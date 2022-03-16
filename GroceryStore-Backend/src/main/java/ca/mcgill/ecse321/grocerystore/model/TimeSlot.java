package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeSlot {
	// attribute fields
	private Long timeSlotID;
	private Date startDate;
	private Date endDate;
	private Time startTime;
	private Time endTime;

	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getTimeSlotID() {
		return timeSlotID;
	}

	public void setTimeSlotID(Long timeSlotID) {
		this.timeSlotID = timeSlotID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
