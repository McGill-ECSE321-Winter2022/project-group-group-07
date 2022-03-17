package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

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
import ca.mcgill.ecse321.grocerystore.dao.OrderRepository;
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
	private OrderRepository orderRepository;
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
	public Owner createOwnerRole() {

		Owner owner = new Owner();
		owner.setEmploymentDate(getCurrentDate());

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
	public Clerk createClerkRole() {

		Clerk clerk = new Clerk();
		clerk.setEmploymentDate(getCurrentDate());

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
	public Cashier createCashierRole() {

		Cashier cashier = new Cashier();
		cashier.setEmploymentDate(getCurrentDate());

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
	public DeliveryPerson createDeliveryPersonRole() {

		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setEmploymentDate(getCurrentDate());

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
	public void deleteAccount(String username, String password) {

		Account account = accountRepository.findByUsernameAndPassword(username, password);

		if (account != null) {
			accountRepository.delete(account);
		}

		return;
	}

	@Transactional
	public Account updatePassword(String username, String oldPassword, String newPassword) {

		Account account = accountRepository.findByUsernameAndPassword(username, oldPassword);

		account.setPassword(newPassword);

		accountRepository.save(account);

		return account;
	}

	@Transactional
	public Account updateName(String username, String newName) {

		Account account = accountRepository.findByUsername(username);

		account.setName(newName);

		accountRepository.save(account);

		return account;
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
	public Address updateAddress(String username, Integer buildingNo, String street, String town) {

		Account account = accountRepository.findByUsername(username);

		Address address = addressRepository.findByAccount(account);

		address.setBuildingNo(buildingNo);
		address.setStreet(street);
		address.setTown(town);

		addressRepository.save(address);

		return address;
	}

	@Transactional
	public BusinessHour createBusinessHour(DayOfWeek dayOfWeek, Time startTime, Time endTime) {
		String error = "";
		if (dayOfWeek == null) {
			error = error + "Business hour day of the week cannot be empty! ";
		}
		if (startTime == null) {
			error = error + "Business hour start time cannot be empty! ";
		}
		if (endTime == null) {
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
	public BusinessHour updateBusinessHourByDayOfWeek(DayOfWeek dayOfWeek, Time startTime, Time endTime) {

		BusinessHour bh = getBusinessHourByDay(dayOfWeek);

		if (bh != null) {
			bh.setStartTime(startTime);
			bh.setEndTime(endTime);
			businessHourRepository.save(bh);

			return bh;
		} else {
			throw new IllegalArgumentException("No business hour exists for" + dayOfWeek.toString());
		}
	}

	@Transactional
	public void deleteBusinessHourByDay(DayOfWeek dayOfWeek) {

		BusinessHour bh = getBusinessHourByDay(dayOfWeek);
		if (bh != null) {
			businessHourRepository.delete(bh);
		}
	}

	@Transactional
	public Cart createCart(OrderType orderType, Float totalValue, Integer numOfItems, Set<Item> items,
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
	public Cart createCart(String username) {

		Account account = getAccount(username);
		Cart cart = new Cart();
		Set<Item> items = new HashSet<Item>();

		cart.setAccount(account);
		cart.setNumOfItems(0);
		cart.setItems(items);
		cart.setTotalValue(0f);
		cartRepository.save(cart);

		return cart;
	}

	@Transactional
	public List<Cart> getAllCarts() {

		return toList(cartRepository.findAll());
	}

	@Transactional
	public Cart getCartByAccount(String username) {

		Account account = getAccount(username);
		Cart cart = cartRepository.findByAccount(account);
		if (cart != null) {
			return cart;
		} else {
			throw new IllegalArgumentException("User does not have a cart");
		}
	}

	@Transactional
	public List<Order> getAllOrders() {

		return toList(orderRepository.findAll());
	}

	@Transactional
	public DeliveryOrder createDeliveryOrder(Float totalValue, Date date, Time purchaseTime, Set<Item> items,
			TimeSlot timeSlot, Account account) {

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
	public PickUpOrder createPickUpOrder(Float totalValue, Date date, Time purchaseTime, Set<Item> items,
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
	public InStoreOrder createInStoreOrder(Float totalValue, Date date, Time purchaseTime, Set<Item> items,
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
	public NonPerishableItem createNonPerishableItem(String name, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {

		NonPerishableItem nonPerishableItem = new NonPerishableItem();
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
	public Report createReport(Date startDate, Date endDate, Float totalValue, Set<Order> orders) {

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
	public Report getReportById(Long reportID) {

		return reportRepository.findByReportID(reportID);
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
	public Schedule getScheduleByEmployee(String username) {

		Employee employee = null;
		if (accountRepository.findByUsername(username).getAccountRole() instanceof Employee) {
			employee = (Employee) accountRepository.findByUsername(username).getAccountRole();
		}
		return scheduleRepository.findByEmployee(employee);
	}

	@Transactional
	public void deleteScheduleByEmployee(String username) {
		Schedule schedule = null;
		Account account = accountRepository.findByUsername(username);
		if (account.getAccountRole() instanceof Employee) {
			schedule = scheduleRepository.findByEmployee((Employee) account.getAccountRole());
			scheduleRepository.delete(schedule);
		}

		return;
	}

	@Transactional
	public Store createStore(String name, String address, String phoneNumber, String email,
			Integer employeeDiscountRate, Float pointToCashRatio) {
		// Input validation
		String error = "";
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
		if (employeeDiscountRate == null) {
			error = error + "Employee discount rate cannot be empty! ";
		} else if (employeeDiscountRate > 100 || employeeDiscountRate < 0) {
			error = error + "Employee discount rate must be between 0 and 100! ";
		}
		if (pointToCashRatio == null) {
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
	public Store updateStore(String name, String address, String phoneNumber, String email,
			Integer employeeDiscountRate, Float pointToCashRatio) {

		Store store = getStore();
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
	public void deleteStore() {

		if (getStore() != null) {
			storeRepository.delete(getStore());
		}
	}

	@Transactional
	public Terminal createTerminal() {

		Terminal terminal = new Terminal();

		terminalRepository.save(terminal);

		return terminal;
	}

	@Transactional
	public List<Terminal> getAllTerminals() {

		return toList(terminalRepository.findAll());
	}

	@Transactional
	public TimeSlot createTimeSlot(Date startDate, Date endDate, Time startTime, Time endTime) {

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
	public WorkingHour createWorkingHour(DayOfWeek dayOfWeek, Time startTime, Time endTime) {

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

	private Date getCurrentDate() {
		long millis = System.currentTimeMillis();
		return new Date(millis);
	}

	@Transactional
	public WorkingHour getWorkingHourByEmployeeAndDayOfWeek(String username, DayOfWeek dayOfWeek) {

		Account account = getAccount(username);
		if (account != null && account.getAccountRole() instanceof Employee) {
			Schedule schedule = getScheduleByEmployee(username);
			if (schedule != null) {
				for (WorkingHour wh : schedule.getWorkingHour()) {
					if (wh.getDayOfWeek().equals(dayOfWeek)) {
						return wh;
					}
				}
			} else {
				throw new IllegalArgumentException("Employee is not assigned a schedule.");
			}
		} else {
			throw new IllegalArgumentException("The username does not belong to any employee.");
		}
		return null;
	}

	@Transactional
	public WorkingHour updateWorkingHourByEmployeeAndDayOfWeek(String username, DayOfWeek dayOfWeek, Time startTime,
			Time endTime) {

		WorkingHour wh = getWorkingHourByEmployeeAndDayOfWeek(username, dayOfWeek);

		wh.setStartTime(startTime);
		wh.setEndTime(endTime);
		workingHourRepository.save(wh);

		return wh;
	}

	@Transactional
	public void deleteTerminal(Long terminalID) {

		Terminal terminal = terminalRepository.findByTerminalID(terminalID);
		if (terminal != null) {
			terminalRepository.delete(terminal);
		} else {
			throw new IllegalArgumentException("No terminal with this ID exists");
		}

	}

	@Transactional
	public List<TimeSlot> getAllTimeSlots() {
		return toList(timeSlotRepository.findAll());
	}

	@Transactional
	public Cart addToCart(Long id, String username) {

		Cart cart = getCartByAccount(username);
		Item item = getNonPerishableItemsByID(id);
		if (item == null) {
			item = getPerishableItemsByID(id);
			if (item == null) {
				throw new IllegalArgumentException("Invalid Item ID");
			}
		}
		cart.getItems().add(item);
		cart.setNumOfItems(cart.getNumOfItems() + 1);
		cart.setTotalValue(cart.getTotalValue() + item.getPrice());
		cartRepository.save(cart);

		return cart;
	}

	@Transactional
	public Cart addTimeSlotToCart(String username, TimeSlot createTimeSlot) {

		Cart cart = getCartByAccount(username);

		cart.setTimeSlot(createTimeSlot);
		cartRepository.save(cart);

		return cart;
	}

	@Transactional
	public Cart chooseOrderTypeForCart(String username, OrderType orderType) {

		Cart cart = getCartByAccount(username);

		cart.setOrderType(orderType);
		cartRepository.save(cart);
		return cart;
	}

}
