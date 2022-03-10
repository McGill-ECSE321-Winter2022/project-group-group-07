package ca.mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
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

	@Transactional
	public Owner createOwnerRole(Integer id, Date employmentDate) {

		Owner owner = new Owner();
		owner.setRoleID(id);
		owner.setEmploymentDate(employmentDate);

		ownerRepository.save(owner);

		return owner;
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
	public Cashier createCashierRole(Integer id, Date employmentDate) {

		Cashier cashier = new Cashier();
		cashier.setRoleID(id);
		cashier.setEmploymentDate(employmentDate);

		cashierRepository.save(cashier);

		return cashier;
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
	public Customer createCustomerRole(Integer id) {

		Customer customer = new Customer();
		customer.setRoleID(id);

		customerRepository.save(customer);

		return customer;
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
	public Address createAddress(Integer id, Integer buildingNo, String street, String town, Account account) {

		Address address = new Address();

		address.setAddressID(id);
		address.setBuildingNo(buildingNo);
		address.setStreet(street);
		address.setTown(town);
		address.setAccount(account);

		addressRepository.save(address);

		return address;
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
	public Schedule createSchedule(Integer scheduleID, Employee employee, Set<WorkingHour> workingHour) {

		Schedule schedule = new Schedule();

		schedule.setScheduleID(scheduleID);
		schedule.setEmployee(employee);
		schedule.setWorkingHour(workingHour);

		scheduleRepository.save(schedule);

		return schedule;
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
	public Terminal createTerminal(Integer id) {

		Terminal terminal = new Terminal();

		terminal.setTerminalID(id);

		terminalRepository.save(terminal);

		return terminal;
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

}
