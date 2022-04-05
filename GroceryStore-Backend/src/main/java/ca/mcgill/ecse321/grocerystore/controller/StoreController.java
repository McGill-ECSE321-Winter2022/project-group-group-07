package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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

import ca.mcgill.ecse321.grocerystore.dto.BusinessHourDto;
import ca.mcgill.ecse321.grocerystore.dto.StoreDto;
import ca.mcgill.ecse321.grocerystore.model.BusinessHour;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.model.Store;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/store")
public class StoreController {

	@Autowired
	private GroceryStoreService service;

	// Store

	@GetMapping(value = { "/store", "/store/" })
	public ResponseEntity<?> getStoreInfo() {
		Store store = service.getStore();
		if (store == null) {
			return new ResponseEntity<>("There is no Store assigned to the System.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(convertToDto(store), HttpStatus.OK);
		}
	}

	@PostMapping(value = { "/setStoreInfo", "/setStoreInfo/" })
	public ResponseEntity<?> createStore(@RequestParam String name, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String email, @RequestParam String employeeDiscountRate,
			@RequestParam String pointToCashRatio) {
		Store store = null;
		try {
			store = service.createStore(name, address, phoneNumber, email, Integer.parseInt(employeeDiscountRate),
					Float.parseFloat(pointToCashRatio));
		} catch (Exception e) {
			System.out.print(e.getMessage().split("!")[0]);
			return new ResponseEntity<>(e.getMessage().split("!")[0] + "!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(store), HttpStatus.OK);
	}

	@PutMapping(value = { "/updateStore", "/updateStore/" })
	public ResponseEntity<?> updateStore(@RequestParam String name, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String email, @RequestParam Integer employeeDiscountRate,
			@RequestParam Float pointToCashRatio) {
		Store store = null;
		try {
			store = service.updateStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage().split("!")[0] + "!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(store), HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deleteStore", "/deleteStore/" })
	public ResponseEntity<?> deleteStore() {
		service.deleteStore();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// BusinessHours

	@PostMapping(value = { "/businessHours", "/businessHours/" })
	public ResponseEntity<?> createBusinessHours(@RequestParam(name = "dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		BusinessHour b = null;
		try {
			b = service.createBusinessHour(DayOfWeek.valueOf(dayOfWeek), Time.valueOf(startTime),
					Time.valueOf(endTime));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage().split("!")[0] + "!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(b), HttpStatus.OK);
	}

	@GetMapping(value = { "/businessHours", "/businessHours/" })
	public ResponseEntity<?> getBusinessHours() {
		return new ResponseEntity<>(
				service.getAllBusinessHours().stream().map(this::convertToDto).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@GetMapping(value = { "/businessHours/{dayOfWeek}", "/businessHours/{dayOfWeek}/" })
	public ResponseEntity<?> getBusinessHourByDay(@PathVariable("dayOfWeek") String dayOfWeek) {
		BusinessHour b = null;
		try {
			b = service.getBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(b), HttpStatus.OK);
	}

	@PutMapping(value = { "/updateBusinessHour/{dayOfWeek}", "/updateBusinessHour/{dayOfWeek}/" })
	public ResponseEntity<?> updateBusinessHour(@PathVariable("dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		BusinessHour bh = null;
		try {
			bh = service.updateBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek), Time.valueOf(startTime),
					Time.valueOf(endTime));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(bh), HttpStatus.OK);
	}

	@DeleteMapping(value = { "/businessHours/{dayOfWeek}", "/businessHours/{dayOfWeek}" })
	public ResponseEntity<?> deleteBusinessHourByDay(@PathVariable("dayOfWeek") String dayOfWeek) {
		BusinessHour bh = null;
		try {
			bh = service.deleteBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(convertToDto(bh), HttpStatus.OK);
	}

	private StoreDto convertToDto(Store store) {
		ArrayList<BusinessHourDto> bhdto= new ArrayList<>();
		for(BusinessHour bh :service.getAllBusinessHours()) {
			bhdto.add(convertToDto(bh));
		}
		return new StoreDto(store.getName(), store.getAddress(), store.getPhoneNumber(), store.getEmail(),
				store.getEmployeeDiscountRate(), store.getPointToCashRatio(),bhdto);
	}

	private BusinessHourDto convertToDto(BusinessHour businessHour) {
		if (businessHour != null) {
			return new BusinessHourDto(businessHour.getDayOfWeek().toString(), businessHour.getStartTime(),
					businessHour.getEndTime());
		} else {
			return null;
		}
	}

}
