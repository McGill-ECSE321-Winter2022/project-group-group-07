package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TimeSlot {
    private int timeSlotID;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    @Id
    public int getTimeSlotID() {
        return timeSlotID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setTimeSlotID(int timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
