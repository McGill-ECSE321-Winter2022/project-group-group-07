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
 import org.springframework.test.context.junit.jupiter.SpringExtension;

 import ca.mcgill.ecse321.grocerystore.model.Account;
 import ca.mcgill.ecse321.grocerystore.model.BusinessHour;
 import ca.mcgill.ecse321.grocerystore.model.Cart;
 import ca.mcgill.ecse321.grocerystore.model.Cashier;
 import ca.mcgill.ecse321.grocerystore.model.Clerk;
 import ca.mcgill.ecse321.grocerystore.model.Customer;
 import ca.mcgill.ecse321.grocerystore.model.DeliveryPerson;
 import ca.mcgill.ecse321.grocerystore.model.Owner;
 import ca.mcgill.ecse321.grocerystore.model.Report;
 import ca.mcgill.ecse321.grocerystore.model.Store;
 import ca.mcgill.ecse321.grocerystore.model.Terminal;
 import ca.mcgill.ecse321.grocerystore.model.TimeSlot;

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
 	public void clearDatabase(){
 		//deleting dependent classes first to avoid null values in non null columns
 		cartRepository.deleteAll();
         reportRepository.deleteAll();
         businessHourRepository.deleteAll();
         storeRepository.deleteAll();
         terminalRepository.deleteAll();
         orderRepository.deleteAll();
         timeSlotRepository.deleteAll();
         itemRepository.deleteAll();
         addressRepository.deleteAll();
         accountRepository.deleteAll();
         workingHourRepository.deleteAll();
         scheduleRepository.deleteAll();
         accountRoleRepository.deleteAll();
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
 		assertEquals(roleID,customer.getRoleID());
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
 		assertEquals(roleID,cashier.getRoleID());
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
 		assertEquals(roleID,clerk.getRoleID());
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
 		assertEquals(roleID,deliveryPerson.getRoleID());
 	}
 	@Test
 	public void testPersistAndLoadOwner() {
 		Integer roleID = 1;
 		Date date =  Date.valueOf("2022-01-01");
 		Owner owner = new Owner();
 		owner.setRoleID(roleID);
 		owner.setEmploymentDate(date);
 		ownerRepository.save(owner);
		
 		owner = null;
		
 		owner = ownerRepository.findByRoleID(roleID);
 		assertNotNull(owner);
 		assertEquals(roleID,owner.getRoleID());
 		assertEquals(date,owner.getEmploymentDate());
 	}
	


 	@Test
     public void testPersistAndLoadBusinessHour() {
		
         Integer storeID =1;
         String name = "BreadnBake";
         String address = "1 ave";
         String phoneNumber = "555-555-5555";
         String email = "john@yahoo.com";
         Integer employeeDiscount = 20;
         Float pointToCash = (float) 1000.0;
         Store store = new Store();
         store.setStoreID(storeID);
         store.setAddress(address);
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
         assertEquals(businessHourId,businessHour.getBusinessHourId());
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
         assertEquals(terminalID,terminal.getTerminalID());
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
         assertEquals(reportID,report.getReportID());
     }

 }
