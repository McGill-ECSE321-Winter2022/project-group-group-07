package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {

    // attributes
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;

    // constructor
    public TimeSlotDto() {
    }

    public TimeSlotDto(Date startDate, Date endDate, Time startTime, Time endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getter
    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }
}
