package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getAllTimeSlots() {

		List<TimeSlotDto> timeSlots = new ArrayList<TimeSlotDto>();
		for (TimeSlot t : service.getAllTimeSlots()) {
			timeSlots.add(convertToDto(t));
		}

		return new ResponseEntity<>(timeSlots, HttpStatus.OK);
	}

	@GetMapping(value = { "/holidays", "/holidays/" })
	public ResponseEntity<?> getAllHolidays() {

		List<TimeSlotDto> timeSlots = new ArrayList<TimeSlotDto>();
		for (TimeSlot t : service.getAllHolidays()) {
			timeSlots.add(convertToDto(t));
		}

		return new ResponseEntity<>(timeSlots, HttpStatus.OK);
	}

	@PostMapping(value = { "/createTimeSlot", "/createTimeSlot/" })
	public ResponseEntity<?> createTimeSlot(@RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		TimeSlot tm = null;
		try {
			tm = service.createTimeSlot(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage().split("!")[0] + "!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(tm), HttpStatus.OK);
	}


	private TimeSlotDto convertToDto(TimeSlot t) {
		return new TimeSlotDto(t.getStartDate(), t.getEndDate(), t.getStartTime(), t.getEndTime());
	}

}
