package ca.mcgill.ecse321.GroceryStoreBackend.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class BusinessHour {
    public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

    //BusinessHour Attributes
    private int businessHourId;
    private DayOfWeek dayOfWeek;
    private Time startTime;
    private Time endTime;

    //BusinessHour Associations
    private Store store;

    public void setBusinessHourId(int aBusinessHourId){
        businessHourId = aBusinessHourId;
    }
    public void setDayOfWeek(DayOfWeek aDayOfWeek){
        dayOfWeek = aDayOfWeek;
    }
    public void setStartTime(Time aStartTime){
        startTime = aStartTime;
    }
    public void setEndTime(Time aEndTime){
        endTime = aEndTime;
    }
    @Id
    public int getBusinessHourId()
    {
        return businessHourId;
    }
    public DayOfWeek getDayOfWeek(){
        return dayOfWeek;
    }
    public Time getStartTime()
    {
        return startTime;
    }
    public Time getEndTime()
    {
        return endTime;
    }

    @ManyToOne(optional=false)
    public Store getStore()
    {
        return store;
    }

    public boolean setStore(Store aNewStore)
    {
        if (aNewStore != null){
            store = aNewStore;
            return true;
        }
        return false;
    }
}
