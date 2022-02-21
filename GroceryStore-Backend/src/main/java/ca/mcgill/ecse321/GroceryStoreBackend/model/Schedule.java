package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Schedule {
    
    private int scheduleID;

    @Id
    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }
}
