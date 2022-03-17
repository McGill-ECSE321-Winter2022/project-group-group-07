package ca.mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;
import ca.mcgill.ecse321.grocerystore.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PickUpOrder;
import ca.mcgill.ecse321.grocerystore.model.Report;
import ca.mcgill.ecse321.grocerystore.model.Schedule;
import ca.mcgill.ecse321.grocerystore.model.Store;
import ca.mcgill.ecse321.grocerystore.model.Terminal;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.model.WorkingHour;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStorePersistence {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountRoleRepository accountRoleRepository;
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
	private ItemRepository itemRepository;
	@Autowired
	private NonPerishableItemRepository nonPerishableItemRepository;
	@Autowired
	private OrderRepository orderRepository;
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

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		cartRepository.deleteAll();
		reportRepository.deleteAll();
		businessHourRepository.deleteAll();
		storeRepository.deleteAll();
		terminalRepository.deleteAll();
		orderRepository.deleteAll();
		inStoreOrderRepository.deleteAll();
		deliveryOrderRepository.deleteAll();
		pickUpOrderRepository.deleteAll();
		timeSlotRepository.deleteAll();
		itemRepository.deleteAll();
		perishableItemRepository.deleteAll();
		nonPerishableItemRepository.deleteAll();
		addressRepository.deleteAll();
		accountRepository.deleteAll();
		workingHourRepository.deleteAll();
		scheduleRepository.deleteAll();
		accountRoleRepository.deleteAll();
		customerRepository.deleteAll();
		employeeRepository.deleteAll();
		deliveryPersonRepository.deleteAll();
		ownerRepository.deleteAll();
		cashierRepository.deleteAll();
		clerkRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadCustomer() {

		Customer customer = new Customer();

		customerRepository.save(customer);

		customer = null;
		customer = ((List<Customer>) customerRepository.findAll()).get(0);

		assertNotNull(customer);
		assertNotNull(customer.getRoleID());
	}

	@Test
	public void testPersistAndLoadCashier() {

		Date date = Date.valueOf("2022-01-02");

		Cashier cashier = new Cashier();
		cashier.setEmploymentDate(date);
		cashierRepository.save(cashier);

		cashier = null;
		cashier = ((List<Cashier>) cashierRepository.findAll()).get(0);

		assertNotNull(cashier);
		assertNotNull(cashier.getRoleID());
		assertEquals(date, cashier.getEmploymentDate());
	}

	@Test
	public void testPersistAndLoadClerk() {

		Date date = Date.valueOf("2022-01-02");

		Clerk clerk = new Clerk();
		clerk.setEmploymentDate(date);
		clerkRepository.save(clerk);

		clerk = null;
		clerk = ((List<Clerk>) clerkRepository.findAll()).get(0);

		assertNotNull(clerk);
		assertNotNull(clerk.getRoleID());
		assertEquals(date, clerk.getEmploymentDate());
	}

	@Test
	public void testPersistAndLoadDeliveryPerson() {

		Date date = Date.valueOf("2022-01-02");

		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setEmploymentDate(date);
		deliveryPersonRepository.save(deliveryPerson);

		deliveryPerson = null;
		deliveryPerson = ((List<DeliveryPerson>) deliveryPersonRepository.findAll()).get(0);

		assertNotNull(deliveryPerson);
		assertNotNull(deliveryPerson.getRoleID());
		assertEquals(date, deliveryPerson.getEmploymentDate());
	}

	@Test
	public void testPersistAndLoadOwner() {

		Date date = Date.valueOf("2022-01-02");

		Owner owner = new Owner();
		owner.setEmploymentDate(date);
		ownerRepository.save(owner);

		owner = null;
		owner = ((List<Owner>) ownerRepository.findAll()).get(0);

		assertNotNull(owner);
		assertNotNull(owner.getRoleID());
		assertEquals(date, owner.getEmploymentDate());
	}

	@Test
	public void testPersistAndLoadBusinessHour() {
		// create business hour and store

		String name = "BreadnBake";
		String address = "1 ave";
		String phoneNumber = "555-555-5555";
		String email = "john@yahoo.com";
		Integer employeeDiscount = 20;
		Float pointToCash = (float) 1000.0;

		Store store = new Store();

		store.setName(name);
		store.setAddress(address);
		store.setPhoneNumber(phoneNumber);
		store.setEmail(email);
		store.setEmployeeDiscountRate(employeeDiscount);
		store.setPointToCashRatio(pointToCash);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		Set<TimeSlot> holidays = new HashSet();
		store.setHolidays(holidays);
		storeRepository.save(store);

		DayOfWeek dayOfWeek = DayOfWeek.Monday;
		Time startTime = Time.valueOf("09:00:00");
		Time endTime = Time.valueOf("12:00:00");

		BusinessHour businessHour = new BusinessHour();

		businessHour.setStore(store);
		businessHour.setDayOfWeek(dayOfWeek);
		businessHour.setStartTime(startTime);
		businessHour.setEndTime(endTime);
		businessHourRepository.save(businessHour);

		businessHour = null;

		businessHour = ((List<BusinessHour>) businessHourRepository.findAll()).get(0);

		assertNotNull(businessHour);
		assertEquals(store.getStoreID(), businessHour.getStore().getStoreID());
		assertEquals(businessHour.getStartTime(), startTime);
		assertEquals(businessHour.getEndTime(), endTime);
		assertEquals(businessHour.getDayOfWeek(), dayOfWeek);
	}

	@Test
	public void testPersistAndLoadTerminal() {

		Terminal terminal = new Terminal();
		terminalRepository.save(terminal);

		terminal = null;
		terminal = ((List<Terminal>) terminalRepository.findAll()).get(0);

		assertNotNull(terminal);
		assertNotNull(terminal.getTerminalID());
	}

	@Test
	public void testPersistAndLoadReport() {

		Report report = new Report();
		reportRepository.save(report);

		report = null;
		report = ((List<Report>) reportRepository.findAll()).get(0);

		assertNotNull(report);
		assertNotNull(report.getReportID());
	}

	@Test
	public void testPersistAndLoadStore() {

		String name = "BreadnBake";
		String address = "1 ave";
		String phoneNumber = "555-555-5555";
		String email = "john@yahoo.com";
		Integer employeeDiscount = 20;
		Float pointToCash = (float) 1000.0;
		Store store = new Store();

		store.setAddress(address);
		store.setPhoneNumber(phoneNumber);
		store.setEmail(email);
		store.setEmployeeDiscountRate(employeeDiscount);
		store.setName(name);
		store.setPointToCashRatio(pointToCash);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<TimeSlot> holidays = new HashSet();
		store.setHolidays(holidays);
		storeRepository.save(store);

		store = null;
		store = ((List<Store>) storeRepository.findAll()).get(0);

		assertNotNull(store);
		assertNotNull(store.getStoreID());
		assertEquals(name, store.getName());
		assertEquals(address, store.getAddress());
		assertEquals(phoneNumber, store.getPhoneNumber());
		assertEquals(email, store.getEmail());
		assertEquals(employeeDiscount, store.getEmployeeDiscountRate());
		assertEquals(pointToCash, store.getPointToCashRatio());
	}

	@Test
	public void testPersistAndLoadPerishableItem() {

		PerishableItem perishable = new PerishableItem();

		perishable.setProductName("Apple");
		perishable.setAvailableOnline(true);
		perishable.setNumInStock(1);
		perishable.setPointPerItem(5);
		perishable.setPrice((float) 2.0);

		perishableItemRepository.save(perishable);

		perishable = null;
		perishable = ((List<PerishableItem>) perishableItemRepository.findAll()).get(0);

		assertNotNull(perishable);
		assertEquals("Apple", perishable.getProductName());
		assertEquals(true, perishable.getAvailableOnline());
		assertEquals(1, perishable.getNumInStock());
		assertEquals(5, perishable.getPointPerItem());
		assertEquals((float) 2.0, perishable.getPrice());
	}

	@Test
	public void testPersistAndLoadNonPerishableItem() {

		NonPerishableItem nonPerishable = new NonPerishableItem();

		nonPerishable.setProductName("Desk");
		nonPerishable.setAvailableOnline(true);
		nonPerishable.setNumInStock(1);
		nonPerishable.setPointPerItem(10);
		nonPerishable.setPrice((float) 50.0);

		nonPerishableItemRepository.save(nonPerishable);

		nonPerishable = null;

		nonPerishable = ((List<NonPerishableItem>) nonPerishableItemRepository.findAll()).get(0);

		assertNotNull(nonPerishable);
		assertEquals("Desk", nonPerishable.getProductName());
		assertEquals(true, nonPerishable.getAvailableOnline());
		assertEquals(1, nonPerishable.getNumInStock());
		assertEquals(10, nonPerishable.getPointPerItem());
		assertEquals((float) 50.0, nonPerishable.getPrice());
	}

	@Test
	public void testPersistAndLoadTimeSlot() {

		Date startDate = Date.valueOf("2022-01-02");
		Date endDate = Date.valueOf("2022-01-02");
		Time startTime = Time.valueOf("14:02:03");
		Time endTime = Time.valueOf("15:00:00");

		TimeSlot timeSlot = new TimeSlot();

		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlotRepository.save(timeSlot);

		timeSlot = null;
		timeSlot = ((List<TimeSlot>) timeSlotRepository.findAll()).get(0);

		assertNotNull(timeSlot);
		assertNotNull(timeSlot.getTimeSlotID());
		assertEquals(startDate, timeSlot.getStartDate());
		assertEquals(endDate, timeSlot.getEndDate());
		assertEquals(startTime, timeSlot.getStartTime());
		assertEquals(endTime, timeSlot.getEndTime());

	}

	@Test
	public void testPersistAndLoadAccount() {

		String username = "TestCustomer";
		String password = "TestPassword";
		String name = "TestName";
		Integer pointBalance = 0;

		Customer customer = new Customer();
		customerRepository.save(customer);

		Account account = new Account();

		account.setAccountRole(customer);
		account.setUsername(username);
		account.setPassword(password);
		account.setName(name);
		account.setPointBalance(pointBalance);

		accountRepository.save(account);

		account = null;
		account = accountRepository.findByUsername(username);

		assertNotNull(account);
		assertEquals(account.getUsername(), username);
		assertEquals(account.getPassword(), password);
		assertEquals(account.getName(), name);
		assertEquals(account.getPointBalance(), pointBalance);
		assertEquals(customer.getRoleID(), account.getAccountRole().getRoleID());
	}

	@Test
	public void testPersistAndLoadCart() {

		OrderType orderType = OrderType.Delivery;
		Float totalValue = 100f;
		Integer numberOfItems = 2;

		Cart cart = new Cart();
		cart.setOrderType(orderType);
		cart.setTotalValue(totalValue);
		cart.setNumOfItems(numberOfItems);

		Set<Item> items = new HashSet<Item>();
		cart.setItems(items);

		Date startDate = Date.valueOf("2022-02-02");
		Date endDate = Date.valueOf("2022-02-02");
		Time startTime = Time.valueOf("13:02:03");
		Time endTime = Time.valueOf("14:00:00");

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);

		cart.setTimeSlot(timeSlot);
		timeSlotRepository.save(timeSlot);

		Account account = new Account();

		String username = "JhonnyBoy";
		String password = "1234";
		String name = "John";
		Integer pointBalance = 10;

		AccountRole accountRole = new Customer();

		account.setUsername(username);
		account.setPassword(password);
		account.setName(name);
		account.setPointBalance(pointBalance);
		account.setAccountRole(accountRole);
		accountRoleRepository.save(accountRole);
		accountRepository.save(account);

		cart.setAccount(account);
		cartRepository.save(cart);

		cart = null;
		cart = ((List<Cart>) cartRepository.findAll()).get(0);

		assertNotNull(cart);
		assertNotNull(cart.getCartID());
		assertEquals(orderType, cart.getOrderType());
		assertEquals(totalValue, cart.getTotalValue());
		assertEquals(numberOfItems, cart.getNumOfItems());
		assertEquals(timeSlot.getTimeSlotID(), cart.getTimeSlot().getTimeSlotID());
		assertEquals(account.getUsername(), cart.getaccount().getUsername());

	}

	@Test
	public void testPersistAndLoadAddress() {

		Customer customer = new Customer();
		customerRepository.save(customer);

		Account account = new Account();
		account.setAccountRole(customer);
		account.setName("Coco");
		account.setPassword("Pass");
		account.setUsername("cocho");
		account.setPointBalance(50);
		accountRepository.save(account);

		Integer buildingNO = 5;
		String street = "Ave River";
		String town = "Ocean";
		Address address = new Address();
		address.setAccount(account);
		address.setBuildingNo(buildingNO);
		address.setStreet(street);
		address.setTown(town);
		addressRepository.save(address);

		address = null;
		address = ((List<Address>) addressRepository.findAll()).get(0);

		assertNotNull(address);
		assertNotNull(address.getAddressID());
		assertEquals(buildingNO, address.getBuildingNo());
		assertEquals(street, address.getStreet());
		assertEquals(town, address.getTown());
		assertEquals(account.getUsername(), address.getAccount().getUsername());
	}

	@Test
	public void testPersistAndLoadInStoreOrder() {

		Customer customer = new Customer();
		customerRepository.save(customer);

		Account account = new Account();
		account.setAccountRole(customer);
		account.setName("Coco");
		account.setPassword("Pass");
		account.setUsername("cocho");
		account.setPointBalance(50);
		accountRepository.save(account);

		InStoreOrder order = new InStoreOrder();
		order.setAccount(account);

		Date date = Date.valueOf("2022-01-02");
		order.setDate(date);

		Time time = Time.valueOf("14:02:03");
		order.setPurchaseTime(time);

		Set<Item> set = new HashSet<Item>();
		order.setItems(set);

		order.setTotalValue(50f);

		inStoreOrderRepository.save(order);

		order = null;
		order = ((List<InStoreOrder>) inStoreOrderRepository.findAll()).get(0);

		assertNotNull(order);
		assertNotNull(order.getOrderID());
		assertEquals((float) 50.0, order.getTotalValue());
		assertEquals(account.getUsername(), order.getAccount().getUsername());
		assertEquals(time, order.getPurchaseTime());
		assertEquals(date, order.getDate());
	}

	@Test
	public void testPersistAndLoadPickupOrder() {

		Customer customer = new Customer();
		customerRepository.save(customer);

		Account account = new Account();
		account.setAccountRole(customer);
		account.setName("Coco");
		account.setPassword("Pass");
		account.setUsername("cocho");
		account.setPointBalance(50);
		accountRepository.save(account);

		PickUpOrder pickUpOrder = new PickUpOrder();
		pickUpOrder.setAccount(account);

		Date date = Date.valueOf("2022-01-02");
		pickUpOrder.setDate(date);
		
		NonPerishableItem nonPerishable = new NonPerishableItem();

		nonPerishable.setProductName("Desk");
		nonPerishable.setAvailableOnline(true);
		nonPerishable.setNumInStock(1);
		nonPerishable.setPointPerItem(10);
		nonPerishable.setPrice((float) 50.0);

		nonPerishableItemRepository.save(nonPerishable);

		Set<Item> set = new HashSet<Item>();
		pickUpOrder.setItems(set);

		Time time = Time.valueOf("14:02:03");
		pickUpOrder.setPurchaseTime(time);

		pickUpOrder.setTotalValue((float) 50.0);

		Date startDate = Date.valueOf("2022-01-02");
		Date endDate = Date.valueOf("2022-01-02");
		Time startTime = Time.valueOf("14:02:03");
		Time endTime = Time.valueOf("15:00:00");

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlotRepository.save(timeSlot);

		pickUpOrder.setTimeSlot(timeSlot);

		pickUpOrderRepository.save(pickUpOrder);
		
		pickUpOrder.getItems().add(nonPerishable);

		pickUpOrder = null;
		pickUpOrder = ((List<PickUpOrder>) pickUpOrderRepository.findAll()).get(0);

		assertNotNull(pickUpOrder);
		assertNotNull(pickUpOrder.getOrderID());
		assertEquals((float) 50.0, pickUpOrder.getTotalValue());
		assertEquals(date, pickUpOrder.getDate());
		assertEquals(time, pickUpOrder.getPurchaseTime());
		assertEquals(timeSlot.getTimeSlotID(), pickUpOrder.getTimeSlot().getTimeSlotID());
		assertEquals(account.getUsername(), pickUpOrder.getAccount().getUsername());
	}

	@Test
	public void testPersistAndLoadSchedule() {

		Cashier cashier = new Cashier();
		cashierRepository.save(cashier);

		Schedule schedule = new Schedule();
		Set<WorkingHour> scheduleHours = new HashSet<WorkingHour>();
		schedule.setWorkingHour(scheduleHours);
		schedule.setEmployee(cashier);
		scheduleRepository.save(schedule);

		schedule = null;
		schedule = ((List<Schedule>) scheduleRepository.findAll()).get(0);

		assertNotNull(schedule);
		assertNotNull(schedule.getScheduleID());
		assertEquals(cashier.getRoleID(), schedule.getEmployee().getRoleID());
	}

	@Test
	public void testPersistAndLoadWorkingHour() {

		Cashier cashier = new Cashier();
		cashierRepository.save(cashier);

		DayOfWeek dayOfWeek = DayOfWeek.Tuesday;
		Time startTime = Time.valueOf("01:00:00");
		Time endTime = Time.valueOf("02:00:00");
		WorkingHour workingHour = new WorkingHour();

		workingHour.setDayOfWeek(dayOfWeek);
		workingHour.setStartTime(startTime);
		workingHour.setEndTime(endTime);
		workingHourRepository.save(workingHour);

		workingHour = null;
		workingHour = ((List<WorkingHour>) workingHourRepository.findAll()).get(0);

		assertNotNull(workingHour);
		assertNotNull(workingHour.getWorkingHourID());
		assertEquals(dayOfWeek, workingHour.getDayOfWeek());
		assertEquals(startTime, workingHour.getStartTime());
		assertEquals(endTime, workingHour.getEndTime());
	}

	@Test
	public void testPersistAndLoadDeliveryOrder() {

		Customer customer = new Customer();
		customerRepository.save(customer);

		Account account = new Account();
		account.setAccountRole(customer);
		account.setName("Coco");
		account.setPassword("Pass");
		account.setUsername("cocho");
		account.setPointBalance(50);
		accountRepository.save(account);

		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.setAccount(account);

		Date date = Date.valueOf("2022-01-02");
		deliveryOrder.setDate(date);

		Set<Item> set = new HashSet<Item>();
		deliveryOrder.setItems(set);

		Time time = Time.valueOf("14:02:03");
		deliveryOrder.setPurchaseTime(time);

		deliveryOrder.setTotalValue((float) 50.0);

		Date startDate = Date.valueOf("2022-01-02");
		Date endDate = Date.valueOf("2022-01-02");
		Time startTime = Time.valueOf("14:02:03");
		Time endTime = Time.valueOf("15:00:00");

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartDate(startDate);
		timeSlot.setEndDate(endDate);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlotRepository.save(timeSlot);

		deliveryOrder.setTimeSlot(timeSlot);

		deliveryOrderRepository.save(deliveryOrder);

		deliveryOrder = null;
		deliveryOrder = ((List<DeliveryOrder>) deliveryOrderRepository.findAll()).get(0);

		assertNotNull(deliveryOrder);
		assertNotNull(deliveryOrder.getOrderID());
		assertEquals((float) 50.0, deliveryOrder.getTotalValue());
		assertEquals(date, deliveryOrder.getDate());
		assertEquals(time, deliveryOrder.getPurchaseTime());
		assertEquals(timeSlot.getTimeSlotID(), deliveryOrder.getTimeSlot().getTimeSlotID());
		assertEquals(account.getUsername(), deliveryOrder.getAccount().getUsername());
	}
}
