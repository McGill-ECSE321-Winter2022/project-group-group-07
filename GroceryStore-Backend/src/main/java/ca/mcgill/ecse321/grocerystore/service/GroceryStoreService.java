package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.grocerystore.dao.AccountRepository;
import ca.mcgill.ecse321.grocerystore.dao.AddressRepository;
import ca.mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import ca.mcgill.ecse321.grocerystore.dao.CartRepository;
import ca.mcgill.ecse321.grocerystore.dao.CashierRepository;
import ca.mcgill.ecse321.grocerystore.dao.ClerkRepository;
import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;
import ca.mcgill.ecse321.grocerystore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.DeliveryPersonRepository;
import ca.mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import ca.mcgill.ecse321.grocerystore.dao.InStoreOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.NonPerishableItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.OwnerRepository;
import ca.mcgill.ecse321.grocerystore.dao.PerishableItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.PickUpOrderRepository;
import ca.mcgill.ecse321.grocerystore.dao.ReportRepository;
import ca.mcgill.ecse321.grocerystore.dao.ScheduleRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreRepository;
import ca.mcgill.ecse321.grocerystore.dao.TerminalRepository;
import ca.mcgill.ecse321.grocerystore.dao.TimeSlotRepository;
import ca.mcgill.ecse321.grocerystore.dao.WorkingHourRepository;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.Address;
import ca.mcgill.ecse321.grocerystore.model.BusinessHour;
import ca.mcgill.ecse321.grocerystore.model.Cart;
import ca.mcgill.ecse321.grocerystore.model.Cashier;
import ca.mcgill.ecse321.grocerystore.model.Clerk;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystore.model.DeliveryPerson;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.Order;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;
import ca.mcgill.ecse321.grocerystore.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PickUpOrder;
import ca.mcgill.ecse321.grocerystore.model.Report;
import ca.mcgill.ecse321.grocerystore.model.Schedule;
import ca.mcgill.ecse321.grocerystore.model.Store;
import ca.mcgill.ecse321.grocerystore.model.Terminal;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.model.WorkingHour;

