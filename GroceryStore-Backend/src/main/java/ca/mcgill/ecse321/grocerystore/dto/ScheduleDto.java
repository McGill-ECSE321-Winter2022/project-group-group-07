package ca.mcgill.ecse321.grocerystore.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.Employee;

public class ScheduleDto {

	// attributes
	private Long scheduleID;
	private Employee employee;
	private List<WorkingHourDto> workingHours;

	// constructor
	public ScheduleDto() {
	}

	@SuppressWarnings("unchecked")
	public ScheduleDto(Long scheduleID) {
		this(scheduleID, Collections.EMPTY_LIST);
	}

	public ScheduleDto(Long scheduleID, List<WorkingHourDto> arrayList) {
		this.scheduleID = scheduleID;
		this.workingHours = arrayList;
	}

	public ScheduleDto(Employee employee, Long scheduleID) {
		this.employee = employee;
		this.scheduleID = scheduleID;
	}

	// getter
	public Long getScheduleID() {
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