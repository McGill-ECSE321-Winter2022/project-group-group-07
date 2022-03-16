package ca.mcgill.ecse321.grocerystore.controller;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.*;
import ca.mcgill.ecse321.grocerystore.model.*;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
public class GroceryStoreRestController {

	@Autowired
	private GroceryStoreService service;

	@PostMapping(value = { "/customerAccounts/{username}", "/customerAccounts/{username}/" })
	public AccountDto createCustomerAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password) {

		Customer role = service.createCustomerRole();
		Account account = service.createAccount(username, password, name, 0, role);

		return convertToDto(account, role);
	}

	@PostMapping(value = { "/employeeAccounts/{username}", "/employeeAccounts/{username}/" })
	public AccountDto createEmployeeAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password, @RequestParam String role) {

		Employee employeeRole = null;
		long millis = System.currentTimeMillis();

		if (role.equals("Cashier")) {
			employeeRole = new Cashier();
		} else if (role.equals("Clerk")) {
			employeeRole = new Clerk();
		} else if (role.equals("DeliveryPerson")) {
			employeeRole = new DeliveryPerson();
		} else if (role.equals("Owner")) {
			employeeRole = new Owner();
		} else {
			throw new IllegalArgumentException("No such employee role exists");
		}

		employeeRole.setEmploymentDate(new java.sql.Date(millis));
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

	@PostMapping(value = { "/address/{Account}", "/address/{Account}/" })
	public AddressDto createAddress(@PathVariable("Account") String username, @RequestParam Integer buildingNo,
			@RequestParam String street, @RequestParam String town) {

		Account account = service.getAccount(username);
		Address address = service.createAddress(buildingNo, street, town, account);

		return convertToDto(address, convertToDto(account, account.getAccountRole()));
	}

	@GetMapping(value = {"/address", "/address/"})
	public List<AddressDto> getAllAddresses(){

		List<AddressDto> addresses = new ArrayList<AddressDto>();

		for (Address a : service.getAllAddresses()) {
			if (a != null) {
				addresses.add(convertToDto(a, convertToDto(a.getAccount(),a.getAccount().getAccountRole())));
			}
		}
		return addresses;
	}

	@GetMapping(value = { "/store", "/store/" })
	public StoreDto getStoreInfo(){
		return convertToDto(service.getStore());
	}

	@PostMapping(value = { "/businessHours", "/businessHours/" })
	public BusinessHourDto createBusinessHours( @RequestParam(name = "id") Integer id,
								@RequestParam(name = "dayOfWeek") GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek,
								@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time startTime,
								@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") Time endTime){
		BusinessHour b = service.createBusinessHour(id, dayOfWeek, startTime, endTime);
		return convertToDto(b);
	}

	@GetMapping(value = { "/businessHours", "/businessHours/" })
	public List<BusinessHourDto> getBusinessHours(){
		return service.getAllBusinessHours().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping(value = { "/businessHours/{dayOfWeek}", "/businessHours/{dayOfWeek}/" })
	public List<BusinessHourDto> getBusinessHoursByDay(@PathVariable("dayOfWeek") String day){
		List<BusinessHourDto> businessHours = new ArrayList<>();
		for (BusinessHour b : service.getAllBusinessHours()) {
			if (b.getDayOfWeek().toString().equals(day)){
				businessHours.add(convertToDto(b));
			}
		}
		return businessHours;
	}

	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

	private AddressDto convertToDto(Address address, AccountDto account) {

		return new AddressDto(address.getBuildingNo(), address.getStreet(), address.getTown(), account);
	}
	
	@PostMapping(value = {"/perishable/{perishableItemID}","/perishable/{perishableItemID}/"}) 
	public PerishableItemDto createPerishableItem(@PathVariable("perishableItemID") Integer id, @RequestParam String productName, 
			@RequestParam Float price, @RequestParam Boolean availableOnline, @RequestParam Integer numInStock, 
			@RequestParam Integer pointPerItem) throws IllegalArgumentException {
		
		PerishableItem perishableItem = service.createPerishableItem(id, productName, price, availableOnline, numInStock, pointPerItem);
		
		return convertToDto(perishableItem);
	}


	private PerishableItemDto convertToDto(PerishableItem perishableItem) {
		return new PerishableItemDto(perishableItem.getItemID(), perishableItem.getProductName(), perishableItem.getPrice(),
				perishableItem.getAvailableOnline(), perishableItem.getNumInStock(), perishableItem.getPointPerItem());
	}
	
	/*
	@PostMapping(value = {"/perishable/{perishableItemID}","/perishable/{perishableItemID}/"})
	public PerishableItemDto createPerishableItemWithDate(@PathVariable("perishableItemID") Integer id, @RequestParam String productName, 
			@RequestParam Float price, @RequestParam Boolean availableOnline, @RequestParam Integer numInStock, 
			@RequestParam Integer pointPerItem, @RequestParam Date date) {
		
		PerishableItem perishableItem = service.createPerishableItem(id, productName, price, availableOnline, numInStock, pointPerItem);
		
		return convertToDto(perishableItem, date);
	}
	
	private PerishableItemDto convertToDto(PerishableItem perishableItem, Date date) {
		
		return new PerishableItemDto(perishableItem.getItemID(), perishableItem.getProductName(), perishableItem.getPrice(),
				perishableItem.getAvailableOnline(), perishableItem.getNumInStock(), perishableItem.getPointPerItem(), date);
	}*/

	@PostMapping(value = {"/nonperishable/{NonPerishableItemID}","/nonperishable/{NonPerishableItemID}/"})
	public NonPerishableItemDto createNonPerishableItem(@PathVariable("NonPerishableItemID") Integer id, @RequestParam String productName, 
			@RequestParam Float price, @RequestParam Boolean availableOnline, @RequestParam Integer numInStock, 
			@RequestParam Integer pointPerItem) throws IllegalArgumentException {
		
		NonPerishableItem nonPerishableItem = service.createNonPerishableItem(id, productName, price, availableOnline, numInStock, pointPerItem);
		
		return convertToDto(nonPerishableItem);
	}
	
	private NonPerishableItemDto convertToDto(NonPerishableItem nonPerishableItem) {
		return new NonPerishableItemDto(nonPerishableItem.getItemID(), nonPerishableItem.getProductName(), nonPerishableItem.getPrice(),
				nonPerishableItem.getAvailableOnline(), nonPerishableItem.getNumInStock(), nonPerishableItem.getPointPerItem());
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
	
	@GetMapping(value = {"/perishableitems", "/perishableitems/"})
	public List<ItemDto> getAllPerishableItems(){
		
		List<ItemDto> items = new ArrayList<ItemDto>();
		
		for (PerishableItem i : service.getAllPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;
	}
	
	@GetMapping(value = {"/nonperishableitems", "/nonperishableitems/"})
	public List<ItemDto> getAllNonPerishableItems(){
		
		List<ItemDto> items = new ArrayList<ItemDto>();
		
		for (NonPerishableItem i : service.getAllNonPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;
	}
	
	@GetMapping(value = {"/items/id:{id}", "/items/id:{id}/"})
	public ItemDto getItemsByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Integer ID = Integer.parseInt(id);
		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		ItemDto itemsDto = null;
		
		if (pitems != null) {
			itemsDto=convertToDto(pitems);
		}
		else if (npitems != null) {
			itemsDto=convertToDto(npitems);
		}
		
		return itemsDto;
	}
	
	@GetMapping(value = {"/items/name:{name}", "/items/name:{name}/"})
	public List<ItemDto> getItemsByName(@PathVariable("name") String name) throws IllegalArgumentException {
		List<PerishableItem> pitems = service.getPerishableItemsByProductName(name);
		List<NonPerishableItem> npitems = service.getNonPerishableItemsByProductName(name);
		List<ItemDto> items = new ArrayList<ItemDto>();
		
		if (pitems != null) {
			for (PerishableItem p : pitems) {
				items.add(convertToDto(p));
			
			}
		}
		if (npitems != null) {
			for (NonPerishableItem p : npitems) {
				items.add(convertToDto(p));
			
			}
		}
		
		return items;
	}

	@DeleteMapping(value = {"/deleteItems/{id}", "/deleteItems/{id}/"})
	public void deleteItemsByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Integer ID = Integer.parseInt(id);
		
		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		
		if (pitems != null) {
			service.deletePerishableItems(pitems);
		}
		if (npitems != null) {
			service.deleteNonPerishableItems(npitems);
		}
	}
	
	@PutMapping(value = {"/items/{id}","/items/{id}/"} )   
	 public ItemDto updateItem(@PathVariable String id, @RequestParam String productName, 
				@RequestParam Float price, @RequestParam Boolean availableOnline, @RequestParam Integer numInStock, 
				@RequestParam Integer pointPerItem)   {
		Integer ID = Integer.parseInt(id);
		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item!");
		} 
		else if (pitems != null) {
			PerishableItem perishableItemToUpdate = service.getPerishableItemsByID(ID);
			perishableItemToUpdate = service.updatePerishableItem(perishableItemToUpdate,ID,productName,price, availableOnline, numInStock,pointPerItem);
			PerishableItemDto updatedPerishableItem = convertToDto(perishableItemToUpdate);
			return updatedPerishableItem;
		}
		else {
			NonPerishableItem nonPerishableItemToUpdate = service.getNonPerishableItemsByID(ID);
			nonPerishableItemToUpdate = service.updateNonPerishableItem(nonPerishableItemToUpdate,ID,productName,price, availableOnline, numInStock,pointPerItem);
			NonPerishableItemDto nonUpdatedPerishableItem = convertToDto(nonPerishableItemToUpdate);
			return nonUpdatedPerishableItem;
		}
		
	}
	
	private StoreDto convertToDto(Store store){
		return new StoreDto(store.getName(), store.getAddress(), store.getPhoneNumber(), store.getEmail(), store.getEmployeeDiscountRate(), store.getPointToCashRatio());
	}

	private BusinessHourDto convertToDto(BusinessHour businessHour){
		return new BusinessHourDto(businessHour.getDayOfWeek().toString(), businessHour.getStartTime(), businessHour.getEndTime());
	}
}
