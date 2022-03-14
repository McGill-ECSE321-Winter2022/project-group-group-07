package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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

	private Integer roleID = 0;
	private Integer addressID = 0;

	@Transactional
	public Owner createOwnerRole(Integer id, Date employmentDate) {

		Owner owner = new Owner();
		owner.setRoleID(id);
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
	public Clerk createClerkRole(Integer id, Date employmentDate) {

		Clerk clerk = new Clerk();
		clerk.setRoleID(id);
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
	public Cashier createCashierRole(Integer id, Date employmentDate) {

		Cashier cashier = new Cashier();
		cashier.setRoleID(id);
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
	public DeliveryPerson createDeliveryPersonRole(Integer id, Date employmentDate) {

		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setRoleID(id);
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
		customer.setRoleID(roleID);
		roleID++;

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
	public Address createAddress( Integer buildingNo, String street, String town, Account account) {

		Address address = new Address();

		address.setAddressID(addressID);
		address.setBuildingNo(buildingNo);
		address.setStreet(street);
		address.setTown(town);
		address.setAccount(account);
		
		addressID++;

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

		BusinessHour hour = new BusinessHour();

		hour.setBusinessHourId(id);
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

		cart.setCartID(id);
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

		cart.setCartID(id);
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

		deliveryOrder.setOrderID(id);
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

		pickUpOrder.setOrderID(id);
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

		inStoreOrder.setOrderID(id);
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
	public PerishableItem createPerishableItem(Integer id, String name, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {

		PerishableItem perishableItem = new PerishableItem();

		perishableItem.setItemID(id);
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
	public PerishableItem getPerishableItemsByID(Integer id) {

		return perishableItemRepository.findByItemID(id);
	}
	@Transactional
	public void deletePerishableItemsByID(Integer id) {
		perishableItemRepository.deleteByItemID(id);
		
	}
	
	@Transactional
	public List<PerishableItem> getPerishableItemsByProductName(String name) {
		return perishableItemRepository.findByProductName(name);
	}

	@Transactional
	public NonPerishableItem createNonPerishableItem(Integer id, String name, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {

		NonPerishableItem nonPerishableItem = new NonPerishableItem();

		nonPerishableItem.setItemID(id);
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
	public NonPerishableItem getNonPerishableItemsByID(Integer id) {
		
		return nonPerishableItemRepository.findByItemID(id);
	}
	
	@Transactional
	public List<NonPerishableItem> getNonPerishableItemsByProductName(String name) {
		return nonPerishableItemRepository.findByProductName(name);
	}
	
	@Transactional
	public void deleteNonPerishableItemsByID(Integer id) {
		nonPerishableItemRepository.deleteByItemID(id);
	}

	@Transactional
	public Report createReport(Integer id, Date startDate, Date endDate, Float totalValue, Set<Order> orders) {

		Report report = new Report();

		report.setReportID(id);
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
	public Schedule createSchedule(Integer scheduleID, Employee employee, Set<WorkingHour> workingHour) {

		Schedule schedule = new Schedule();

		schedule.setScheduleID(scheduleID);
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

		Store store = new Store();

		store.setStoreID(storeID);
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

		terminal.setTerminalID(id);

		terminalRepository.save(terminal);

		return terminal;
	}
	
	@Transactional
	public List<Terminal> getAllTerminals(){
		
		return toList(terminalRepository.findAll());
	}

	@Transactional
	public TimeSlot createTimeSlot(Integer timeSlotID, Date startDate, Date endDate, Time startTime, Time endTime) {

		TimeSlot timeSlot = new TimeSlot();

		timeSlot.setTimeSlotID(timeSlotID);
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);

		timeSlotRepository.save(timeSlot);

		return timeSlot;
	}
	
	public List<TimeSlot> getAllHolidays(){
		
		return new ArrayList<TimeSlot>(getStore().getHolidays());
	}

	@Transactional
	public WorkingHour createWorkingHour(Integer workingHourID, DayOfWeek dayOfWeek, Time startTime, Time endTime) {

		WorkingHour workingHour = new WorkingHour();

		workingHour.setWorkingHourID(workingHourID);
		workingHour.setDayOfWeek(dayOfWeek);
		workingHour.setStartTime(startTime);
		workingHour.setEndTime(endTime);

		workingHourRepository.save(workingHour);

		return workingHour;
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
