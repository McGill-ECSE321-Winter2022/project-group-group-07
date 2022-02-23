package ca.mcgill.ecse321.grocerystore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {
	//attribute fields
	private Employee employee;
	private Integer scheduleID;
	//association fields
	private Set<WorkingHour> workingHour;
	//getters and setters
	@Id
	public Integer getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(Integer scheduleID) {
		this.scheduleID = scheduleID;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<WorkingHour> getWorkingHour() {
		return this.workingHour;
	}
	public void setWorkingHour(Set<WorkingHour> workingHour) {
		this.workingHour = workingHour;
	}

	@OneToOne(optional = false)
	public Employee getEmployee() {
		return this.employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
