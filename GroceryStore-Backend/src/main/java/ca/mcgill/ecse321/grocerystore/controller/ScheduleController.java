package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.ScheduleDto;
import ca.mcgill.ecse321.grocerystore.dto.WorkingHourDto;
import ca.mcgill.ecse321.grocerystore.model.Schedule;
import ca.mcgill.ecse321.grocerystore.model.WorkingHour;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	@Autowired
	private GroceryStoreService service;

	// Schedule

	@GetMapping(value = { "/schedule/{username}", "/schedule/{username}/" })
	public ScheduleDto getScheduleByEmployee(@PathVariable("username") String username) {

		return convertToDto(service.getScheduleByEmployee(username));
	}

	@GetMapping(value = { "/allSchedules", "/allSchedules/" })
	public List<ScheduleDto> getAllSchedules() {
		return service.getAllSchedules().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@PostMapping(value = { "/schedule", "/schedules/" })
	public ScheduleDto createScheduleForEmployee(@RequestParam String username) throws IllegalArgumentException {
		Schedule schedule = service.createSchedule(username);
		return convertToDto(schedule);
	}

	@PutMapping(value = { "/updateSchedule", "/updateSchedule/" })
	public ScheduleDto addWorkingHourToScheduleOfEmployee(@RequestParam String username, @RequestParam String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		return convertToDto(service.addWorkingHourToScheduleOfEmployee(username, DayOfWeek.valueOf(dayOfWeek),
				Time.valueOf(startTime), Time.valueOf(endTime)));
	}

	@DeleteMapping(value = { "/deleteSchedule/{username}", "/deleteSchedule/{username}" })
	public void deleteScheduleByEmployee(@PathVariable("username") String username) {
		service.deleteScheduleByEmployee(username);
	}

	// WorkingHours

	@GetMapping(value = { "/workingHour/{username}", "/workingHour/{username}/" })
	public WorkingHourDto getWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			String dayOfWeek) {
		return convertToDto(service.getWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek)));
	}

	@GetMapping(value = { "/allWorkingHours", "/allWorkingHours/" })
	public List<WorkingHourDto> getAllWorkingHours() {
		return service.getAllWorkingHours().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@PutMapping(value = { "/updateWorkingHour/{username}", "/updateWorkingHour/{username}/" })
	public WorkingHourDto updateWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			@RequestParam String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		return convertToDto(service.updateWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek),
				Time.valueOf(startTime), Time.valueOf(endTime)));
	}

	@DeleteMapping(value = { "/deleteWorkingHour/{username}", "/deleteWorkingHour/{username}/" })
	public void deleteWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			@RequestParam String dayOfWeek) {
		service.deleteWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek));
	}

	private WorkingHourDto convertToDto(WorkingHour workingHour) {
		return new WorkingHourDto(workingHour.getDayOfWeek(), workingHour.getStartTime(), workingHour.getEndTime());
	}

	private ScheduleDto convertToDto(Schedule schedule) {
		return new ScheduleDto(schedule.getScheduleID());
	}

}
