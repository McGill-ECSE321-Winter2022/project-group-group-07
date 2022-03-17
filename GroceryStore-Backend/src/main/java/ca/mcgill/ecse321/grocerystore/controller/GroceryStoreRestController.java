package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.*;
import ca.mcgill.ecse321.grocerystore.model.*;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
public class GroceryStoreRestController {

	@Autowired
	private GroceryStoreService service;

	// Account GET, POST, PUT and DELETE

	@PostMapping(value = { "/customerAccount/{username}", "/customerAccount/{username}/" })
	public AccountDto createCustomerAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password) {

		Customer role = service.createCustomerRole();
		Account account = service.createAccount(username, password, name, 0, role);

		return convertToDto(account, role);
	}

	@PostMapping(value = { "/employeeAccount/{username}", "/employeeAccount/{username}/" })
	public AccountDto createEmployeeAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password, @RequestParam String role) {

		Employee employeeRole = null;

		if (role.contains("Cashier")) {
			employeeRole = service.createCashierRole();
		} else if (role.contains("Clerk")) {
			employeeRole = service.createClerkRole();
		} else if (role.contains("DeliveryPerson")) {
			employeeRole = service.createDeliveryPersonRole();
		} else if (role.contains("Owner")) {
			employeeRole = service.createOwnerRole();
		} else {
			throw new IllegalArgumentException("No such employee role exists");
		}

		Account account = service.createAccount(username, password, name, 0, employeeRole);

		return convertToDto(account, employeeRole);
	}

	@GetMapping(value = { "/accounts", "/accounts/" })
	public List<AccountDto> getAllAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return accounts;
	}

	@GetMapping(value = { "/customerAccounts", "/customerAccounts/" })
	public List<AccountDto> getAllCustomerAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null && a.getAccountRole() instanceof Customer) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return accounts;
	}

	@GetMapping(value = { "/employeeAccounts", "/employeeAccounts/" })
	public List<AccountDto> getAllEmployeeAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null && a.getAccountRole() instanceof Employee) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return accounts;
	}

	@DeleteMapping(value = { "/deleteAccount/{username}", "/deleteAccount/{username}/" })
	public void deleteAccount(@PathVariable("username") String username, @RequestParam String password) {
		service.deleteAccount(username, password);
	}

	@PutMapping(value = { "/updatePassword/{username}", "/updatePassword/{username}/" })
	public AccountDto updatePassword(@PathVariable("username") String username, @RequestParam String oldPassword,
			@RequestParam String newPassword) {

		Account account = service.updatePassword(username, oldPassword, newPassword);

		return convertToDto(account, account.getAccountRole());
	}

	@PutMapping(value = { "/updateName/{username}", "/updateName/{username}/" })
	public AccountDto updateName(@PathVariable("username") String username, @RequestParam String newName) {

		Account account = service.updateName(username, newName);

		return convertToDto(account, account.getAccountRole());
	}

	// Address GET, POST, PUT and DELETE

	@PostMapping(value = { "/address/{username}", "/address/{username}/" })
	public AddressDto createAddress(@PathVariable("username") String username, @RequestParam Integer buildingNo,
			@RequestParam String street, @RequestParam String town) {

		Account account = service.getAccount(username);
		Address address = service.createAddress(buildingNo, street, town, account);

		return convertToDto(address, convertToDto(account, account.getAccountRole()));
	}

	@GetMapping(value = { "/address", "/address/" })
	public List<AddressDto> getAllAddresses() {

		List<AddressDto> addresses = new ArrayList<AddressDto>();

		for (Address a : service.getAllAddresses()) {
			if (a != null) {
				addresses.add(convertToDto(a, convertToDto(a.getAccount(), a.getAccount().getAccountRole())));
			}
		}
		return addresses;
	}

	@GetMapping(value = { "/address/{username}", "/address/{username}/" })
	public AddressDto getAddressByAccount(@PathVariable("username") String username) {

		Account account = service.getAccount(username);
		return convertToDto(service.getAddressByAccount(account), convertToDto(account, account.getAccountRole()));

	}

	@PutMapping(value = { "/updateAddress/{username}", "/updateAddress/{username}/" })
	public AddressDto updateAddress(@PathVariable("username") String username, @RequestParam Integer buildingNo,
			@RequestParam String street, @RequestParam String town) {

		Address address = service.updateAddress(username, buildingNo, street, town);
		return convertToDto(address, convertToDto(address.getAccount(), address.getAccount().getAccountRole()));
	}

	// Schedule & WorkingHour GET, POST, PUT and DELETE

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
	
	@PutMapping(value = {"/updateSchedule", "/updateSchedule/"})
	public ScheduleDto addWorkingHourToScheduleOfEmployee(@RequestParam String username,@RequestParam String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		return convertToDto(service.addWorkingHourToScheduleOfEmployee(username,DayOfWeek.valueOf(dayOfWeek),Time.valueOf(startTime),Time.valueOf(endTime))); 
	}
	

	@DeleteMapping(value = { "/deleteSchedule/{username}", "/deleteSchedule/{username}" })
	public void deleteScheduleByEmployee(@PathVariable("username") String username) {
		service.deleteScheduleByEmployee(username);
	}
	
	@GetMapping(value = { "/workingHour/{username}", "/workingHour/{username}/" })
	public WorkingHourDto getWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			String dayOfWeek) {
		return convertToDto(service.getWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek)));
	}

	@GetMapping(value = { "/allWorkingHours", "/allWorkingHours/" })
	public List<WorkingHourDto> getAllWorkingHours() {
		return service.getAllWorkingHours().stream().map(this::convertToDto).collect(Collectors.toList());
	}

