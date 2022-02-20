package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WorkingHour {
    
    private int workingHourID;
    private Date dayOfWeek;
    private Time startTime;
    private Time endTime;

    @Id
    public int getWorkingHourID() {
        return workingHourID;
    }

    public void setWorkingHourID(int workingHourId) {
        this.workingHourID = workingHourId;
    }

    public Date getDayOfWeek(){
        return dayOfWeek;
    }

    public void setDayOfWeek(Date dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStartTime(){
        return startTime;
    }

    public void setStartTime(Time startTime){
        this.startTime = startTime;
    }

    public Time getEndTime(){
        return endTime;
    }

    public void setEndTime(Time endTime){
        this.endTime = endTime;
    }
}
