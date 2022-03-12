package ca.mcgill.ecse321.grocerystore.dto;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import java.util.Collections;
import java.util.List;

public class ScheduleDto {

	// attributes
	private Integer scheduleID;
	private Employee employee;
	private List<WorkingHourDto> workingHours;

	// constructor
	public ScheduleDto() {
	}

	@SuppressWarnings("unchecked")
	public ScheduleDto(Integer scheduleID) {
		this(scheduleID, Collections.EMPTY_LIST);
	}

	public ScheduleDto(Integer scheduleID, List<WorkingHourDto> arrayList) {
		this.scheduleID = scheduleID;
		this.workingHours = arrayList;
	}

	public ScheduleDto(Employee employee, Integer scheduleID) {
		this.employee = employee;
		this.scheduleID = scheduleID;
	}

	// getter
	public Integer getScheduleID() {
		return this.scheduleID;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public List<WorkingHourDto> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHourDto> workingHours) {
		this.workingHours = workingHours;
	}
}