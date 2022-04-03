package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getScheduleByEmployee(@PathVariable("username") String username) {
		Schedule sch = null;
		try {
			sch = service.getScheduleByEmployee(username);
		}
		catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		if(sch == null) {
			return new ResponseEntity<>("There is no employee account with that username.", HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(convertToDto(sch), HttpStatus.OK);
		}
	}

	@GetMapping(value = { "/allSchedules", "/allSchedules/" })
	public ResponseEntity<?> getAllSchedules() {
		return new ResponseEntity<>(service.getAllSchedules().stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping(value = { "/schedule", "/schedules/" })
	public ResponseEntity<?> createScheduleForEmployee(@RequestParam String username) throws IllegalArgumentException {
		Schedule schedule = null;
		try {
			schedule = service.createSchedule(username);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(schedule), HttpStatus.OK);
	}

	@PutMapping(value = { "/updateSchedule", "/updateSchedule/" })
	public ResponseEntity<?> addWorkingHourToScheduleOfEmployee(@RequestParam String username, @RequestParam String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		Schedule sch = null;
		try {
			sch = service.addWorkingHourToScheduleOfEmployee(username, DayOfWeek.valueOf(dayOfWeek),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (Exception e) {
			return new ResponseEntity<>("There is no employee with this username.", HttpStatus.BAD_REQUEST);
		}
		if(sch == null) {
			return new ResponseEntity<>("Invalid Input.", HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<>(convertToDto(sch), HttpStatus.OK);
		}
	}

	@DeleteMapping(value = { "/deleteSchedule/{username}", "/deleteSchedule/{username}" })
	public ResponseEntity<?> deleteScheduleByEmployee(@PathVariable("username") String username) {
		try {
			service.deleteScheduleByEmployee(username);
		} catch (Exception e) {
			return new ResponseEntity<>("There is no employee with this username.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// WorkingHours

	@GetMapping(value = { "/workingHour/{username}", "/workingHour/{username}/" })
	public ResponseEntity<?> getWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			String dayOfWeek) {
		WorkingHour wh = null;
		try {
			wh = service.getWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		if(wh == null) {
			return new ResponseEntity<>("Invalid input" , HttpStatus.BAD_REQUEST);
		}
		else{
			return new ResponseEntity<>(convertToDto(wh), HttpStatus.OK);
		}
	}

	@GetMapping(value = { "/allWorkingHours", "/allWorkingHours/" })
	public ResponseEntity<?> getAllWorkingHours() {
		return new ResponseEntity<>(service.getAllWorkingHours().stream().map(this::convertToDto).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PutMapping(value = { "/updateWorkingHour/{username}", "/updateWorkingHour/{username}/" })
	public ResponseEntity<?> updateWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			@RequestParam String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		WorkingHour wHour = null;
		try {
			wHour = service.updateWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek),
					Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(wHour), HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deleteWorkingHour/{username}", "/deleteWorkingHour/{username}/" })
	public ResponseEntity<?> deleteWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			@RequestParam String dayOfWeek) {
		try {
			service.deleteWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek));
		} catch (Exception e) {
			return new ResponseEntity<>("There is no employee with this username." , HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private WorkingHourDto convertToDto(WorkingHour workingHour) {
		return new WorkingHourDto(workingHour.getDayOfWeek(), workingHour.getStartTime(), workingHour.getEndTime());
	}

	private ScheduleDto convertToDto(Schedule schedule) {
		return new ScheduleDto(schedule.getScheduleID());
	}

}
