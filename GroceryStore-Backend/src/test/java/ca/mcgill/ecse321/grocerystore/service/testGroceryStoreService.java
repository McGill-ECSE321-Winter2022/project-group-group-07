package ca.mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.AccountRepository;
import ca.mcgill.ecse321.grocerystore.dao.AccountRoleRepository;
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
import ca.mcgill.ecse321.grocerystore.model.Address;
import ca.mcgill.ecse321.grocerystore.model.BusinessHour;
import ca.mcgill.ecse321.grocerystore.model.Cart;
import ca.mcgill.ecse321.grocerystore.model.Cashier;
import ca.mcgill.ecse321.grocerystore.model.Clerk;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.DeliveryPerson;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DayOfWeek;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.ItemCategory;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.Owner;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.model.Report;
import ca.mcgill.ecse321.grocerystore.model.Schedule;
import ca.mcgill.ecse321.grocerystore.model.Store;
import ca.mcgill.ecse321.grocerystore.model.Terminal;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.model.WorkingHour;

@ExtendWith(MockitoExtension.class)
public class testGroceryStoreService {
	@Mock
	private AccountRepository accountDao;
	@Mock
	private AccountRoleRepository accountRoleDao;
	@Mock
	private OwnerRepository ownerDao;
	@Mock
	private CustomerRepository customerDao;
	@Mock
	private CashierRepository cashierDao;
	@Mock
	private ClerkRepository clerkDao;
	@Mock
	private DeliveryPersonRepository deliveryPersonDao;
	@Mock
	private StoreRepository storeDao;
	@Mock
	private BusinessHourRepository businessHourDao;
	@Mock
	private PerishableItemRepository perishableItemDao;
	@Mock
	private NonPerishableItemRepository nonPerishableItemDao;
	@Mock
	private AddressRepository addressDao;
	@Mock
	private WorkingHourRepository workingHourDao;
	@Mock
	private ScheduleRepository scheduleDao;
	@Mock
	private CartRepository cartDao;
	@Mock
	private ReportRepository reportDao;
	@Mock
	private OrderRepository orderDao;
	@Mock
	private DeliveryOrderRepository deliveryOrderDao;
	@Mock
	private PickUpOrderRepository PickUpOrderDao;
	@Mock
	private InStoreOrderRepository InStoreOrderDao;
	@Mock
	private TimeSlotRepository timeSlotDao;
	@Mock
	private TerminalRepository terminalDao;

	@InjectMocks
	private GroceryStoreService service;
	private static final String Account_KEY = "TestAccount";
	private static final String Account_Password = "123";
	private static final String Account_Name = "MARK";

	private static final Long PerishableItem_ID = 1L;
	private static final String PerishableItem_name = "Apple";
	private static final Float PerishableItem_price = (float) 2.5;
	private static final Boolean PerishableItem_availableOnline = false;
	private static final Integer PerishableItem_numInStock = 10;
	private static final Integer PerishableItem_pointPerItem = 2;

	private static final Long NonPerishableItem_ID = 2L;
	private static final String NonPerishableItem_name = "Desk";
	private static final Float NonPerishableItem_price = (float) 20.0;
	private static final Boolean NonPerishableItem_availableOnline = true;
	private static final Integer NonPerishableItem_numInStock = 5;
	private static final Integer NonPerishableItem_pointPerItem = 10;

	private static final Long address_ID = 1L;
	private static final Integer address_buildingNo = 200;
	private static final String street = "testStreet";
	private static final String town = "testTown";

