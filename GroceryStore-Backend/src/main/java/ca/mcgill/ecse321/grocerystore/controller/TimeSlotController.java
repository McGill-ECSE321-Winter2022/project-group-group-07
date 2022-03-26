package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.TimeSlotDto;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/timeslot")
public class TimeSlotController {

	@Autowired
	private GroceryStoreService service;

	@GetMapping(value = { "/timeSlots", "/timeSlots/" })
	public List<TimeSlotDto> getAllTimeSlots() {

		List<TimeSlotDto> timeSlots = new ArrayList<TimeSlotDto>();
		for (TimeSlot t : service.getAllTimeSlots()) {
			timeSlots.add(convertToDto(t));
		}

		return timeSlots;
	}

	@GetMapping(value = { "/holidays", "/holidays/" })
	public List<TimeSlotDto> getAllHolidays() {

		List<TimeSlotDto> timeSlots = new ArrayList<TimeSlotDto>();
		for (TimeSlot t : service.getAllHolidays()) {
			timeSlots.add(convertToDto(t));
		}

		return timeSlots;
	}

	@PostMapping(value = { "/createTimeSlot", "/createTimeSlot/" })
	public TimeSlotDto createTimeSlot(@RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {

		return convertToDto(service.createTimeSlot(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime)));
	}


	private TimeSlotDto convertToDto(TimeSlot t) {
		return new TimeSlotDto(t.getStartDate(), t.getEndDate(), t.getStartTime(), t.getEndTime());
	}

}
