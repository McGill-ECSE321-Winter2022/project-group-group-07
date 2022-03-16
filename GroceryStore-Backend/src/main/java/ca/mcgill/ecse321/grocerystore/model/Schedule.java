package ca.mcgill.ecse321.grocerystore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {
	// attribute fields

	private Long scheduleID;
	// association fields
	private Set<WorkingHour> workingHour;
	private Employee employee;

	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(Long scheduleID) {
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