//	@PostMapping(value = { "/workingHour", "/workingHour/" })
//	public WorkingHourDto createWorkingHour(@RequestParam("dayOfWeek") String dayOfWeek,
//			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
//			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime)
//			throws IllegalArgumentException {
//		WorkingHour workingHour = service.createWorkingHour(DayOfWeek.valueOf(dayOfWeek), Time.valueOf(startTime),
//				Time.valueOf(endTime));
//		return convertToDto(workingHour);
//	}

	@PutMapping(value = { "/updateWorkingHour/{username}", "/updateWorkingHour/{username}/" })
	public WorkingHourDto updateWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			@RequestParam String dayOfWeek,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		return convertToDto(service.updateWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.valueOf(dayOfWeek),
				Time.valueOf(startTime), Time.valueOf(endTime)));
	}
	
	@DeleteMapping(value = {"/deleteWorkingHour/{username}" , "/deleteWorkingHour/{username}/"})
	public void deleteWorkingHourByEmployeeAndDayOfWeek(@PathVariable("username") String username,
			@RequestParam String dayOfWeek) {
		service.deleteWorkingHourByEmployeeAndDayOfWeek(username,DayOfWeek.valueOf(dayOfWeek));
	}


	// Store GET, POST, PUT and DELETE
	
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

	// BusinessHours GET, POST, PUT and DELETE

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
	    BusinessHour bh = service.updateBusinessHourByDayOfWeek(DayOfWeek.valueOf(dayOfWeek), Time.valueOf(startTime),
			Time.valueOf(endTime));
		  return convertToDto(bh);
    	}

	@DeleteMapping(value = { "/businessHours/{dayOfWeek}", "/businessHours/{dayOfWeek}" })
	public void deleteBusinessHourByDay(@PathVariable("dayOfWeek") String dayOfWeek) {
		service.deleteBusinessHourByDay(DayOfWeek.valueOf(dayOfWeek));
	}
	
	// Items GET, POST, PUT and DELETE

	@PostMapping(value = { "/perishable/", "/perishable" })
	public PerishableItemDto createPerishableItem(@RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock, @RequestParam Integer pointPerItem)
			throws IllegalArgumentException {

		PerishableItem perishableItem = service.createPerishableItem(productName, price, availableOnline, numInStock,
				pointPerItem);

		return convertToDto(perishableItem);
	}

	@PostMapping(value = { "/nonperishable", "/nonperishable/" })
	public NonPerishableItemDto createNonPerishableItem(@RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock, @RequestParam Integer pointPerItem)
			throws IllegalArgumentException {

		NonPerishableItem nonPerishableItem = service.createNonPerishableItem(productName, price, availableOnline,
				numInStock, pointPerItem);

		return convertToDto(nonPerishableItem);
	}

	@GetMapping(value = {"/items", "/items/"})
	public List<ItemDto> getAllItems(){
		List<ItemDto> items = new ArrayList<ItemDto>();

		for (PerishableItem i : service.getAllPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		for (NonPerishableItem i : service.getAllNonPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;

	}

	@GetMapping(value = { "/perishableitems", "/perishableitems/" })
	public List<ItemDto> getAllPerishableItems() {
		List<ItemDto> items = new ArrayList<ItemDto>();

		for (PerishableItem i : service.getAllPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;
	}

	@GetMapping(value = { "/nonperishableitems", "/nonperishableitems/" })
	public List<ItemDto> getAllNonPerishableItems() {

		List<ItemDto> items = new ArrayList<ItemDto>();
		for (NonPerishableItem i : service.getAllNonPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;
	}

	@GetMapping(value = { "/items/id:{id}", "/items/id:{id}/" })
	public ItemDto getItemsByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Long ID = Long.parseLong(id);
		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		ItemDto itemsDto = null;
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item to get!");
		} else if (pitems != null) {
			itemsDto = convertToDto(pitems);
		} else {
			itemsDto = convertToDto(npitems);
		}

		return itemsDto;
	}

	@GetMapping(value = { "/items/name:{name}", "/items/name:{name}/" })
	public List<ItemDto> getItemsByName(@PathVariable("name") String name) throws IllegalArgumentException {
		List<PerishableItem> pitems = service.getPerishableItemsByProductName(name);
		List<NonPerishableItem> npitems = service.getNonPerishableItemsByProductName(name);
		List<ItemDto> items = new ArrayList<ItemDto>();
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item to get!");
		} else if (pitems != null) {
			for (PerishableItem p : pitems) {
				items.add(convertToDto(p));

			}
		} else {
			for (NonPerishableItem p : npitems) {
				items.add(convertToDto(p));

			}
		}

		return items;
	}

	@DeleteMapping(value = { "/deleteItems/{id}", "/deleteItems/{id}/" })
	public ItemDto deleteItemsByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Long ID = Long.parseLong(id);

		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item to delete!");
		} else if (pitems != null) {
			PerishableItem deletedPitems = service.deletePerishableItems(pitems);
			 return convertToDto(deletedPitems);
		} else {
			NonPerishableItem deletedNPitems = service.deleteNonPerishableItems(npitems);
			return convertToDto(deletedNPitems);
		}
	}

	@PutMapping(value = { "/items/{id}", "/items/{id}/" })
	public ItemDto updateItem(@PathVariable String id, @RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock,
			@RequestParam Integer pointPerItem) {
		Long ID = Long.parseLong(id);
		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item!");
		} else if (pitems != null) {
			PerishableItem perishableItemToUpdate = service.getPerishableItemsByID(ID);
			perishableItemToUpdate = service.updatePerishableItem(perishableItemToUpdate, productName, price,
					availableOnline, numInStock, pointPerItem);
			PerishableItemDto updatedPerishableItem = convertToDto(perishableItemToUpdate);
			return updatedPerishableItem;
		} else {
			NonPerishableItem nonPerishableItemToUpdate = service.getNonPerishableItemsByID(ID);
			nonPerishableItemToUpdate = service.updateNonPerishableItem(nonPerishableItemToUpdate, productName, price,
					availableOnline, numInStock, pointPerItem);
			NonPerishableItemDto nonUpdatedPerishableItem = convertToDto(nonPerishableItemToUpdate);
			return nonUpdatedPerishableItem;
		}

	}

	// Report GET And POST

	@GetMapping(value = { "/reports", "/reports/" })
	public List<ReportDto> getAllReports() {
		List<ReportDto> reports = new ArrayList<ReportDto>();
		for (Report report : service.getAllReports()) {
			reports.add(convertToDto(report));
		}
		return reports;
	}

	@GetMapping(value = { "/report/{id}", "/report/{id}/" })
	public ReportDto getReportByID(@PathVariable("id") Long id) {
		Report report = service.getReportById(id);
		return convertToDto(report);
	}

	@PostMapping(value = { "/report", "/report/" })
	public ReportDto createReport(@RequestParam Date startDate, @RequestParam Date endDate)
			throws IllegalArgumentException {
		Report report = service.createReport(startDate, endDate);
		return convertToDto(report);
	}
	// Orders GET, POST and PUT

	@GetMapping(value = {"/order/{username}","/order/{username}/"})
	public List<OrderDto> getOrdersByAccount(@PathVariable("unsername") String username){
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for(Order order : service.getDeliveryOrdersByAccount(service.getAccount(username))) {
			orders.add(convertToDto(order));
		}
		for(Order order : service.getInStoreOrdersByAccount(service.getAccount(username))) {
			orders.add(convertToDto(order));
		}
		for(Order order : service.getPickUpOrdersByAccount(service.getAccount(username))) {
			orders.add(convertToDto(order));
		}
		return orders;
	}
	@GetMapping(value = { "/order/{id}", "/order/{id}/" })
	public OrderDto getOrderByID(@PathVariable("id") Long id){
		Order order = service.getOrderById(id);
		return convertToDto(order);
	}
	@PostMapping(value = {"/createInPersonOrder","/createInPersonOrder/"})
	public InStoreOrderDto createInStoreOrder(@RequestParam Date date,@RequestParam Time purchaseTime,@RequestParam Set<Item> items) {
		return (InStoreOrderDto) convertToDto(service.createInStoreOrder(date, purchaseTime, items));
	}
	@PostMapping(value = {"/createInPersonOrder/{username}","/createInPersonOrder/{username}/"})
	public InStoreOrderDto createInStoreOrder(@RequestParam Date date,@RequestParam Time purchaseTime,@RequestParam Set<Item> items,@PathVariable("username") String username) {
		return (InStoreOrderDto) convertToDto(service.createInStoreOrder(date, purchaseTime, items, service.getAccount(username)));
	}
	@PostMapping(value = {"/createDeliveryOrder/{username}","/createDeliveryOrder/{username}/"})
	public DeliveryOrderDto createDeliveryOrder(@PathVariable("username") String username,@RequestParam Date date,@RequestParam Time purchaseTime,@RequestParam Set<Item> items,@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam Time startTime,@RequestParam Time endTime) {
		return (DeliveryOrderDto) convertToDto(service.createDeliveryOrder(date, purchaseTime, items,service.createTimeSlot(startDate, endDate, startTime, endTime), service.getAccount(username)));
	}
	@PostMapping(value = {"/createPickUpOrder/{username}","/createPickUpOrder/{username}/"})
	public PickUpOrderDto createPickUpOrder(@RequestParam Date date,@RequestParam Time purchaseTime,@RequestParam Set<Item> items,@RequestParam Date startDate,@RequestParam Date endDate,@RequestParam Time startTime,@RequestParam Time endTime,@PathVariable("username") String username) {
		return (PickUpOrderDto) convertToDto(service.createPickUpOrder(date, purchaseTime, items,service.createTimeSlot(startDate, endDate, startTime, endTime), service.getAccount(username)));
	}
	@PutMapping(value={"/deliveryUpdate/{id}","/deliveryUpdate/{id}/"})
	public DeliveryOrderDto updateDeliveryStatus(@PathVariable("id") long id,@RequestParam String status) {
		return (DeliveryOrderDto) convertToDto(service.updateDeliveryOrderStatus((DeliveryOrder) service.getOrderById(id),status));
	}
	@PutMapping(value={"/PickUpUpdate/{id}","/PickUpUpdate/{id}/"})
	public PickUpOrderDto updatePickUpStatus(@PathVariable("id") long id,@RequestParam String status) {
		return (PickUpOrderDto) convertToDto(service.updatePickUpOrderStatus((PickUpOrder) service.getOrderById(id),status));
	}
	@PostMapping(value={"/checkout/{username}","/checkout/{username}/"})
	public OrderDto checkout(@PathVariable("username") String username) {
		return convertToDto(service.checkout(service.getCartByAccount(username)));
	}
	
	


	// Terminal Get, Post and Delete

	@GetMapping(value = { "/terminals", "/terminals/" })
	public List<TerminalDto> getAllTerminals() {

		List<TerminalDto> terminals = new ArrayList<TerminalDto>();
		for (Terminal t : service.getAllTerminals()) {
			terminals.add(convertToDto(t));
		}

		return terminals;

	}
	
	@PostMapping(value = { "/terminal", "/terminal/" })
	public TerminalDto createTerminal() {

		return convertToDto(service.createTerminal());
	}

	@DeleteMapping(value = { "/deleteTerminal/{id}", "/deleteTerminal/{id}/" })
	public void deleteTerminalbyID(@PathVariable("id") Long terminalID) {

		service.deleteTerminal(terminalID);
	}

	// TimerSlot Get and POST

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

	// Cart GET, POST and PUT

	@GetMapping(value = { "/cart/{username}", "/cart/{username}/" })
	public CartDto getCartByAccount(@PathVariable("username") String username) {
		return convertToDto(service.getCartByAccount(username));
	}

	@PostMapping(value = { "/cart/{username}", "/cart/{username}/" })
	public CartDto createCart(@PathVariable("username") String username) {
		return convertToDto(service.createCart(username));
	}

	@PutMapping(value = { "/addToCart/{id}", "/addToCart/{id}/" })
	public CartDto addToCart(@PathVariable("id") Long id, String username) {

		return convertToDto(service.addToCart(id, username));
	}

	@PutMapping(value = { "/pickTimeSlot/{username}", "/pickTimeSlot/{username}/" })
	public CartDto addTimeSlotToCart(@PathVariable("username") String username, @RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		return convertToDto(service.addTimeSlotToCart(username, service.createTimeSlot(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime))));
	}
	@PutMapping(value = {"/chooseOrderType/{username}","/chooseOrderType/{username}/"})
	public CartDto chooseOrderTypeForCart(@PathVariable("username") String username, @RequestParam String orderType) {
		return convertToDto(service.chooseOrderTypeForCart(username,OrderType.valueOf(orderType)));
	}

	// -------------------------------------------------------------------------------------------------------------------------------//
	// convertToDto Methods

	private CartDto convertToDto(Cart cart) {
		List<ItemDto> items = new ArrayList<ItemDto>();
		AccountDto account = convertToDto(cart.getaccount(), cart.getaccount().getAccountRole());
		TimeSlotDto timeSlot = convertToDto(cart.getTimeSlot());
		for (Item i : cart.getItems()) {
			if (i instanceof PerishableItem) {
				items.add(convertToDto((PerishableItem) i));
			}
			if (i instanceof NonPerishableItem) {
				items.add(convertToDto((NonPerishableItem) i));
			}
		}
		return new CartDto(cart.getOrderType(), cart.getTotalValue(), cart.getNumOfItems(), items, timeSlot, account);
	}

	private TimeSlotDto convertToDto(TimeSlot t) {
		return new TimeSlotDto(t.getStartDate(), t.getEndDate(), t.getStartTime(), t.getEndTime());
	}

	private StoreDto convertToDto(Store store) {
		return new StoreDto(store.getName(), store.getAddress(), store.getPhoneNumber(), store.getEmail(),
				store.getEmployeeDiscountRate(), store.getPointToCashRatio());
	}

	private BusinessHourDto convertToDto(BusinessHour businessHour) {

		return new BusinessHourDto(businessHour.getDayOfWeek().toString(), businessHour.getStartTime(),
				businessHour.getEndTime());
	}

	private OrderDto convertToDto(Order order) {
		Long orderID = order.getOrderID();
		Float totalValue = order.getTotalValue();
		Date date = order.getDate();
		Time purchaseTime = order.getPurchaseTime();
		AccountDto account = convertToDto(order.getAccount(), order.getAccount().getAccountRole());
		TimeSlot timeSlot = new TimeSlot();
		if (order instanceof DeliveryOrder)
			timeSlot = ((DeliveryOrder) order).getTimeSlot();
		if (order instanceof PickUpOrder)
			timeSlot = ((PickUpOrder) order).getTimeSlot();
		List<ItemDto> items = new ArrayList<ItemDto>();
		if (order.getItems() != null && order.getItems().size() > 0) {
			for (Item i : order.getItems()) {
				if (i instanceof PerishableItem) {
					items.add(convertToDto((PerishableItem) i));
				} else {
					items.add(convertToDto((NonPerishableItem) i));
				}
			}
		}
		if (order instanceof PickUpOrder) {
			if (order.getItems() != null && order.getItems().size() > 0) {
				if (order.getAccount() == null) {
					return new PickUpOrderDto(orderID, totalValue, date, purchaseTime, items, timeSlot);
				} else {
					return new PickUpOrderDto(orderID, totalValue, date, purchaseTime, account, items, timeSlot);
				}
			} else {
				return new PickUpOrderDto(orderID, totalValue, date, purchaseTime, account, timeSlot);
			}
		} else if (order instanceof InStoreOrder) {
			if (order.getItems() != null && order.getItems().size() > 0) {
				if (order.getAccount() == null) {
					return new InStoreOrderDto(orderID, totalValue, date, purchaseTime, items);
				} else {
					return new InStoreOrderDto(orderID, totalValue, date, purchaseTime, account, items);
				}
			} else {
				return new InStoreOrderDto(orderID, totalValue, date, purchaseTime, account);
			}
		} else {
			if (order.getItems() != null && order.getItems().size() > 0) {
				if (order.getAccount() == null) {
					return new DeliveryOrderDto(orderID, totalValue, date, purchaseTime, items, timeSlot);
				} else {
					return new DeliveryOrderDto(orderID, totalValue, date, purchaseTime, account, items, timeSlot);
				}
			} else {
				return new DeliveryOrderDto(orderID, totalValue, date, purchaseTime, account, timeSlot);
			}
		}
	}

	private ReportDto convertToDto(Report report) {
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (Order ord : report.getOrders()) {
			if (ord instanceof PickUpOrder) {
				orders.add((PickUpOrderDto) convertToDto(ord));
			} else if (ord instanceof InStoreOrder) {
				orders.add((InStoreOrderDto) convertToDto(ord));
			} else {
				orders.add((DeliveryOrderDto) convertToDto(ord));
			}
		}
		return new ReportDto(report.getReportID(), report.getStartDate(), report.getEndDate(), report.getTotalValue(),
				orders);
	}

	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

	private AddressDto convertToDto(Address address, AccountDto account) {

		return new AddressDto(address.getBuildingNo(), address.getStreet(), address.getTown(), account);
	}

	private PerishableItemDto convertToDto(PerishableItem perishableItem) {
		return new PerishableItemDto(perishableItem.getItemID(), perishableItem.getProductName(),
				perishableItem.getPrice(), perishableItem.getAvailableOnline(), perishableItem.getNumInStock(),
				perishableItem.getPointPerItem());
	}

	private NonPerishableItemDto convertToDto(NonPerishableItem nonPerishableItem) {
		return new NonPerishableItemDto(nonPerishableItem.getItemID(), nonPerishableItem.getProductName(),
				nonPerishableItem.getPrice(), nonPerishableItem.getAvailableOnline(), nonPerishableItem.getNumInStock(),
				nonPerishableItem.getPointPerItem());
	}

	private WorkingHourDto convertToDto(WorkingHour workingHour) {
		return new WorkingHourDto(workingHour.getDayOfWeek(), workingHour.getStartTime(), workingHour.getEndTime());
	}

	private ScheduleDto convertToDto(Schedule schedule) {
		return new ScheduleDto(schedule.getScheduleID());
	}

	private TerminalDto convertToDto(Terminal terminal) {
		return new TerminalDto(terminal.getTerminalID());
	}

}
