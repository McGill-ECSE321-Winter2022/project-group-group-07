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

import ca.mcgill.ecse321.grocerystore.dto.BusinessHourDto;
import ca.mcgill.ecse321.grocerystore.dto.StoreDto;
import ca.mcgill.ecse321.grocerystore.model.BusinessHour;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem;
import ca.mcgill.ecse321.grocerystore.model.Store;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/store")
public class StoreController {

	@Autowired
	private GroceryStoreService service;

	// Store 

	@GetMapping(value = { "/store", "/store/" })
	public StoreDto getStoreInfo() {
		return convertToDto(service.getStore());
	}

	@PostMapping(value = { "/setStoreInfo", "/setStoreInfo/" })
	public StoreDto createStore(@RequestParam String name, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String email, @RequestParam Integer employeeDiscountRate,
			@RequestParam Float pointToCashRatio) {

		return convertToDto(
				service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio));
	}

	@PutMapping(value = { "/updateStore", "/updateStore/" })
	public StoreDto updateStore(@RequestParam String name, @RequestParam String address,
			@RequestParam String phoneNumber, @RequestParam String email, @RequestParam Integer employeeDiscountRate,
			@RequestParam Float pointToCashRatio) {
		return convertToDto(
				service.updateStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio));
	}
	
	@DeleteMapping(value = { "/deleteStore", "/deleteStore/" })
	public void deleteStore() {
		service.deleteStore();
	}

	// BusinessHours 

	@PostMapping(value = { "/businessHours", "/businessHours/" })
	public BusinessHourDto createBusinessHours(
			@RequestParam(name = "dayOfWeek") GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		BusinessHour b = service.createBusinessHour(dayOfWeek, Time.valueOf(startTime), Time.valueOf(endTime));
		return convertToDto(b);
	}

	@GetMapping(value = { "/businessHours", "/businessHours/" })
	public List<BusinessHourDto> getBusinessHours() {
		return service.getAllBusinessHours().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping(value = { "/businessHours/{dayOfWeek}", "/businessHours/{dayOfWeek}/" })
	public BusinessHourDto getBusinessHourByDay(@PathVariable("dayOfWeek") String dayOfWeek) {
		return convertToDto(service.getBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek)));
	}

	@PutMapping(value = { "/updateBusinessHour/{dayOfWeek}", "/updateBusinessHour/{dayOfWeek}/" })
	public BusinessHourDto updateBusinessHour(@PathVariable("dayOfWeek") String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		BusinessHour bh = service.updateBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek), Time.valueOf(startTime),
				Time.valueOf(endTime));
		return convertToDto(bh);
	}

	@DeleteMapping(value = { "/businessHours/{dayOfWeek}", "/businessHours/{dayOfWeek}" })
	public void deleteBusinessHourByDay(@PathVariable("dayOfWeek") String dayOfWeek) {
		service.deleteBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek));
	}

	private StoreDto convertToDto(Store store) {
		return new StoreDto(store.getName(), store.getAddress(), store.getPhoneNumber(), store.getEmail(),
				store.getEmployeeDiscountRate(), store.getPointToCashRatio());
	}

	private BusinessHourDto convertToDto(BusinessHour businessHour) {

		return new BusinessHourDto(businessHour.getDayOfWeek().toString(), businessHour.getStartTime(),
				businessHour.getEndTime());
	}


}