	private static final Long terminal_ID = 1L;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(accountDao.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(Account_KEY)) {
				Account account = new Account();
				Employee employee = new Cashier();
				employee.setRoleID(1L);
				employee.setEmploymentDate(Date.valueOf("2020-2-2"));
				account.setName(Account_Name);
				account.setUsername(Account_KEY);
				account.setPassword(Account_Password);
				account.setPointBalance(0);
				account.setAccountRole(employee);
				return account;
			} else {
				return null;
			}
		});
		lenient().when(scheduleDao.findByEmployee(any(Employee.class))).thenAnswer((InvocationOnMock invocation) -> {
			Schedule schedule = new Schedule();
			schedule.setScheduleID(1L);
			schedule.setEmployee(invocation.getArgument(0));
			Set<WorkingHour> whs = new HashSet<WorkingHour>();
			WorkingHour wh = new WorkingHour();
			wh.setDayOfWeek(DayOfWeek.Monday);
			whs.add(wh);
			schedule.setWorkingHour(whs);
			return schedule;
		});
		lenient().when(accountDao.findByUsernameAndPassword(anyString(), anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals("Testing") && invocation.getArgument(1).equals("Test")) {
						Account account = new Account();
						Customer customer = new Customer();
						customer.setRoleID(1L);
						account.setName(Account_KEY);
						account.setUsername("Testing");
						account.setPassword("Test");
						account.setAccountRole(customer);
						return account;
					} else {
						return null;
					}
				});
		lenient().when(addressDao.findByAccount(any(Account.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (((Account) invocation.getArgument(0)).getUsername().equals(Account_KEY)) {
				Address address = new Address();
				address.setAddressID(address_ID);
				address.setBuildingNo(address_buildingNo);
				address.setStreet(street);
				address.setTown(town);

				Account account = new Account();
				account.setName(Account_KEY);
				account.setUsername(Account_KEY);
				address.setAccount(account);

				return address;
			} else {
				return null;
			}
		});
		lenient().when(businessHourDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			BusinessHour b1 = new BusinessHour();
			BusinessHour b2 = new BusinessHour();
			b1.setBusinessHourID(1L);
			b1.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Monday);
			b2.setBusinessHourID(2L);
			b2.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Tuesday);
			List<BusinessHour> days = new ArrayList<>();
			days.add(b1);
			days.add(b2);
			return days;
		});
		lenient().when(workingHourDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			WorkingHour wh1 = new WorkingHour();
			WorkingHour wh2 = new WorkingHour();
			wh1.setWorkingHourID(1L);
			wh1.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Monday);
			wh2.setWorkingHourID(2L);
			wh2.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Tuesday);
			List<WorkingHour> days = new ArrayList<>();
			days.add(wh1);
			days.add(wh2);
			return days;
		});
		lenient().when(perishableItemDao.findByItemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(PerishableItem_ID)) {
				PerishableItem pitem = new PerishableItem();
				pitem.setItemID(PerishableItem_ID);
				pitem.setProductName(PerishableItem_name);
				pitem.setPrice(PerishableItem_price);
				pitem.setAvailableOnline(PerishableItem_availableOnline);
				pitem.setNumInStock(PerishableItem_numInStock);
				pitem.setPointPerItem(PerishableItem_pointPerItem);
				return pitem;
			} else {
				return null;
			}
		});
		lenient().when(perishableItemDao.findByProductName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(PerishableItem_name)) {
				List<PerishableItem> plist = new ArrayList<PerishableItem>();
				PerishableItem pitem = new PerishableItem();
				pitem.setItemID(PerishableItem_ID);
				pitem.setProductName(PerishableItem_name);
				pitem.setPrice(PerishableItem_price);
				pitem.setAvailableOnline(PerishableItem_availableOnline);
				pitem.setNumInStock(PerishableItem_numInStock);
				pitem.setPointPerItem(PerishableItem_pointPerItem);
				plist.add(pitem);

				PerishableItem pitem2 = new PerishableItem();
				pitem2.setItemID(2L);
				pitem2.setProductName(PerishableItem_name);
				pitem2.setPrice(PerishableItem_price);
				pitem2.setAvailableOnline(PerishableItem_availableOnline);
				pitem2.setNumInStock(PerishableItem_numInStock);
				pitem2.setPointPerItem(PerishableItem_pointPerItem);
				plist.add(pitem2);
				return plist;
			} else {
				return null;
			}
		});
		lenient().when(nonPerishableItemDao.findByItemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(NonPerishableItem_ID)) {
				NonPerishableItem npitem = new NonPerishableItem();
				npitem.setItemID(NonPerishableItem_ID);
				npitem.setProductName(NonPerishableItem_name);
				npitem.setPrice(NonPerishableItem_price);
				npitem.setAvailableOnline(NonPerishableItem_availableOnline);
				npitem.setNumInStock(NonPerishableItem_numInStock);
				npitem.setPointPerItem(NonPerishableItem_pointPerItem);
				return npitem;
			} else {
				return null;
			}
		});
		lenient().when(nonPerishableItemDao.findByProductName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(NonPerishableItem_name)) {
						List<NonPerishableItem> nplist = new ArrayList<NonPerishableItem>();
						NonPerishableItem npitem = new NonPerishableItem();
						npitem.setItemID(NonPerishableItem_ID);
						npitem.setProductName(NonPerishableItem_name);
						npitem.setPrice(NonPerishableItem_price);
						npitem.setAvailableOnline(NonPerishableItem_availableOnline);
						npitem.setNumInStock(NonPerishableItem_numInStock);
						npitem.setPointPerItem(NonPerishableItem_pointPerItem);
						nplist.add(npitem);
						return nplist;
					} else {
						return null;
					}
				});
		lenient().when(reportDao.findByReportID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {

			Report report = new Report();
			Calendar c = Calendar.getInstance();
			c.set(2001, Calendar.JULY, 17);
			report.setReportID(1L);
			report.setOrders(null);
			Date startDate = new Date(c.getTimeInMillis());
			Date endDate = new Date(c.getTimeInMillis());
			report.setEndDate(endDate);
			report.setStartDate(startDate);
			report.setTotalValue((float) 200.0);
			return report;

		});

		lenient().when(perishableItemDao.findByItemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(PerishableItem_ID)) {
				PerishableItem pitem = new PerishableItem();
				pitem.setItemID(PerishableItem_ID);
				pitem.setProductName(PerishableItem_name);
				pitem.setPrice(PerishableItem_price);
				pitem.setAvailableOnline(PerishableItem_availableOnline);
				pitem.setNumInStock(PerishableItem_numInStock);
				pitem.setPointPerItem(PerishableItem_pointPerItem);
				return pitem;
			} else {
				return null;
			}
		});
		lenient().when(perishableItemDao.findByProductName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(PerishableItem_name)) {
				List<PerishableItem> plist = new ArrayList<PerishableItem>();
				PerishableItem pitem = new PerishableItem();
				pitem.setItemID(PerishableItem_ID);
				pitem.setProductName(PerishableItem_name);
				pitem.setPrice(PerishableItem_price);
				pitem.setAvailableOnline(PerishableItem_availableOnline);
				pitem.setNumInStock(PerishableItem_numInStock);
				pitem.setPointPerItem(PerishableItem_pointPerItem);
				plist.add(pitem);

				PerishableItem pitem2 = new PerishableItem();
				pitem2.setItemID(2L);
				pitem2.setProductName(PerishableItem_name);
				pitem2.setPrice(PerishableItem_price);
				pitem2.setAvailableOnline(PerishableItem_availableOnline);
				pitem2.setNumInStock(PerishableItem_numInStock);
				pitem2.setPointPerItem(PerishableItem_pointPerItem);
				plist.add(pitem2);
				return plist;
			} else {
				return null;
			}
		});
		lenient().when(nonPerishableItemDao.findByItemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(NonPerishableItem_ID)) {
				NonPerishableItem npitem = new NonPerishableItem();
				npitem.setItemID(NonPerishableItem_ID);
				npitem.setProductName(NonPerishableItem_name);
				npitem.setPrice(NonPerishableItem_price);
				npitem.setAvailableOnline(NonPerishableItem_availableOnline);
				npitem.setNumInStock(NonPerishableItem_numInStock);
				npitem.setPointPerItem(NonPerishableItem_pointPerItem);
				return npitem;
			} else {
				return null;
			}
		});
		lenient().when(nonPerishableItemDao.findByProductName(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(NonPerishableItem_name)) {
						List<NonPerishableItem> nplist = new ArrayList<NonPerishableItem>();
						NonPerishableItem npitem = new NonPerishableItem();
						npitem.setItemID(NonPerishableItem_ID);
						npitem.setProductName(NonPerishableItem_name);
						npitem.setPrice(NonPerishableItem_price);
						npitem.setAvailableOnline(NonPerishableItem_availableOnline);
						npitem.setNumInStock(NonPerishableItem_numInStock);
						npitem.setPointPerItem(NonPerishableItem_pointPerItem);
						npitem.setCategory(ItemCategory.Furniture);
						nplist.add(npitem);
						return nplist;
					} else {
						return null;
					}
				});
		lenient().when(cartDao.findByAccount(any(Account.class))).thenAnswer((InvocationOnMock invocation) -> {
			if (((Account) invocation.getArgument(0)).getUsername().equals(Account_KEY)) {
				Cart cart = new Cart();
				cart.setAccount(new Account());
				cart.setCartID(1L);
				cart.setItems(new HashSet<Item>());
				cart.setTotalValue(0f);
				cart.setNumOfItems(0);
				cart.setOrderType(OrderType.Delivery);
				cart.setTimeSlot(new TimeSlot());
				cart.getaccount();
				cart.getCartID();
				cart.getItems();
				cart.getTotalValue();
				cart.getNumOfItems();
				cart.getOrderType();
				cart.getTimeSlot();
				return cart;
			} else {
				return null;
			}
		});
		lenient().when(addressDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<Address> addresses = new HashSet<Address>();
			return addresses;
		});
		lenient().when(accountDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<Account> accounts = new HashSet<Account>();
			return accounts;
		});
		lenient().when(businessHourDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			BusinessHour b1 = new BusinessHour();
			BusinessHour b2 = new BusinessHour();
			b1.setBusinessHourID(1L);
			b1.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Monday);
			b2.setBusinessHourID(2L);
			b2.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Tuesday);
			List<BusinessHour> days = new ArrayList<>();
			days.add(b1);
			days.add(b2);
			return days;
		});
		lenient().when(timeSlotDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<TimeSlot> slots = new HashSet<TimeSlot>();
			return slots;
		});
		lenient().when(terminalDao.findByTerminalID(terminal_ID)).thenAnswer((InvocationOnMock invocation) -> {
			Terminal terminal = new Terminal();
			terminal.setTerminalID(terminal_ID);
			return terminal;
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(ownerDao.save(any(Owner.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(clerkDao.save(any(Clerk.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(cashierDao.save(any(Cashier.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(deliveryPersonDao.save(any(DeliveryPerson.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(addressDao.save(any(Address.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(perishableItemDao.save(any(PerishableItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(nonPerishableItemDao.save(any(NonPerishableItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(cartDao.save(any(Cart.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(timeSlotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(terminalDao.save(any(Terminal.class))).thenAnswer(returnParameterAsAnswer);

	}

	// account

	@Test
	public void testCreateAccount() {
		assertEquals(0, service.getAllAccounts().size());

		String name = "Lello";
		Account account = null;
		Customer customer = service.createCustomerRole();
		try {
			account = service.createAccount("lelele", "batata", name, 0, customer);
		} catch (IllegalArgumentException e) {
			if (e.getMessage().equals("No such account to be found! ")) {

			} else {
				fail();
			}
		}
		assertNotNull(account);
		assertEquals(name, account.getName());
	}

	@Test
	public void testCreateAllNullParameters() {
		String name = null;
		String error = null;
		Account account = null;
		Customer customer = service.createCustomerRole();
		try {
			account = service.createAccount(null, null, name, 0, customer);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(account);
		// check error
		assertEquals(
				"Account username cannot be empty! Your password must at least be 6 characters long! Account name cannot be empty!",
				error);
	}

	@Test
	public void testCreateAccountAllInvalidParameters() {
		String name = "";
		String error = "";
		Account account = null;
		Customer customer = service.createCustomerRole();
		try {
			account = service.createAccount("", "", name, 0, customer);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(account);
		// check error
		assertEquals(
				"Account username cannot be empty! Your password must at least be 6 characters long! Account name cannot be empty!",
				error);
	}

	@Test
	public void testGetAccount() {
		Account account = null;
		try {
			account = service.getAccount(Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(account);
		assertEquals(Account_Name, account.getName());
	}

	@Test

	public void testGetAccountNull() {
		Account account = null;
		String error = "";
		try {
			account = service.getAccount(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(account);
		assertEquals("Invalid username! ", error);
	}

	@Test

	public void testGetAccountBlankUsername() {
		Account account = null;
		String error = "";
		try {
			account = service.getAccount("");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(account);
		assertEquals("Invalid username! ", error);
	}

	@Test

	public void testGetAccountInvalidUsername() {
		Account account = null;
		String error = "";
		try {
			account = service.getAccount("LOL");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(account);
		assertEquals("No such account to be found! ", error);
	}

//	@Test
//
//	public void testUpdateAccountPassword() {
//		Customer customer = service.createCustomerRole();
//		Account account = service.createAccount("TestingPass", "Test123456", "TestAccount", 0, customer);
//		Account updatedAccount = null;
//		String error = "";
//		try {
//			updatedAccount = service.updatePassword("TestingPass", "Test123456", "NewTest");
//		} catch (IllegalArgumentException e) {
//			error = e.getMessage();
//			fail();
//		}
//
//		assertNotNull(updatedAccount);
//		assertEquals("Test123456", account.getPassword());
//		assertEquals("NewTest", updatedAccount.getPassword());
//		assertEquals(account.getName(), updatedAccount.getName());
//		assertEquals(account.getUsername(), updatedAccount.getUsername());
//	}

	@Test

	public void testUpdateAccountInvalidPassword() {
		String error = "";
		Account updatedAccount = null;
		try {
			updatedAccount = service.updatePassword("Testing", "Test", "NewTe");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(updatedAccount);
		assertEquals("Your password must at least be 6 characters long.", error);
	}

	public void testUpdateAccountNullPassword() {
		String error = "";
		Account updatedAccount = null;
		try {
			updatedAccount = service.updatePassword("Testing", "Test", null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(updatedAccount);
		assertEquals("Your password must at least be 6 characters long.", error);
	}

	public void testUpdateAccountInvalidUsername() {
		String error = "";
		Account updatedAccount = null;
		try {
			updatedAccount = service.updatePassword("Test", "Test", "NewTest");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(updatedAccount);
		assertEquals("Wrong Username or Password.", error);
	}

//	@Test
//
//	public void testUpdateAccountName() {
//		Customer customer = service.createCustomerRole();
//		Account account = service.createAccount(Account_KEY, "Test", "TestAccount", 0, customer);
//		Account updatedAccount = null;
//		try {
//			updatedAccount = service.updateName(Account_KEY, "TestAccount2");
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		assertNotNull(updatedAccount);
//		assertEquals("TestAccount2", updatedAccount.getName());
//		assertEquals("TestAccount", account.getName());
//		assertEquals(account.getUsername(), updatedAccount.getUsername());
//	}

	@Test
	public void testUpdateAccountNameWithNull() {
		String error = "";
		Account updatedAccount = null;
		try {
			updatedAccount = service.updateName(Account_KEY, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(updatedAccount);
		assertEquals("Your name cannot be blank.", error);
	}

	@Test
	public void testUpdateAccountNameInvalidName() {
		String error = "";
		Account updatedAccount = null;
		try {
			updatedAccount = service.updateName(Account_KEY, "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(updatedAccount);
		assertEquals("Your name cannot be blank.", error);
	}

	@Test
	public void testUpdateAccountNameInvalidUsername() {
		String error = "";

		Account updatedAccount = null;
		try {
			updatedAccount = service.updateName("", "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(updatedAccount);
		assertEquals("Your name cannot be blank.", error);
	}

	@Test
	public void testCreateOwnerRole() {
		Owner owner = null;
		owner = service.createOwnerRole();

		assertNotNull(owner);
		assertEquals("Owner", owner.toString());
	}

	@Test
	public void testCreateCashierRole() {
		Cashier cashier = null;
		cashier = service.createCashierRole();

		assertNotNull(cashier);
		assertEquals("Cashier, Employment Date: " + cashier.getEmploymentDate(), cashier.toString());
	}

	@Test
	public void testCreateClerkRole() {
		Clerk clerk = null;
		clerk = service.createClerkRole();

		assertNotNull(clerk);
		assertEquals("Clerk, Employment Date: " + clerk.getEmploymentDate(), clerk.toString());
	}

	@Test
	public void testCreateCustomerRole() {
		Customer customer = null;
		customer = service.createCustomerRole();

		assertNotNull(customer);
		assertEquals("Customer", customer.toString());
	}

	@Test
	public void testCreateDeliveryPersonRole() {
		DeliveryPerson deliveryPerson = null;
		deliveryPerson = service.createDeliveryPersonRole();

		assertNotNull(deliveryPerson);
		assertEquals("DeliveryPerson, Employment Date: " + deliveryPerson.getEmploymentDate(),
				deliveryPerson.toString());
	}

	@Test
	public void testGetAllCustomers() {
		List<Account> customers = new ArrayList<Account>();
		try {
			customers = service.getAllCustomers();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(customers);
	}

	@Test
	public void testGetAllCashiers() {
		List<Account> cashiers = new ArrayList<Account>();
		try {
			cashiers = service.getAllCashiers();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(cashiers);
	}

	@Test
	public void testGetAllClerks() {
		List<Account> clerks = new ArrayList<Account>();
		try {
			clerks = service.getAllClerks();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(clerks);
	}

	@Test
	public void testGetAllDeliveryPersons() {
		List<Account> deliveryPersons = new ArrayList<Account>();
		try {
			deliveryPersons = service.getAllDeliveryPersons();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(deliveryPersons);
	}

	@Test
	public void testGetAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			accounts = service.getAllAccounts();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(accounts);
	}

	@Test
	public void testGetAllEmployees() {
		List<Account> employees = new ArrayList<Account>();
		try {
			employees = service.getAllEmployees();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(employees);
	}

	// Test for Address

	@Test
	public void testCreateAddress() {
		Account testAccount = new Account();
		testAccount.setName(Account_KEY);
		Address testAddress = null;
		try {
			testAddress = service.createAddress(address_buildingNo, street, town, testAccount);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(testAddress);
		assertEquals(address_buildingNo, testAddress.getBuildingNo());
		assertEquals(street, testAddress.getStreet());
		assertEquals(town, testAddress.getTown());
		assertEquals(Account_KEY, testAddress.getAccount().getName());
	}

	@Test
	public void testNullAddress() {

		String error = null;

		Address testAddress = null;
		try {
			testAddress = service.createAddress(null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(testAddress);
		assertEquals("Invalid building number! Street cannot be empty! Town cannot be empty! Account cannot be empty!",
				error);
	}

	@Test
	public void testEmptyAddress() {

		String error = null;

		Address testAddress = null;
		try {
			testAddress = service.createAddress(-10, "", "", null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(testAddress);
		assertEquals("Invalid building number! Street cannot be empty! Town cannot be empty! Account cannot be empty!",
				error);
	}

	@Test
	public void testGetAddressByUsername() {
		Address address = null;

		try {
			address = service.getAddressByUsername(Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(address);
		assertEquals(address.getAccount().getUsername(), Account_KEY);

	}

	@Test
	public void testGetAddressWithInvalidUsername() {
		Address address = null;
		String error = "";
		try {
			address = service.getAddressByUsername(null);
		} catch (IllegalArgumentException e) {
			error += e.getMessage();
		}

		assertNull(address);
		assertEquals(error, "Invalid username! ");

	}

	@Test
	public void testGetAllAddresses() {
		List<Address> addresses = null;

		try {
			addresses = service.getAllAddresses();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(addresses);
	}

	@Test
	public void testUpdateAddress() {
		Address address = null;

		Integer newBuildingNo = 200;
		String newStreet = "newStreet";
		String newTown = "newStreet";

		try {
			address = service.updateAddress(Account_KEY, newBuildingNo, newStreet, newTown);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(address);
		assertEquals(newBuildingNo, address.getBuildingNo());
		assertEquals(newStreet, address.getStreet());
		assertEquals(newTown, address.getTown());
		assertEquals(Account_KEY, address.getAccount().getUsername());
	}

	@Test
	public void testUpdateAddressWithInvalidInputs() {
		Address address = null;
		String error = "";

		Integer newBuildingNo = -10;
		String newStreet = "";
		String newTown = null;

		try {
			address = service.updateAddress(Account_KEY, newBuildingNo, newStreet, newTown);
		} catch (IllegalArgumentException e) {
			error += e.getMessage();
		}
		assertNull(address);
		assertEquals("Invalid building number! Street cannot be empty! Town cannot be empty!", error);
	}

	@Test
	public void testDeleteAddressByAccount() {
		Address address = null;

		try {
			address = service.deleteAddressByAccount(Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(address);
	}

	@Test
	public void testDeleteAddressOfNonExistingAccount() {
		Address address = null;
		String error = "";
		try {
			address = service.deleteAddressByAccount("");
		} catch (IllegalArgumentException e) {
			error += e.getMessage();
		}
		assertNull(address);
		assertEquals(error, "Invalid username! ");
	}

	// Store

	@Test
	public void testCreateStore() {
		assertNull(service.getStore());

		String name = "Store";
		String address = "123 test street";
		String phoneNumber = "666";
		String email = "tes@example.ex";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5.5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(store);
		assertEquals(name, store.getName());
		assertEquals(address, store.getAddress());
		assertEquals(phoneNumber, store.getPhoneNumber());
		assertEquals(email, store.getEmail());
		assertEquals(employeeDiscountRate, store.getEmployeeDiscountRate());
		assertEquals(pointToCashRatio, store.getPointToCashRatio());
		assertNull(error);
	}

	@Test
	public void testCreateStoreNull() {
		assertNull(service.getStore());

		String name = null;
		String address = null;
		String phoneNumber = null;
		String email = null;
		Integer employeeDiscountRate = null;
		Float pointToCashRatio = null;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals(
				"Store name cannot be empty! Store address time cannot be empty! Store phone number cannot be empty! Store email cannot be empty! Employee discount rate cannot be empty! Point to cash ratio cannot be empty!",
				error);
	}

	@Test
	public void testCreateStoreEmpty() {
		assertNull(service.getStore());
		String name = "";
		String address = "";
		String phoneNumber = "";
		String email = "";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals(
				"Store name cannot be empty! Store address time cannot be empty! Store phone number cannot be empty! Store email cannot be empty!",
				error);
	}

	@Test
	public void testCreateStoreSpace() {
		assertNull(service.getStore());
		String name = " ";
		String address = " ";
		String phoneNumber = " ";
		String email = " ";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals(
				"Store name cannot be empty! Store address time cannot be empty! Store phone number cannot be empty! Store email cannot be empty!",
				error);
	}

	@Test
	public void testCreateStoreIllegalDiscount() {
		assertNull(service.getStore());
		String name = "Store";
		String address = "123 test street";
		String phoneNumber = "666";
		String email = "tes@example.ex";
		Integer employeeDiscountRate = 200;
		Float pointToCashRatio = 5.5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals("Employee discount rate must be between 0 and 100!", error);
	}

	@Test
	public void testUpdateStore() {
		Store store = new Store();
		store.setStoreID(123L);
		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));

		String name = "Store";
		String address = "123 test street";
		String phoneNumber = "666";
		String email = "tes@example.ex";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5.5f;
		String error = null;
		Store testStore = null;

		try {
			testStore = service.updateStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(testStore);
		assertEquals(name, testStore.getName());
		assertEquals(address, testStore.getAddress());
		assertEquals(phoneNumber, testStore.getPhoneNumber());
		assertEquals(email, testStore.getEmail());
		assertEquals(employeeDiscountRate, testStore.getEmployeeDiscountRate());
		assertEquals(pointToCashRatio, testStore.getPointToCashRatio());
		assertNull(error);
	}

	@Test
	public void testUpdateStoreWithInvalidInputs() {
		Store store = new Store();
		store.setStoreID(123L);
		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));

		String name = null;
		String address = null;
		String phoneNumber = null;
		String email = null;
		Integer employeeDiscountRate = null;
		Float pointToCashRatio = null;
		Store testStore = null;
		try {
			testStore = service.createStore(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			
		}
		assertNull(testStore);
	}

	@Test
	public void testGetExistingStore() {
		Store store = new Store();
		store.setStoreID(123L);
		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));

		assertEquals(123, service.getStore().getStoreID());
	}

	@Test
	public void testGetNonExistingStore() {
		assertNull(service.getStore());
	}

	@Test
	public void testDeleteStore() {

		Store store = new Store();
		store.setStoreID(123L);
		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));

		assertNotNull(service.deleteStore());
	}

	// BusinessHour

	@Test
	public void testcreateBusinessHour() {
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = GroceryStoreSoftwareSystem.DayOfWeek.Monday;
		Time startTime = Time.valueOf("7:59:59");
		Time endTime = Time.valueOf("23:59:59");
		BusinessHour businessHour = null;
		String error = null;
		try {
			businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(businessHour);
		assertEquals(dayOfWeek, businessHour.getDayOfWeek());
		assertEquals(startTime, businessHour.getStartTime());
		assertEquals(endTime, businessHour.getEndTime());
		assertNull(error);
	}

	@Test
	public void testcreateBusinessHourNull() {
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = null;
		Time startTime = null;
		Time endTime = null;
		BusinessHour businessHour = null;
		String error = null;
		try {
			businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(businessHour);
		assertEquals(
				"Business hour day of the week cannot be empty! Business hour start time cannot be empty! Business hour end time cannot be empty!",
				error);
	}

	@Test
	public void testcreateBusinessHourEndTimeBeforeStartTime() {
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = GroceryStoreSoftwareSystem.DayOfWeek.Monday;
		Time startTime = Time.valueOf("17:59:59");
		Time endTime = Time.valueOf("3:59:59");
		BusinessHour businessHour = null;
		String error = null;
		try {
			businessHour = service.createBusinessHour(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(businessHour);
		assertEquals("Business hour end time cannot be before Business hour start time!", error);
	}

	@Test
	public void testGetAllBusinessHours() {
		assertNotNull(service.getAllBusinessHours());
	}

	@Test
	public void testGetExistingBusinessHours() {
		assertEquals(service.getBusinessHourByDay(GroceryStoreSoftwareSystem.DayOfWeek.Monday).getDayOfWeek(),
				GroceryStoreSoftwareSystem.DayOfWeek.Monday);
	}

	@Test
	public void testGetBusinessHoursNull() {
		String error = null;
		try {
			service.getBusinessHourByDay(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Day of week cannot be empty!", error);
	}

	@Test
	public void testDeleteBusinessHourByDay() {
		BusinessHour bh = null;
		try {
			bh = service.deleteBusinessHourByDay(DayOfWeek.Monday);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(bh);
	}

	@Test
	public void testUpdateBusinessHourbyDay() {
		BusinessHour bh = null;
		try {
			bh = service.updateBusinessHourByDay(DayOfWeek.Monday, Time.valueOf("10:00:00"), Time.valueOf("10:00:00"));
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(bh);
		assertEquals(DayOfWeek.Monday, bh.getDayOfWeek());
	}

	// Items

	@Test
	public void testCreatePerishableItem() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = "Apple";
		Float price = (float) 2.5;
		Boolean availableOnline = false;
		Integer numInStock = 10;
		Integer pointPerItem = 2;

		PerishableItem pitem = null;

		try {
			pitem = service.createPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(pitem);
	}

	@Test
	public void testCreatePerishableItemNoNameOrPrice() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = null;
		Float price = null;
		Boolean availableOnline = false;
		Integer numInStock = 10;
		Integer pointPerItem = 2;

		PerishableItem pitem = null;
		String error = null;

		try {
			pitem = service.createPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(pitem);
		assertEquals("Item name is empty!, Price is empty!", error);
	}

	@Test
	public void testCreatePerishableItemNoName() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = "";
		Float price = (float) 2.5;
		Boolean availableOnline = false;
		Integer numInStock = 10;
		Integer pointPerItem = 2;

		PerishableItem pitem = null;
		String error = null;

		try {
			pitem = service.createPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(pitem);
		assertEquals("Item name is empty!", error);
	}

	@Test
	public void testCreatePerishableItemInvalidEverything() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = null;
		Float price = null;
		Boolean availableOnline = null;
		Integer numInStock = null;
		Integer pointPerItem = null;

		PerishableItem pitem = null;
		String error = null;

		try {
			pitem = service.createPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(pitem);
		assertEquals("Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
	}

	@Test
	public void testGetPerishableItem() {
		assertEquals(0, service.getAllPerishableItems().size());

		PerishableItem getPitem = null;
		try {
			getPitem = service.getPerishableItemByID(PerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals(PerishableItem_ID, getPitem.getItemID());
		assertEquals(PerishableItem_name, getPitem.getProductName());
		assertEquals(PerishableItem_availableOnline, getPitem.getAvailableOnline());
		assertEquals(PerishableItem_price, getPitem.getPrice());
		assertEquals(PerishableItem_numInStock, getPitem.getNumInStock());
		assertEquals(PerishableItem_pointPerItem, getPitem.getPointPerItem());
	}

	@Test
	public void testGetPerishableItemInvalidID() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;

		PerishableItem getPitem = null;

		try {
			getPitem = service.getPerishableItemByID(NonPerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(getPitem);
		assertEquals("No such perishable item. Please search by another ID.", error);
	}

	@Test
	public void testGetPerishableItemByName() {
		assertEquals(0, service.getAllPerishableItems().size());

		List<PerishableItem> getPitem = null;
		try {
			getPitem = service.getPerishableItemsByProductName(PerishableItem_name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals(2, getPitem.size());
	}

	@Test
	public void testGetPerishableItemByInvalidName() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;

		List<PerishableItem> getPitem = null;
		try {
			getPitem = service.getPerishableItemsByProductName(NonPerishableItem_name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(getPitem);
		assertEquals("No such perishable items. Please search by another name.", error);
	}

	@Test
	public void testUpdatePerishableItem() {
		PerishableItem getPitem = service.getPerishableItemByID(PerishableItem_ID);
		try {
			getPitem = service.updatePerishableItem(getPitem, "AppleUpdated", NonPerishableItem_price,
					NonPerishableItem_availableOnline, NonPerishableItem_numInStock, NonPerishableItem_pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals(PerishableItem_ID, getPitem.getItemID());
		assertEquals("AppleUpdated", getPitem.getProductName());
		assertEquals(NonPerishableItem_availableOnline, getPitem.getAvailableOnline());
		assertEquals(NonPerishableItem_price, getPitem.getPrice());
		assertEquals(NonPerishableItem_numInStock, getPitem.getNumInStock());
		assertEquals(NonPerishableItem_pointPerItem, getPitem.getPointPerItem());
	}

	@Test
	public void testUpdatePerishableItemInvalidParameters() {
		PerishableItem getPitem = service.getPerishableItemByID(PerishableItem_ID);
		String error = null;
		try {
			getPitem = service.updatePerishableItem(getPitem, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals(
				"Item unchanged. Item name is empty!, Price is empty!, Please state whether this item is available online!, "
						+ "Please state the amount of stock!, Please state the amount of point given per item!",
				error);
	}

	@Test
	public void testUpdatePerishableItemInvalidPerishableItem() {
		PerishableItem getPitem = null;
		String error = null;
		try {
			getPitem = service.updatePerishableItem(null, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals("Please enter an item to update.", error);
		assertNull(getPitem);
	}

	@Test
	public void testCreateNonPerishableItem() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = "Desk";
		Float price = (float) 20.0;
		Boolean availableOnline = true;
		Integer numInStock = 10;
		Integer pointPerItem = 2;

		NonPerishableItem npitem = null;

		try {
			npitem = service.createNonPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(npitem);
	}

	@Test
	public void testCreateNonPerishableItemNoName() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;

		String name = "";
		Float price = (float) 20.0;
		Boolean availableOnline = true;
		Integer numInStock = 10;
		Integer pointPerItem = 2;

		NonPerishableItem npitem = null;

		try {
			npitem = service.createNonPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(npitem);
		assertEquals("Item name is empty!", error);
	}

	@Test
	public void testCreateNonPerishableItemNullEverything() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = null;
		Float price = null;
		Boolean availableOnline = null;
		Integer numInStock = null;
		Integer pointPerItem = null;

		NonPerishableItem npitem = null;
		String error = null;

		try {
			npitem = service.createNonPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(npitem);
		assertEquals("Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
	}

	@Test
	public void testGetNonPerishableItem() {
		assertEquals(0, service.getAllNonPerishableItems().size());

		NonPerishableItem getNPitem = null;
		try {
			getNPitem = service.getNonPerishableItemByID(NonPerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals(NonPerishableItem_ID, getNPitem.getItemID());
		assertEquals(NonPerishableItem_name, getNPitem.getProductName());
		assertEquals(NonPerishableItem_availableOnline, getNPitem.getAvailableOnline());
		assertEquals(NonPerishableItem_price, getNPitem.getPrice());
		assertEquals(NonPerishableItem_numInStock, getNPitem.getNumInStock());
		assertEquals(NonPerishableItem_pointPerItem, getNPitem.getPointPerItem());

	}

	@Test
	public void testGetNonPerishableItemInvalidID() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;

		NonPerishableItem getNPitem = null;

		try {
			getNPitem = service.getNonPerishableItemByID(PerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(getNPitem);
		assertEquals("No such non perishable item. Please search by another ID! ", error);
	}

	@Test
	public void testGetNonPerishableItemByName() {
		assertEquals(0, service.getAllNonPerishableItems().size());

		List<NonPerishableItem> getNPitem = null;
		try {
			getNPitem = service.getNonPerishableItemsByProductName(NonPerishableItem_name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals(1, getNPitem.size());
		assertEquals(ItemCategory.Furniture, getNPitem.get(0).getCategory());
	}

	@Test
	public void testGetNonPerishableItemByInvalidName() {
		assertEquals(0, service.getAllNonPerishableItems().size());
		String error = null;

		List<NonPerishableItem> getNPitem = null;
		try {
			getNPitem = service.getNonPerishableItemsByProductName(PerishableItem_name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(getNPitem);
		assertEquals("No such non perishable items. Please search by another name! ", error);
	}

	@Test
	public void testUpdateNonPerishableItem() {
		NonPerishableItem getNPitem = service.getNonPerishableItemByID(NonPerishableItem_ID);
		try {
			getNPitem = service.updateNonPerishableItem(getNPitem, "DeskUpdated", PerishableItem_price,
					PerishableItem_availableOnline, PerishableItem_numInStock, PerishableItem_pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertEquals(NonPerishableItem_ID, getNPitem.getItemID());
		assertEquals("DeskUpdated", getNPitem.getProductName());
		assertEquals(PerishableItem_availableOnline, getNPitem.getAvailableOnline());
		assertEquals(PerishableItem_price, getNPitem.getPrice());
		assertEquals(PerishableItem_numInStock, getNPitem.getNumInStock());
		assertEquals(PerishableItem_pointPerItem, getNPitem.getPointPerItem());
	}

	// WorkingHour Test
	@Test
	public void testCreateWorkingHour() {

		WorkingHour workingHour = null;

		DayOfWeek dayOfWeek = DayOfWeek.Monday;
		Time startTime = Time.valueOf("1:00:00");
		Time endTime = Time.valueOf("2:00:00");

		try {
			workingHour = service.createWorkingHour(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(workingHour);
		assertEquals(dayOfWeek, workingHour.getDayOfWeek());
		assertEquals(startTime, workingHour.getStartTime());
		assertEquals(endTime, workingHour.getEndTime());
	}

	@Test
	public void testcreateWorkingHourNull() {
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = null;
		Time startTime = null;
		Time endTime = null;
		WorkingHour workingHour = null;
		String error = null;
		try {
			workingHour = service.createWorkingHour(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(workingHour);
		assertEquals(
				"Working hour day of the week cannot be empty! Working hour start time cannot be empty! Working hour end time cannot be empty!",
				error);
	}

	@Test
	public void testcreateWorkingHourEndTimeBeforeStartTime() {
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = GroceryStoreSoftwareSystem.DayOfWeek.Monday;
		Time startTime = Time.valueOf("2:00:00");
		Time endTime = Time.valueOf("1:00:00");
		WorkingHour workingHour = null;
		String error = null;
		try {
			workingHour = service.createWorkingHour(dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(workingHour);
		assertEquals("Working hour end time cannot be before Working hour start time!", error);
	}

	@Test
	public void testGetAllWorkingHours() {
		assertNotNull(service.getAllWorkingHours());
	}

	@Test
	public void testGetExistingWorkingHours() {
		assertEquals(service.getWorkingHourByDay(GroceryStoreSoftwareSystem.DayOfWeek.Monday).getDayOfWeek(),
				GroceryStoreSoftwareSystem.DayOfWeek.Monday);
	}

	@Test
	public void testGetWorkingHoursNull() {
		String error = null;
		try {
			service.getWorkingHourByDay(null);

		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Day of week cannot be empty!", error);
	}

	@Test
	public void testGetWorkingHourByEmployeeAndDayOfWeek() {
		WorkingHour wh = null;
		String username = Account_KEY;

		try {
			wh = service.getWorkingHourByEmployeeAndDayOfWeek(username, DayOfWeek.Monday);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(wh);
		assertEquals(wh.getDayOfWeek(), DayOfWeek.Monday);
	}

	// Schedule Test

//	@Test
//	public void testCreateSchedule() {
//		assertEquals(0, service.getAllSchedules().size());
//
//		Schedule schedule = null;
//		Cashier cashier = service.createCashierRole();
//		cashier.setRoleID(1L);
//		cashier.setEmploymentDate(Date.valueOf("2020-2-2"));
//		Account employee = service.createAccount(Account_KEY, Account_Password, Account_Name, 0, cashier);
//		WorkingHour workingHour = service.createWorkingHour(DayOfWeek.Monday, Time.valueOf("10:00:00"),
//				Time.valueOf("13:00:00"));
//		List<WorkingHour> workingHours = new ArrayList<WorkingHour>();
//		workingHours.add(workingHour);
//
//		try {
//			schedule = service.createSchedule(employee.getUsername());
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//
//		assertNotNull(schedule);
//		assertEquals(employee.getAccountRole().getRoleID(), schedule.getEmployee().getRoleID());
//		assertEquals(((Employee) employee.getAccountRole()).getEmploymentDate(),
//				schedule.getEmployee().getEmploymentDate());
//
//	}

	@Test
	public void testcreateScheduleNull() {
		Schedule schedule = null;
		String error = null;

		try {
			schedule = service.createSchedule(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(schedule);
		assertEquals("Schedule employee cannot be empty!", error);
	}

	@Test
	public void testGetAllSchedules() {
		assertNotNull(service.getAllSchedules());
	}

	@Test
	public void testGetScheduleByEmployee() {
		Schedule schedule = null;

		Account account = accountDao.findByUsername(Account_KEY);

		try {
			schedule = service.getScheduleByEmployee(Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(schedule);
		assertEquals(1L, schedule.getScheduleID());
		assertEquals(account.getAccountRole().getRoleID(), schedule.getEmployee().getRoleID());
		assertEquals(((Employee) account.getAccountRole()).getEmploymentDate(),
				schedule.getEmployee().getEmploymentDate());
		assertEquals(((Employee) account.getAccountRole()).getClass(), schedule.getEmployee().getClass());
	}

	@Test
	public void testDeleteScheduleByEmployee() {

		assertNotNull(service.deleteScheduleByEmployee(Account_KEY));
	}

	// Item

	@Test
	public void testUpdateNonPerishableItemInvalidParameters() {
		NonPerishableItem getNPitem = service.getNonPerishableItemByID(NonPerishableItem_ID);
		String error = null;
		try {
			getNPitem = service.updateNonPerishableItem(getNPitem, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals(
				"Item unchanged. Item name is empty!, Price is empty!, Please state whether this item is available online!, "
						+ "Please state the amount of stock!, Please state the amount of point given per item!",
				error);
	}

	@Test
	public void testUpdateNonPerishableItemInvalidPerishableItem() {
		NonPerishableItem getNPitem = null;
		String error = null;
		try {
			getNPitem = service.updateNonPerishableItem(null, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals("Please enter an item to update! ", error);
		assertNull(getNPitem);
	}

	@Test
	public void testDeletePerishableItem() {
		PerishableItem pitem = service.createPerishableItem(PerishableItem_name, PerishableItem_price,
				PerishableItem_availableOnline, PerishableItem_numInStock, PerishableItem_pointPerItem,null,null);
		Long id = pitem.getItemID();
		PerishableItem deletedPitem = service.deletePerishableItem(pitem);
		assertNotNull(deletedPitem);
		assertEquals(id, deletedPitem.getItemID());
	}

	@Test
	public void testDeletePerishableItemInvalidPerishableItem() {

		PerishableItem deletedPitem = null;
		String error = null;
		try {
			deletedPitem = service.deletePerishableItem(null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		assertEquals("Please enter an item to delete.", error);
		assertNull(deletedPitem);
	}

	@Test
	public void testDeleteNonPerishableItem() {
		NonPerishableItem npitem = service.createNonPerishableItem(NonPerishableItem_name, NonPerishableItem_price,
				NonPerishableItem_availableOnline, NonPerishableItem_numInStock, NonPerishableItem_pointPerItem,null,null);
		Long id = npitem.getItemID();
		NonPerishableItem deletedNPitem = service.deleteNonPerishableItem(npitem);
		assertNotNull(deletedNPitem);
		assertEquals(id, deletedNPitem.getItemID());
	}

	@Test
	public void testDeleteNonPerishableItemInvalidNonPerishableItem() {

		NonPerishableItem deletedNPitem = null;
		String error = null;
		try {
			deletedNPitem = service.deleteNonPerishableItem(null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		assertEquals("Please enter an item to delete! ", error);
		assertNull(deletedNPitem);
	}

	// Cart

	@Test
	public void testCreateCart() {
		Cart cart = null;
		try {
			cart = service.createCart(Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(cart);
		assertEquals(0, cart.getNumOfItems());
		assertEquals(0f, cart.getTotalValue());
		assertEquals(Account_KEY, cart.getaccount().getUsername());
		assertNotNull(cart.getaccount());
		assertNotNull(cart.getItems());
	}

	@Test
	public void testCreateCartWithInvalidAccount() {
		Cart cart = null;
		String error = "";
		try {
			cart = service.createCart("");
		} catch (IllegalArgumentException e) {
			error += e.getMessage();
		}

		assertNull(cart);
		assertEquals("Invalid username! ", error);

	}

	@Test
	public void testGetCartByAccount() {
		Cart cart = null;
		try {
			cart = service.getCartByAccount(Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(cart);
	}

	@Test
	public void testGetAllCarts() {
		List<Cart> carts = null;

		try {
			carts = service.getAllCarts();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(carts);
	}

	@Test
	public void testCreatePerishableItemNullEverything() {
		assertEquals(0, service.getAllPerishableItems().size());

		String name = null;
		Float price = null;
		Boolean availableOnline = null;
		Integer numInStock = null;
		Integer pointPerItem = null;

		PerishableItem pitem = null;
		String error = null;

		try {
			pitem = service.createPerishableItem(name, price, availableOnline, numInStock, pointPerItem,null,null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(pitem);
		assertEquals("Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
	}

	@Test
	public void testCreateAllInvalidParameters() {
		String name = "";
		String error = "";
		Account account = null;
		Customer customer = service.createCustomerRole();
		try {
			account = service.createAccount("", "", name, 0, customer);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(account);
		// check error
		assertEquals(
				"Account username cannot be empty! Your password must at least be 6 characters long! Account name cannot be empty!",
				error);
	}

	@Test
	public void testAddToCart() {

		Cart cart = null;

		try {
			cart = service.addToCart(NonPerishableItem_ID, Account_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(cart);
	}

	@Test
	public void testAddTimeSlotToCart() {

		Cart cart = null;

		try {
			cart = service.addTimeSlotToCart(Account_KEY, new TimeSlot());
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(cart);
	}

	// Report

	@Test
	public void testCreateReport() {

		Report report = new Report();
		Calendar c = Calendar.getInstance();
		c.set(2001, Calendar.JULY, 17);
		Date startDate = new Date(c.getTimeInMillis());
		Date endDate = new Date(c.getTimeInMillis());

		try {
			report = service.createReport(startDate, endDate);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(report);
		assertEquals(startDate, report.getStartDate());
		assertEquals(endDate, report.getEndDate());
		assertEquals(0L, report.getTotalValue());
		assertEquals(0, report.getOrders().size());
	}

	@Test
	public void testCreateReportNullParameters() {

		Report report = null;
		String error = null;
		Date startDate = null;
		Date endDate = null;

		try {
			report = service.createReport(startDate, endDate);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(report);
		assertEquals("Time cannot be empty.", error);
	}

	@Test
	public void testCreateReportEndBeforeStart() {

		Report report = null;
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2001, Calendar.JULY, 17);
		Calendar c2 = Calendar.getInstance();
		c2.set(2001, Calendar.JULY, 16);
		Date startDate = new Date(c.getTimeInMillis());
		Date endDate = new Date(c2.getTimeInMillis());

		try {
			report = service.createReport(startDate, endDate);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(report);
		assertEquals("End time cannot be before start time!", error);
	}

	@Test
	public void testGetAllReports() {
		assertNotNull(service.getAllReports());
	}

	@Test
	public void testGetReportByID() {
		Report report = null;
		try {
			report = service.getReportById(1L);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		Calendar c = Calendar.getInstance();
		c.set(2001, Calendar.JULY, 17);
		assertNotNull(report);
		assertEquals((float) 200.0, report.getTotalValue());
	}

	@Test
	public void testGetReportByIDNullParameter() {
		Report report = null;
		String error = null;
		try {
			report = service.getReportById(null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		assertNull(report);
		assertEquals("Please enter legal id.", error);
	}

	// TimeSlot

	@Test
	public void testCreateTimeSlot() {
		Date startDate = Date.valueOf("2000-10-14");
		Date endDate = Date.valueOf("2000-10-15");
		Time startTime = Time.valueOf("1:00:00");
		Time endTime = Time.valueOf("2:00:00");
		String error = null;
		TimeSlot slot = null;
		try {
			slot = service.createTimeSlot(startDate, endDate, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(slot);
		assertEquals(startDate, slot.getStartDate());
		assertEquals(endDate, slot.getEndDate());
		assertEquals(startTime, slot.getStartTime());
		assertEquals(endTime, slot.getEndTime());
		assertNull(error);
	}

	@Test
	public void testCreateTimeSlotNull() {
		Date startDate = null;
		Date endDate = null;
		Time startTime = null;
		Time endTime = null;
		String error = null;
		TimeSlot slot = null;
		try {
			slot = service.createTimeSlot(startDate, endDate, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(slot);
		assertEquals(
				"Start date name cannot be empty! End date time cannot be empty! Start time cannot be empty! End time cannot be empty!",
				error);
	}

	@Test
	public void testCreateTimeSlotDateTImeEndBeforeStart() {
		Date startDate = Date.valueOf("2000-10-16");
		Date endDate = Date.valueOf("2000-10-15");
		Time startTime = Time.valueOf("3:00:00");
		Time endTime = Time.valueOf("2:00:00");
		String error = null;
		TimeSlot slot = null;
		try {
			slot = service.createTimeSlot(startDate, endDate, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(slot);
		assertEquals("End time cannot be before start time! End date cannot be before start date!", error);
	}

	@Test
	public void testGetAllTimeslot() {
		assertNotNull(service.getAllTimeSlots());
	}

	@Test
	public void testGetAllHolidays() {
		Store store = new Store();
		store.setHolidays(new HashSet<TimeSlot>());
		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));
		assertNotNull(service.getAllHolidays());
	}

	@Test
	public void testGetAllHolidaysNull() {
		Store store = new Store();

		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));
		assertNull(service.getAllHolidays());
	}

	// Terminal

	@Test
	public void testCreateTerminal() {
		Terminal terminal = null;
		try {
			terminal = service.createTerminal();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(terminal);
	}

	@Test
	public void testDeleteTerminal() {
		Terminal deletedTerminal = null;
		try {
			deletedTerminal = service.deleteTerminal(terminal_ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(deletedTerminal);
		assertEquals(terminal_ID, deletedTerminal.getTerminalID());
	}

	@Test
	public void testDeleteTerminalNullParameter() {

		Terminal deletedTerminal = null;
		String error = null;
		try {
			deletedTerminal = service.deleteTerminal(null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertNull(deletedTerminal);
		assertEquals("No terminal with this ID exists", error);
	}

	@Test
	public void testGetAllTerminals() {
		assertNotNull(service.getAllTerminals());
	}

}
