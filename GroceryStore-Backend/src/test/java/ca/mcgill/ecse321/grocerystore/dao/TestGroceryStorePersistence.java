package ca.mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
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
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem;
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

	@AfterEach
	public void clearDatabase() {
		// deleting dependent classes first to avoid null values in non null columns
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
		Integer roleID = 1;
		Customer customer = new Customer();
		customer.setRoleID(roleID);
		customerRepository.save(customer);
		customer = null;
		customer = customerRepository.findCustomerByRoleID(roleID);
		assertNotNull(customer);
		assertEquals(roleID, customer.getRoleID());
	}

	@Test
	public void testPersistAndLoadCashier() {
		Integer roleID = 1;
		Cashier cashier = new Cashier();
		cashier.setRoleID(roleID);
		cashierRepository.save(cashier);
		cashier = null;
		cashier = cashierRepository.findByRoleID(roleID);
		assertNotNull(cashier);
		assertEquals(roleID, cashier.getRoleID());
	}

	@Test
	public void testPersistAndLoadClerk() {
		Integer roleID = 1;
		Clerk clerk = new Clerk();
		clerk.setRoleID(roleID);
		clerkRepository.save(clerk);
		clerk = null;
		clerk = clerkRepository.findByRoleID(roleID);
		assertNotNull(clerk);
		assertEquals(roleID, clerk.getRoleID());
	}

	@Test
	public void testPersistAndLoadDeliveryPerson() {
		Integer roleID = 1;
		DeliveryPerson deliveryPerson = new DeliveryPerson();
		deliveryPerson.setRoleID(roleID);
		deliveryPersonRepository.save(deliveryPerson);

		deliveryPerson = null;

		deliveryPerson = deliveryPersonRepository.findByRoleID(roleID);
		assertNotNull(deliveryPerson);
		assertEquals(roleID, deliveryPerson.getRoleID());
	}

	@Test
	public void testPersistAndLoadOwner() {
		Integer roleID = 1;
		Date date = Date.valueOf("2022-01-01");
		Owner owner = new Owner();
		owner.setRoleID(roleID);
		owner.setEmploymentDate(date);
		ownerRepository.save(owner);

		owner = null;

		owner = ownerRepository.findByRoleID(roleID);
		assertNotNull(owner);
		assertEquals(roleID, owner.getRoleID());
		assertEquals(date, owner.getEmploymentDate());
	}

	@Test
	public void testPersistAndLoadBusinessHour() {

		Integer storeID = 1;
		String name = "BreadnBake";
		String address = "1 ave";
		String phoneNumber = "555-555-5555";
		String email = "john@yahoo.com";
		Integer employeeDiscount = 20;
		Float pointToCash = (float) 1000.0;
		Store store = new Store();
		store.setStoreID(storeID);
		store.setAddress(address);
		store.setPhoneNumber(phoneNumber);
		store.setEmail(email);
		store.setEmployeeDiscountRate(employeeDiscount);
		store.setName(name);
		store.setPointToCashRatio(pointToCash);
		Set<TimeSlot> holidays = new HashSet();
		store.setHolidays(holidays);
		storeRepository.save(store);

		Integer businessHourId = 1;
		BusinessHour businessHour = new BusinessHour();
		businessHour.setBusinessHourId(businessHourId);
		businessHour.setStore(store);
		businessHourRepository.save(businessHour);

		businessHour = null;

		businessHour = businessHourRepository.findByBusinessHourId(businessHourId);
		assertNotNull(businessHour);
		assertEquals(businessHourId, businessHour.getBusinessHourId());
	}

	@Test
	public void testPersistAndLoadTerminal() {
		Integer terminalID = 1;
		Terminal terminal = new Terminal();
		terminal.setTerminalID(terminalID);
		terminalRepository.save(terminal);

		terminal = null;

		terminal = terminalRepository.findByTerminalID(terminalID);
		assertNotNull(terminal);
		assertEquals(terminalID, terminal.getTerminalID());
	}

	@Test
	public void testPersistAndLoadReport() {
		Integer reportID = 1;
		Report report = new Report();
		report.setReportID(reportID);
		reportRepository.save(report);

		report = null;

		report = reportRepository.findByReportID(reportID);
		assertNotNull(report);
		assertEquals(reportID, report.getReportID());
	}
	@Test
	public void testPersistAndLoadStore() {

		Integer storeID = 1;
		String name = "BreadnBake";
		String address = "1 ave";
		String phoneNumber = "555-555-5555";
		String email = "john@yahoo.com";
		Integer employeeDiscount = 20;
		Float pointToCash = (float) 1000.0;
		Store store = new Store();
		store.setStoreID(storeID);
		store.setAddress(address);
		store.setPhoneNumber(phoneNumber);
		store.setEmail(email);
		store.setEmployeeDiscountRate(employeeDiscount);
		store.setName(name);
		store.setPointToCashRatio(pointToCash);
		Set<TimeSlot> holidays = new HashSet();
		store.setHolidays(holidays);
		storeRepository.save(store);
		store=null;
		store=storeRepository.findByStoreID(storeID);
		assertNotNull(store);
		assertEquals(storeID,store.getStoreID());
		assertEquals(name,store.getName());
		assertEquals(address,store.getAddress());
		assertEquals(phoneNumber,store.getPhoneNumber());
		assertEquals(email,store.getEmail());
		assertEquals(employeeDiscount,store.getEmployeeDiscountRate());
		assertEquals(pointToCash,store.getPointToCashRatio());
	}
	@Test
    public void testPersistAndLoadPerishableItem() {
        Integer perishableID = 1;
        PerishableItem perishable = new PerishableItem();
        perishable.setItemID(perishableID);
        perishable.setAvailableOnline(true);
        perishable.setNumInStock(1);
        perishable.setPointPerItem(5);
        perishable.setPrice((float) 2.0);
        perishable.setProductName("Apple");
        perishableItemRepository.save(perishable);

        perishable = null;

        perishable  = perishableItemRepository.findByItemID(perishableID);
        assertNotNull(perishable);
        assertEquals(perishableID,perishable.getItemID());
        assertEquals(true,perishable.getAvailableOnline());
        assertEquals(1,perishable.getNumInStock());
        assertEquals(5,perishable.getPointPerItem());
        assertEquals((float)2.0,perishable.getPrice());
        assertEquals("Apple",perishable.getProductName());
    }

    @Test
    public void testPersistAndLoadNonPerishableItem() {
        Integer nonPerishableID = 1;
        NonPerishableItem nonPerishable = new NonPerishableItem();
        nonPerishable.setItemID(nonPerishableID);
        nonPerishable.setAvailableOnline(true);
        nonPerishable.setNumInStock(1);
        nonPerishable.setPointPerItem(10);
        nonPerishable.setPrice((float) 50.0);
        nonPerishable.setProductName("Desk");
        nonPerishableItemRepository.save(nonPerishable);

        nonPerishable = null;

        nonPerishable  = nonPerishableItemRepository.findByItemID(nonPerishableID);
        assertNotNull(nonPerishable);
        assertEquals(nonPerishableID,nonPerishable.getItemID());
        assertEquals(true,nonPerishable.getAvailableOnline());
        assertEquals(1,nonPerishable.getNumInStock());
        assertEquals(10,nonPerishable.getPointPerItem());
        assertEquals((float)50.0,nonPerishable.getPrice());
        assertEquals("Desk",nonPerishable.getProductName());
    }
    @Test
    public void testPersistAndLoadTimeSlot() {
    	Integer timeSlotID=25;
    	Date startDate=Date.valueOf("2022-01-02");
    	Date endDate=Date.valueOf("2022-01-02");
    	Time startTime=Time.valueOf("14:02:03");
    	Time endTime=Time.valueOf("15:00:00");
    	
    	TimeSlot timeSlot=new TimeSlot();
    	timeSlot.setTimeSlotID(timeSlotID);
    	timeSlot.setStartDate(startDate);
    	timeSlot.setEndDate(endDate);
    	timeSlot.setStartTime(startTime);
    	timeSlot.setEndTime(endTime);
    	timeSlotRepository.save(timeSlot);
    	
    	timeSlot=null;
    	timeSlot=timeSlotRepository.findByTimeSlotID(timeSlotID);
    	assertNotNull(timeSlot);
    	assertEquals(timeSlotID,timeSlot.getTimeSlotID());
    	assertEquals(startDate,timeSlot.getStartDate());
    	assertEquals(endDate,timeSlot.getEndDate());
    	assertEquals(startTime,timeSlot.getStartTime());
    	assertEquals(endTime,timeSlot.getEndTime());
    	
    }
    @Test
	public void testPersistAndLoadAccount() {
		Integer roleID = 5;
		Customer customer = new Customer();
		customer.setRoleID(roleID);
		customerRepository.save(customer);
		Account account= new Account();
		customer=customerRepository.findCustomerByRoleID(roleID);
		
		account.setAccountRole(customer);
		account.setName("Coco");
		account.setPassword("Pass");
		account.setUsername("cocho");
		account.setPointBalance(50);
		accountRepository.save(account);
		
		account = null;
		account = accountRepository.findByUsername("cocho");
		
		assertNotNull(account);
		assertEquals("cocho",account.getUsername());
	}
    //Adam
    @Test
    public void testPersistAndLoadCart() {
        Integer cartID=30;
        OrderType orderType = OrderType.Delivery;
        Float totalValue = 100f;
        Integer numberOfItems = 2;

        Cart cart = new Cart();
        cart.setCartID(cartID);
        cart.setOrderType(orderType);
        cart.setTotalValue(totalValue);
        cart.setNumOfItems(numberOfItems);
        //Create and add items
        Set<Item> items = new HashSet<Item>();
        Item item = new PerishableItem();
        Integer itemID = 31;
        String productName = "Bread";
        Float price = 40f;
        Boolean availableOnline = false;
        Integer numInStock = 10;
        Integer pointPerItem = 1;
        
        item.setItemID(itemID);
        item.setProductName(productName);
        item.setPrice(price);
        item.setAvailableOnline(availableOnline);
        item.setNumInStock(numInStock);
        item.setPointPerItem(pointPerItem);
        
        Item item2 = new NonPerishableItem();
        Integer itemID2 = 32;
        String productName2 = "Table";
        Float price2 = 200f;
        Boolean availableOnline2 = true;
        Integer numInStock2 = 3;
        Integer pointPerItem2 = 10;
        
        item2.setItemID(itemID2);
        item2.setProductName(productName2);
        item2.setPrice(price2);
        item2.setAvailableOnline(availableOnline2);
        item2.setNumInStock(numInStock2);
        item2.setPointPerItem(pointPerItem2);
        
        items.add(item);
        items.add(item2);
        itemRepository.save(item);
        itemRepository.save(item2);
        //Set items
        cart.setItems(items);
        //Create TimeSlot
        Integer timeSlotID=35;
        Date startDate=Date.valueOf("2022-02-02");
        Date endDate=Date.valueOf("2022-02-02");
        Time startTime=Time.valueOf("13:02:03");
        Time endTime=Time.valueOf("14:00:00");

        TimeSlot timeSlot=new TimeSlot();
        timeSlot.setTimeSlotID(timeSlotID);
        timeSlot.setStartDate(startDate);
        timeSlot.setEndDate(endDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        //Set TimeSlot
        timeSlotRepository.save(timeSlot);
        cart.setTimeSlot(timeSlot);
        
        //Create Account
        Account account = new Account();

        String username = "whatever";
        String password = "1234";
        String name = "John";
        Integer pointBalance = 10;
        AccountRole accountRole = new Customer();
        accountRole.setRoleID(33);
        
        account.setUsername(username);
        account.setPassword(password);
        account.setName(name);
        account.setPointBalance(pointBalance);
        account.setAccountRole(accountRole);
        accountRoleRepository.save(accountRole);
        //Set Account
        accountRepository.save(account);
        cart.setAccount(account);
        
        cartRepository.save(cart);

        cart=null;
        cart=cartRepository.findByCartID(cartID);
        
        assertNotNull(cart);
        assertEquals(cartID,cart.getCartID());
        assertEquals(orderType,cart.getOrderType());
        assertEquals(totalValue,cart.getTotalValue());
        assertEquals(numberOfItems,cart.getNumOfItems());
        assertEquals(timeSlot.getTimeSlotID(), cart.getTimeSlot().getTimeSlotID());
        assertEquals(account.getUsername(),cart.getaccount().getUsername());

    }
    @Test
    public void testPersistAndLoadAddress() {
    	
    	Integer roleID = 5;
        Customer customer = new Customer();
        customer.setRoleID(roleID);
        customerRepository.save(customer);
        
        Account account= new Account();
        account.setAccountRole(customer);
        account.setName("Coco");
        account.setPassword("Pass");
        account.setUsername("cocho");
        account.setPointBalance(50);
        accountRepository.save(account);
    	
    	Integer addressID = 1000;
    	Integer buildingNO = 5;
    	String street = "Ave River";
    	String town = "Ocean";
    	Address address = new Address();
    	address.setAccount(account);
    	address.setAddressID(addressID);
    	address.setBuildingNo(buildingNO);
    	address.setStreet(street);
    	address.setTown(town);
    	addressRepository.save(address);
    	
    	address = null;
    	address = addressRepository.findByAddressID(addressID);
    	assertNotNull(address);
    	assertEquals(addressID, address.getAddressID());
    	assertEquals(buildingNO, address.getBuildingNo());
    	assertEquals(street, address.getStreet());
    	assertEquals(town, address.getTown());
    	assertEquals(account.getUsername(), address.getAccount().getUsername());
    }
    
    @Test
    public void testPersistAndLoadInStoreOrder() {

    	Integer roleID = 5;
        Customer customer = new Customer();
        customer.setRoleID(roleID);
        customerRepository.save(customer);
        
    	Account account= new Account();
        account.setAccountRole(customer);
        account.setName("Coco");
        account.setPassword("Pass");
        account.setUsername("cocho");
        account.setPointBalance(50);
        accountRepository.save(account);
        
        InStoreOrder inStoreOrder = new InStoreOrder();
        inStoreOrder.setAccount(account);
        
        Date date=Date.valueOf("2022-01-02");
        inStoreOrder.setDate(date);
        
        Set<Item> set = new HashSet<Item> (); 
        NonPerishableItem nonPerishable = new NonPerishableItem();
        Integer nonPerishableID = 1;
        nonPerishable.setItemID(nonPerishableID);
        nonPerishable.setAvailableOnline(true);
        nonPerishable.setNumInStock(1);
        nonPerishable.setPointPerItem(10);
        nonPerishable.setPrice((float) 50.0);
        nonPerishable.setProductName("Desk");
        nonPerishableItemRepository.save(nonPerishable);
        set.add(nonPerishable);
        inStoreOrder.setItems(set);
        
        inStoreOrder.setOrderID(90);
        
        Time time=Time.valueOf("14:02:03");
        inStoreOrder.setPurchaseTime(time);
        
        inStoreOrder.setTotalValue((float) 50.0);
        
        inStoreOrderRepository.save(inStoreOrder);
        
        inStoreOrder = null;
        inStoreOrder = inStoreOrderRepository.findByOrderID(90);
        assertNotNull(inStoreOrder);
        assertEquals(90,inStoreOrder.getOrderID());
        assertEquals((float) 50.0,inStoreOrder.getTotalValue());
        assertEquals(account.getUsername(), inStoreOrder.getAccount().getUsername());
        assertEquals(time,inStoreOrder.getPurchaseTime());
        assertEquals(date,inStoreOrder.getDate());
         
    }
    
    @Test
    public void testPersistAndLoadPickupOrder() {

    	Integer roleID = 5;
        Customer customer = new Customer();
        customer.setRoleID(roleID);
        customerRepository.save(customer);
        
    	Account account= new Account();
        account.setAccountRole(customer);
        account.setName("Coco");
        account.setPassword("Pass");
        account.setUsername("cocho");
        account.setPointBalance(50);
        accountRepository.save(account);
        
        PickUpOrder pickUpOrder = new PickUpOrder();
        pickUpOrder.setAccount(account);
        
        Date date=Date.valueOf("2022-01-02");
        pickUpOrder.setDate(date);
        
        Set<Item> set = new HashSet<Item> (); 
        NonPerishableItem nonPerishable = new NonPerishableItem();
        Integer nonPerishableID = 1;
        nonPerishable.setItemID(nonPerishableID);
        nonPerishable.setAvailableOnline(true);
        nonPerishable.setNumInStock(1);
        nonPerishable.setPointPerItem(10);
        nonPerishable.setPrice((float) 50.0);
        nonPerishable.setProductName("Desk");
        nonPerishableItemRepository.save(nonPerishable);
        set.add(nonPerishable);
        pickUpOrder.setItems(set);
        
        pickUpOrder.setOrderID(90);
        
        Time time=Time.valueOf("14:02:03");
        pickUpOrder.setPurchaseTime(time);
        
        pickUpOrder.setTotalValue((float) 50.0);
        
        Integer timeSlotID=25;
        Date startDate=Date.valueOf("2022-01-02");
        Date endDate=Date.valueOf("2022-01-02");
        Time startTime=Time.valueOf("14:02:03");
        Time endTime=Time.valueOf("15:00:00");

        TimeSlot timeSlot=new TimeSlot();
        timeSlot.setTimeSlotID(timeSlotID);
        timeSlot.setStartDate(startDate);
        timeSlot.setEndDate(endDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlotRepository.save(timeSlot);
        
        pickUpOrder.setTimeSlot(timeSlot);
        
        pickUpOrderRepository.save(pickUpOrder);
        
        pickUpOrder = null;
        pickUpOrder = pickUpOrderRepository.findByOrderID(90);
        assertNotNull(pickUpOrder);
        assertEquals(90,pickUpOrder.getOrderID());
        assertEquals((float) 50.0,pickUpOrder.getTotalValue());
        assertEquals(date,pickUpOrder.getDate());
        assertEquals(time,pickUpOrder.getPurchaseTime());
        assertEquals(timeSlot.getTimeSlotID(),pickUpOrder.getTimeSlot().getTimeSlotID());  
        assertEquals(account.getUsername(), pickUpOrder.getAccount().getUsername());
    }
    @Test
    public void testPersistAndLoadSchedule() {
        // Create cashier
        Integer roleID = 1;
        Cashier cashier = new Cashier();
        cashier.setRoleID(roleID);
        cashierRepository.save(cashier);

        // Create schedule
        Integer scheduleID = 1;
        Schedule schedule = new Schedule();
        schedule.setScheduleID(scheduleID);
        Set<WorkingHour> scheduleHours = new HashSet<WorkingHour>();
        schedule.setWorkingHour(scheduleHours);
        schedule.setEmployee(cashier);
        scheduleRepository.save(schedule);

        schedule = null;

        // Checking schedule and attributes
        schedule = scheduleRepository.findByScheduleID(scheduleID);
        assertNotNull(schedule);
        assertEquals(scheduleID, schedule.getScheduleID());
        assertEquals(cashier.getRoleID(), schedule.getEmployee().getRoleID());
    }

    @Test
    public void testPersistAndLoadWorkingHour() {
        
        // Create cashier
        Integer roleID = 1;
        Cashier cashier = new Cashier();
        cashier.setRoleID(roleID);
        cashierRepository.save(cashier);
        
        // Create working Hour
        Integer workingHourID = 1;
        DayOfWeek dayOfWeek = DayOfWeek.Tuesday;
        Time startTime = Time.valueOf("01:00:00");
        Time endTime = Time.valueOf("02:00:00");
        WorkingHour workingHour = new WorkingHour();
        
        // Add attributes
        workingHour.setWorkingHourID(workingHourID);
        workingHour.setDayOfWeek(dayOfWeek);
        workingHour.setStartTime(startTime);
        workingHour.setEndTime(endTime);
        workingHourRepository.save(workingHour);

        workingHour = null;

        workingHour = workingHourRepository.findByWorkingHourID(workingHourID);
        assertNotNull(workingHour);
        assertEquals(workingHourID, workingHour.getWorkingHourID());
        assertEquals(dayOfWeek, workingHour.getDayOfWeek());
        assertEquals(startTime, workingHour.getStartTime());
        assertEquals(endTime, workingHour.getEndTime());
    }
    @Test
    public void testPersistAndLoadDeliveryOrder() {

        Integer roleID = 5;
        Customer customer = new Customer();
        customer.setRoleID(roleID);
        customerRepository.save(customer);
        
        Account account= new Account();
        account.setAccountRole(customer);
        account.setName("Coco");
        account.setPassword("Pass");
        account.setUsername("cocho");
        account.setPointBalance(50);
        accountRepository.save(account);
        
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setAccount(account);
        
        Date date=Date.valueOf("2022-01-02");
        deliveryOrder.setDate(date);
        
        Set<Item> set = new HashSet<Item> (); 
        NonPerishableItem nonPerishable = new NonPerishableItem();
        Integer nonPerishableID = 1;
        nonPerishable.setItemID(nonPerishableID);
        nonPerishable.setAvailableOnline(true);
        nonPerishable.setNumInStock(1);
        nonPerishable.setPointPerItem(10);
        nonPerishable.setPrice((float) 50.0);
        nonPerishable.setProductName("Desk");
        nonPerishableItemRepository.save(nonPerishable);
        set.add(nonPerishable);
        deliveryOrder.setItems(set);
        
        deliveryOrder.setOrderID(90);
        
        Time time=Time.valueOf("14:02:03");
        deliveryOrder.setPurchaseTime(time);
        
        deliveryOrder.setTotalValue((float) 50.0);
        
        Integer timeSlotID=25;
        Date startDate=Date.valueOf("2022-01-02");
        Date endDate=Date.valueOf("2022-01-02");
        Time startTime=Time.valueOf("14:02:03");
        Time endTime=Time.valueOf("15:00:00");

        TimeSlot timeSlot=new TimeSlot();
        timeSlot.setTimeSlotID(timeSlotID);
        timeSlot.setStartDate(startDate);
        timeSlot.setEndDate(endDate);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(endTime);
        timeSlotRepository.save(timeSlot);
        
        deliveryOrder.setTimeSlot(timeSlot);
        
        deliveryOrderRepository.save(deliveryOrder);
        
        deliveryOrder = null;
        deliveryOrder = deliveryOrderRepository.findByOrderID(90);
        assertNotNull(deliveryOrder);
        assertEquals(90,deliveryOrder.getOrderID());
        assertEquals((float) 50.0,deliveryOrder.getTotalValue());
        assertEquals(date,deliveryOrder.getDate());
        assertEquals(time,deliveryOrder.getPurchaseTime());
        assertEquals(timeSlot.getTimeSlotID(),deliveryOrder.getTimeSlot().getTimeSlotID());  
        assertEquals(account.getUsername(), deliveryOrder.getAccount().getUsername());
    }
 
}
