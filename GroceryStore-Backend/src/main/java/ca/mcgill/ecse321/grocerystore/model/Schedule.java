package ca.mcgill.ecse321.grocerystore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {
    
    private Integer scheduleID;

    @Id
    public Integer getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Integer scheduleID) {
        this.scheduleID = scheduleID;
    }

    private Set<WorkingHour> workingHour;

    @OneToMany(cascade={CascadeType.ALL})
    public Set<WorkingHour> getWorkingHour() {
        return this.workingHour;
    }
    
    public void setWorkingHour(Set<WorkingHour> workingHour) {
        this.workingHour = workingHour;
    }

    
    private Employee employee;

    @OneToOne(optional = false)
    public Employee getEmployee(){
        return this.employee;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }
}
