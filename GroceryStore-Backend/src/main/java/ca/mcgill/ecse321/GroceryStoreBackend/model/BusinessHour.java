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
    private GroceryStoreSoftwareSystem system;

    public BusinessHour(int aBusinessHourId, DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime, Store aStore, GroceryStoreSoftwareSystem aGsss)
    {
        businessHourId = aBusinessHourId;
        dayOfWeek = aDayOfWeek;
        startTime = aStartTime;
        endTime = aEndTime;
        if (!setStore(aStore))
        {
            throw new RuntimeException("Unable to create BusinessHour because the given Store is null.");
        }
        if (!setGsss(aGsss))
        {
        throw new RuntimeException("Unable to create hour because the given System is null.");
        }
    }
    //------------------------
    // INTERFACE
    //------------------------
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

    @ManyToOne(optional=false)
    public GroceryStoreSoftwareSystem getGsss()
    {
        return system;
    }

    public boolean setStore(Store aNewStore)
    {
        if (aNewStore != null){
            store = aNewStore;
            return true;
        }
        return false;
    }

    public boolean setGsss(GroceryStoreSoftwareSystem aGsss)
    {
        if (aGsss == null)
        {
            return false;
        }
        GroceryStoreSoftwareSystem existingGsss = system;
        system = aGsss;
        if (existingGsss != null && !existingGsss.equals(aGsss)){
            existingGsss.removeHour(this);
        }
        system.addHour(this);
        return true;
    }
    public void delete()
    {
        store = null;
        GroceryStoreSoftwareSystem placeholderGsss = system;
        this.system = null;
        if(placeholderGsss != null){
            placeholderGsss.removeHour(this);
        }
    }
    public String toString() {
    return super.toString() + "["+
    "businessHourId" + ":" + getBusinessHourId()+ "]" + System.getProperties().getProperty("line.separator") +
    "  " + "dayOfWeek" + "=" + (getDayOfWeek() != null ? !getDayOfWeek().equals(this)  ? getDayOfWeek().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
    "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
    "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
    "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null") + System.getProperties().getProperty("line.separator") +
    "  " + "gsss = "+(getGsss()!=null?Integer.toHexString(System.identityHashCode(getGsss())):"null");
    }
}
