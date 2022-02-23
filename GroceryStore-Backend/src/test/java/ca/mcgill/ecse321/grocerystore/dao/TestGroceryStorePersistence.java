//package ca.mcgill.ecse321.grocerystore.dao;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.Month;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import ca.mcgill.ecse321.grocerystore.model.Customer;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class TestGroceryStorePersistence {
//	
//	@Autowired
//	private AccountRepository accountRepository;
//	@Autowired
//	private AccountRoleRepository accountRoleRepository;
//	@Autowired
//	private AddressRepository addressRepository;
//	@Autowired
//	private BusinessHourRepository businessHourRepository;
//	@Autowired
//	private CartRepository cartRepository;
//	@Autowired
//	private CashierRepository cashierRepository;
//	@Autowired
//	private ClerkRepository clerkRepository;
//	@Autowired
//	private CustomerRepository customerRepository;
//	@Autowired
//	private DeliveryOrderRepository deliveryOrderRepository;
//	@Autowired
//	private DeliveryPersonRepository deliveryPersonRepository;
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	@Autowired
//	private InStoreOrderRepository inStoreOrderRepository;
//	@Autowired
//	private ItemRepository itemRepository;
//	@Autowired
//	private NonPerishableItemRepository nonPerishableItemRepository;
//	@Autowired
//	private OrderRepository orderRepository;
//	@Autowired
//	private OwnerRepository ownerRepository;
//	@Autowired
//	private PerishableItemRepository perishableItemRepository;
//	@Autowired
//	private PickUpOrderRepository pickUpOrderRepository;
//	@Autowired
//	private ReportRepository reportRepository;
//	@Autowired
//	private ScheduleRepository scheduleRepository;
//	@Autowired
//	private StoreRepository storeRepository;
//	@Autowired
//	private TerminalRepository terminalRepository;
//	@Autowired
//	private TimeSlotRepository timeSlotRepository;
//	@Autowired
//	private WorkingHourRepository workingHourRepository;
	
//	@AfterEach
//	public void clearDatabase(){
//		//deleting dependent classes first to avoid null values in non null columns
//		cartRepository.deleteAll();
//		reportRepository.deleteAll();
//		businessHourRepository.deleteAll();
//		storeRepository.deleteAll();
//		terminalRepository.deleteAll();
//		orderRepository.deleteAll();
//		timeSlotRepository.deleteAll();
//		itemRepository.deleteAll();
//		addressRepository.deleteAll();
//		accountRepository.deleteAll();
//		workingHourRepository.deleteAll();
//		scheduleRepository.deleteAll();
//		accountRoleRepository.deleteAll();
//	}
//	
//	@Test
//	public void testPersistAndLoadCustomer() {
//		
//		Integer roleID = 1;
//		Customer customer = new Customer();
//		customer.setRoleID(roleID);
//		customerRepository.save(customer);
//		
//		customer = null;
//		
//		customer = customerRepository.findByRoleID(roleID);
//		assertNotNull(customer);
//		assertEquals(roleID,customer.getRoleID());
//	}
//
//
//	
//}