@Service
public class GroceryStoreService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private BusinessHourRepository businessHourRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CashierRepository cashierRepository;
	@Autowired
	private ClerkRepository clerkRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;
	@Autowired
	private DeliveryPersonRepository deliveryPersonRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private InStoreOrderRepository inStoreOrderRepository;
	@Autowired
	private NonPerishableItemRepository nonPerishableItemRepository;
	@Autowired
	private OwnerRepository ownerRepository;
	@Autowired
	private PerishableItemRepository perishableItemRepository;
	@Autowired
	private PickUpOrderRepository pickUpOrderRepository;
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private TerminalRepository terminalRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private WorkingHourRepository workingHourRepository;

	@Transactional
	public Owner createOwnerRole(Date employmentDate) {

		Owner owner = new Owner();

		owner.setEmploymentDate(employmentDate);

		ownerRepository.save(owner);

		return owner;
	}

	@Transactional
	public Account getOwner() {

		for (Account a : accountRepository.findAll()) {
			if (a.getAccountRole() instanceof Owner) {
				return a;
			}
		}

		return null;
	}

	@Transactional
	public Clerk createClerkRole(Date employmentDate) {

		Clerk clerk = new Clerk();

		clerk.setEmploymentDate(employmentDate);

		clerkRepository.save(clerk);

		return clerk;
	}

	@Transactional
	public List<Account> getAllClerks() {

		List<Account> clerks = new ArrayList<Account>();

		for (Account a : accountRepository.findAll()) {
			if (a.getAccountRole() instanceof Clerk) {
				clerks.add(a);
			}
		}
		return clerks;
	}

	@Transactional
	public Cashier createCashierRole(Date employmentDate) {

		Cashier cashier = new Cashier();
		cashier.setEmploymentDate(employmentDate);

		cashierRepository.save(cashier);

		return cashier;
	}

	@Transactional
	public List<Account> getAllCashiers() {

		List<Account> cashiers = new ArrayList<Account>();

		for (Account a : accountRepository.findAll()) {
			if (a.getAccountRole() instanceof Cashier) {
				cashiers.add(a);
			}
		}
		return cashiers;
	}

	@Transactional
	public DeliveryPerson createDeliveryPersonRole(Date employmentDate) {

		DeliveryPerson deliveryPerson = new DeliveryPerson();

		deliveryPerson.setEmploymentDate(employmentDate);

		deliveryPersonRepository.save(deliveryPerson);

		return deliveryPerson;
	}

	@Transactional
	public List<Account> getAllDeliverPersons() {

		List<Account> deliveryPersons = new ArrayList<Account>();

		for (Account a : accountRepository.findAll()) {
			if (a.getAccountRole() instanceof DeliveryPerson) {
				deliveryPersons.add(a);
			}
		}
		return deliveryPersons;
	}

	@Transactional
	public Customer createCustomerRole() {

		Customer customer = new Customer();
		customerRepository.save(customer);

		return customer;
	}

	@Transactional
	public List<Account> getAllCustomers() {

		List<Account> customers = new ArrayList<Account>();

		for (Account a : accountRepository.findAll()) {
			if (a.getAccountRole() instanceof Customer) {
				customers.add(a);
			}
		}

		return customers;
	}

	@Transactional
	public Account createAccount(String username, String password, String name, Integer pointBalance,
			AccountRole accountRole) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Account name cannot be empty!");
		}
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setName(name);
		account.setPointBalance(pointBalance);
		account.setAccountRole(accountRole);

		accountRepository.save(account);

		return account;
	}

	@Transactional
	public Account getAccount(String username) {

		return accountRepository.findByUsername(username);
	}

	@Transactional
	public List<Account> getAllAccounts() {

		return toList(accountRepository.findAll());
	}

	@Transactional
	public Address createAddress(Integer buildingNo, String street, String town, Account account) {

		Address address = new Address();

		address.setBuildingNo(buildingNo);
		address.setStreet(street);
		address.setTown(town);
		address.setAccount(account);

		addressRepository.save(address);

		return address;
	}

	@Transactional
	public Address getAddressByAccount(Account account) {

		for (Address a : addressRepository.findAll()) {
			if (a.getAccount().equals(account)) {
				return a;
			}
		}

		return null;
	}

	@Transactional
	public List<Address> getAllAddresses() {

		return toList(addressRepository.findAll());
	}

	@Transactional
	public BusinessHour createBusinessHour(Integer id, DayOfWeek dayOfWeek, Time startTime, Time endTime) {
		String error = "";
		if (id == null){
			error = error + "Business hour id cannot be empty! ";
		}
		if (dayOfWeek == null){
			error = error + "Business hour day of the week cannot be empty! ";
		}
		if (startTime == null){
			error = error + "Business hour start time cannot be empty! ";
		}
		if (endTime == null){
			error = error + "Business hour end time cannot be empty! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Business hour end time cannot be before Business hour start time! ";
		}

		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		BusinessHour hour = new BusinessHour();

		hour.setDayOfWeek(dayOfWeek);
		hour.setStartTime(startTime);
		hour.setEndTime(endTime);

		businessHourRepository.save(hour);

		return hour;
	}

	@Transactional
	public List<BusinessHour> getAllBusinessHours() {

		return toList(businessHourRepository.findAll());
	}

	@Transactional
	public BusinessHour getBusinessHourByDay(DayOfWeek dayOfWeek) {
		if (dayOfWeek == null) {
			throw new IllegalArgumentException("Day of week cannot be empty!");
		}
		for (BusinessHour bh : businessHourRepository.findAll()) {
			if (bh.getDayOfWeek().equals(dayOfWeek)) {
				return bh;
			}
		}

		return null;
	}

	@Transactional
	public Cart createCart(Integer id, OrderType orderType, Float totalValue, Integer numOfItems, Set<Item> items,
			TimeSlot timeSlot, Account account) {

		Cart cart = new Cart();

		cart.setOrderType(orderType);
		cart.setTotalValue(totalValue);
		cart.setNumOfItems(numOfItems);
		cart.setItems(items);
		cart.setTimeSlot(timeSlot);
		cart.setAccount(account);

		cartRepository.save(cart);

		return cart;
	}

	@Transactional
	public Cart createCart(Integer id, OrderType orderType, Float totalValue, Integer numOfItems, Set<Item> items,
			TimeSlot timeSlot) {

		Cart cart = new Cart();

		cart.setOrderType(orderType);
		cart.setTotalValue(totalValue);
		cart.setNumOfItems(numOfItems);
		cart.setItems(items);
		cart.setTimeSlot(timeSlot);

		cartRepository.save(cart);

		return cart;
	}

	@Transactional
	public List<Cart> getAllCarts() {

		return toList(cartRepository.findAll());
	}

	@Transactional
	public Cart getCartByAccount(Account account) {

		return cartRepository.findByAccount(account);
	}

	@Transactional
	public DeliveryOrder createDeliveryOrder(Integer id, Float totalValue, Date date, Time purchaseTime,
			Set<Item> items, TimeSlot timeSlot, Account account) {

		DeliveryOrder deliveryOrder = new DeliveryOrder();

		deliveryOrder.setTotalValue(totalValue);
		deliveryOrder.setDate(date);
		deliveryOrder.setPurchaseTime(purchaseTime);
		deliveryOrder.setItems(items);
		deliveryOrder.setTimeSlot(timeSlot);
		deliveryOrder.setAccount(account);

		deliveryOrderRepository.save(deliveryOrder);

		return deliveryOrder;
	}

	@Transactional
	public List<DeliveryOrder> getDeliveryOrdersByAccount(Account account) {

		return deliveryOrderRepository.findByAccount(account);
	}

	@Transactional
	public PickUpOrder createPickUpOrder(Integer id, Float totalValue, Date date, Time purchaseTime, Set<Item> items,
			TimeSlot timeSlot, Account account) {

		PickUpOrder pickUpOrder = new PickUpOrder();

		pickUpOrder.setTotalValue(totalValue);
		pickUpOrder.setDate(date);
		pickUpOrder.setPurchaseTime(purchaseTime);
		pickUpOrder.setItems(items);
		pickUpOrder.setTimeSlot(timeSlot);
		pickUpOrder.setAccount(account);

		pickUpOrderRepository.save(pickUpOrder);

		return pickUpOrder;
	}

	@Transactional
	public List<PickUpOrder> getPickUpOrdersByAccount(Account account) {

		return pickUpOrderRepository.findByAccount(account);
	}

	@Transactional
	public InStoreOrder createInStoreOrder(Integer id, Float totalValue, Date date, Time purchaseTime, Set<Item> items,
			Account account) {

		InStoreOrder inStoreOrder = new InStoreOrder();

		inStoreOrder.setTotalValue(totalValue);
		inStoreOrder.setDate(date);
		inStoreOrder.setPurchaseTime(purchaseTime);
		inStoreOrder.setItems(items);
		inStoreOrder.setAccount(account);

		inStoreOrderRepository.save(inStoreOrder);

		return inStoreOrder;
	}

	@Transactional
	public List<InStoreOrder> getInStoreOrdersByAccount(Account account) {

		return inStoreOrderRepository.findByAccount(account);
	}

	@Transactional
	public PerishableItem createPerishableItem(String name, Float price, Boolean availableOnline, Integer numInStock,
			Integer pointPerItem) {

		PerishableItem perishableItem = new PerishableItem();

		ArrayList<String> errors = new ArrayList<String>();

		if (name == null || name.trim().length() == 0) {
			errors.add("Item name is empty!");
		}
		if (price == null) {
			errors.add("Price is empty!");
		}
		if (availableOnline == null) {
			errors.add("Please state whether this item is available online!");
		}
		if (numInStock == null) {
			errors.add("Please state the amount of stock!");
		}
		if (pointPerItem == null) {
			errors.add("Please state the amount of point given per item!");
		}
		String listErrors = String.join(", ", errors);
		if (errors.size() != 0)
			throw new IllegalArgumentException(listErrors);

		perishableItem.setProductName(name);
		perishableItem.setPrice(price);
		perishableItem.setAvailableOnline(availableOnline);
		perishableItem.setNumInStock(numInStock);
		perishableItem.setPointPerItem(pointPerItem);

		perishableItemRepository.save(perishableItem);

		return perishableItem;
	}

	@Transactional
	public List<PerishableItem> getAllPerishableItems() {

		return toList(perishableItemRepository.findAll());
	}

	@Transactional
	public PerishableItem getPerishableItemsByID(Long id) {

		return perishableItemRepository.findByItemID(id);
	}

	@Transactional
	public void deletePerishableItems(PerishableItem pitem) {
		perishableItemRepository.delete(pitem);

	}

	@Transactional
	public List<PerishableItem> getPerishableItemsByProductName(String name) {
		return perishableItemRepository.findByProductName(name);
	}

	@Transactional
	public PerishableItem updatePerishableItem(PerishableItem pitem, String productName, Float price,
			Boolean availableOnline, Integer numInStock, Integer pointPerItem) {

		pitem.setProductName(productName);
		pitem.setPrice(price);
		pitem.setAvailableOnline(availableOnline);
		pitem.setNumInStock(numInStock);
		pitem.setPointPerItem(pointPerItem);
		return pitem;
	}

	@Transactional
	public NonPerishableItem createNonPerishableItem(Integer id, String name, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {

		NonPerishableItem nonPerishableItem = new NonPerishableItem();
		ArrayList<String> errors = new ArrayList<String>();

		if (id == null) {
			errors.add("ItemID is empty!");
		}
		if (name == null || name.trim().length() == 0) {
			errors.add("Item name is empty!");
		}
		if (price == null) {
			errors.add("Price is empty!");
		}
		if (availableOnline == null) {
			errors.add("Please state whether this item is available online!");
		}
		if (numInStock == null) {
			errors.add("Please state the amount of stock!");
		}
		if (pointPerItem == null) {
			errors.add("Please state the amount of point given per item!");
		}
		String listErrors = String.join(", ", errors);
		if (errors.size() != 0)
			throw new IllegalArgumentException(listErrors);

		nonPerishableItem.setProductName(name);
		nonPerishableItem.setPrice(price);
		nonPerishableItem.setAvailableOnline(availableOnline);
		nonPerishableItem.setNumInStock(numInStock);
		nonPerishableItem.setPointPerItem(pointPerItem);

		nonPerishableItemRepository.save(nonPerishableItem);

		return nonPerishableItem;
	}

	@Transactional
	public List<NonPerishableItem> getAllNonPerishableItems() {

		return toList(nonPerishableItemRepository.findAll());
	}

	@Transactional
	public NonPerishableItem getNonPerishableItemsByID(Long id) {

		return nonPerishableItemRepository.findByItemID(id);
	}

	@Transactional
	public List<NonPerishableItem> getNonPerishableItemsByProductName(String name) {
		return nonPerishableItemRepository.findByProductName(name);
	}

	@Transactional
	public void deleteNonPerishableItems(NonPerishableItem npitem) {

		nonPerishableItemRepository.delete(npitem);
	}

	@Transactional

	public NonPerishableItem updateNonPerishableItem(NonPerishableItem npitem, String productName, Float price,
			Boolean availableOnline, Integer numInStock, Integer pointPerItem) {

		npitem.setProductName(productName);
		npitem.setPrice(price);
		npitem.setAvailableOnline(availableOnline);
		npitem.setNumInStock(numInStock);
		npitem.setPointPerItem(pointPerItem);
		return npitem;
	}

	@Transactional
	public Report createReport(Integer id, Date startDate, Date endDate, Float totalValue, Set<Order> orders) {

		Report report = new Report();

		report.setStartDate(startDate);
		report.setEndDate(endDate);
		report.setTotalValue(totalValue);
		report.setOrders(orders);

		reportRepository.save(report);

		return report;
	}

	@Transactional
	public List<Report> getAllReports() {

		return toList(reportRepository.findAll());
	}

	@Transactional
	public Schedule createSchedule(Integer scheduleID, String username, Set<WorkingHour> workingHour) {

		Schedule schedule = new Schedule();
		Employee employee = null;

		if (accountRepository.findByUsername(username).getAccountRole() instanceof Employee) {
			employee = (Employee) accountRepository.findByUsername(username).getAccountRole();
		} else {
			throw new IllegalArgumentException("No employee found");
		}
		schedule.setEmployee(employee);
		schedule.setWorkingHour(workingHour);

		scheduleRepository.save(schedule);

		return schedule;
	}

	@Transactional
	public List<Schedule> getAllSchedules() {

		return toList(scheduleRepository.findAll());
	}

	@Transactional
	public Schedule getScheduleByEmployee(Employee employee) {

		return scheduleRepository.findByEmployee(employee);
	}

	@Transactional
	public Store createStore(Integer storeID, String name, String address, String phoneNumber, String email,
			Integer employeeDiscountRate, Float pointToCashRatio) {
		// Input validation
		String error = "";
		if (storeID == null) {
			error = error + "Store id cannot be empty! ";
		}
		if (name == null || name.trim().length() == 0) {
			error = error + "Store name cannot be empty! ";
		}
		if (address == null || address.trim().length() == 0) {
			error = error + "Store address time cannot be empty! ";
		}
		if (phoneNumber == null || phoneNumber.trim().length() == 0) {
			error = error + "Store phone number cannot be empty! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "Store email cannot be empty! ";
		}
		if (employeeDiscountRate == null){
			error = error + "Employee discount rate cannot be empty! ";
		}
		else
		if (employeeDiscountRate > 100 || employeeDiscountRate < 0){
			error = error + "Employee discount rate must be between 0 and 100! ";
		}
		if (pointToCashRatio == null){
			error = error + "Point to cash ratio cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Store store = new Store();

		store.setName(name);
		store.setAddress(address);
		store.setPhoneNumber(phoneNumber);
		store.setEmail(email);
		store.setEmployeeDiscountRate(employeeDiscountRate);
		store.setPointToCashRatio(pointToCashRatio);

		storeRepository.save(store);

		return store;
	}

	@Transactional
	public Store getStore() {

		for (Store s : storeRepository.findAll()) {
			if (s != null) {
				return s;
			}
		}

		return null;
	}

	@Transactional
	public Terminal createTerminal(Integer id) {

		Terminal terminal = new Terminal();

		terminalRepository.save(terminal);

		return terminal;
	}

	@Transactional
	public List<Terminal> getAllTerminals() {

		return toList(terminalRepository.findAll());
	}

	@Transactional
	public TimeSlot createTimeSlot(Integer timeSlotID, Date startDate, Date endDate, Time startTime, Time endTime) {

		TimeSlot timeSlot = new TimeSlot();

		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);

		timeSlotRepository.save(timeSlot);

		return timeSlot;
	}

	public List<TimeSlot> getAllHolidays() {

		return new ArrayList<TimeSlot>(getStore().getHolidays());
	}

	@Transactional
	public WorkingHour createWorkingHour(Integer workingHourID, DayOfWeek dayOfWeek, Time startTime, Time endTime) {

		WorkingHour workingHour = new WorkingHour();

		workingHour.setDayOfWeek(dayOfWeek);
		workingHour.setStartTime(startTime);
		workingHour.setEndTime(endTime);

		workingHourRepository.save(workingHour);

		return workingHour;
	}

	@Transactional
	public WorkingHour getWorkingHourByID(Integer workingHourID) {
		for (WorkingHour workingHour : workingHourRepository.findAll()) {
			if (workingHour.getWorkingHourID().equals(workingHourID)) {
				return workingHour;
			}
		}
		return null;
	}

	@Transactional
	public List<WorkingHour> getAllWorkingHourIDs() {
		return toList(workingHourRepository.findAll());
	}

	@Transactional
	public Schedule getScheduleByID(Integer scheduleID) {
		for (Schedule schedule : scheduleRepository.findAll()) {
			if (schedule.getScheduleID().equals(scheduleID)) {
				return schedule;
			}
		}
		return null;
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
