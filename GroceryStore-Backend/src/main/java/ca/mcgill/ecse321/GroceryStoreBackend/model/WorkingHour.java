package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class WorkingHour {

    //TODO: sort out this DayOfWeek thing
    private enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
    
    private int workingHourID;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;

    @Id
    public int getWorkingHourID() {
        return workingHourID;
    }

    public void setWorkingHourID(int workingHourId) {
        this.workingHourID = workingHourId;
    }

    public DayOfWeek getDayOfWeek(){
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek){
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

    private Schedule schedule;

    @OneToOne(optional = false)
    public Schedule getSchedule(){
        return this.schedule;
    }

    public void setSchedule(Schedule schedule){
        this.schedule = schedule;
    }
}
