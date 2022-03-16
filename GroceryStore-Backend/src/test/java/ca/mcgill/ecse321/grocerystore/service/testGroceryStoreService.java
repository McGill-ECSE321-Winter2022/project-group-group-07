package ca.mcgill.ecse321.grocerystore.service;

import ca.mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import ca.mcgill.ecse321.grocerystore.dao.StoreRepository;
import ca.mcgill.ecse321.grocerystore.model.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.grocerystore.dao.AccountRepository;
import ca.mcgill.ecse321.grocerystore.dao.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class testGroceryStoreService {
	@Mock
	private AccountRepository accountDao;
	@Mock
	private CustomerRepository customerRoleDao;
	@Mock
	private StoreRepository storeDao;
	@Mock
	private BusinessHourRepository businessHourDao;
	@InjectMocks
	private GroceryStoreService service;
	private static final String Account_KEY = "TestAccount";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(accountDao.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(Account_KEY)) {
				Account account = new Account();
				account.setName(Account_KEY);
				return account;
			} else {
				return null;
			}
		});
		lenient().when(businessHourDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
				BusinessHour b1 = new BusinessHour();
				BusinessHour b2 = new BusinessHour();
				b1.setBusinessHourId(1);
				b1.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Monday);
				b2.setBusinessHourId(2);
				b2.setDayOfWeek(GroceryStoreSoftwareSystem.DayOfWeek.Tuesday);
				List<BusinessHour> days = new ArrayList<>();
				days.add(b1);
				days.add(b2);
				return days;
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRoleDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateAccount() {
		assertEquals(0, service.getAllAccounts().size());

		String name = "Lello";
		Account account = null;
		Customer customer = service.createCustomerRole();
		try {
			account = service.createAccount("lel", "batata", name, 0, customer);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(account);
		assertEquals(name, account.getName());
	}

	@Test
	public void testCreateAccountNull() {
		String name = null;
		String error = null;
		Account account = null;
		Customer customer = service.createCustomerRole();
		try {
			account = service.createAccount("lel", "batata", name, 0, customer);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(account);
		// check error
		assertEquals("Account name cannot be empty!", error);
	}

	// Test for Store
	@Test
	public void testCreateStore() {
		assertNull(service.getStore());
		Integer storeID = 1;
		String name = "Store";
		String address = "123 test street";
		String phoneNumber = "666";
		String email = "tes@example.ex";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5.5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(storeID, name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(store);
		assertEquals(storeID, store.getStoreID());
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
		Integer storeID = null;
		String name = null;
		String address = null;
		String phoneNumber = null;
		String email = null;
		Integer employeeDiscountRate = null;
		Float pointToCashRatio = null;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(storeID, name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals("Store id cannot be empty! Store name cannot be empty! Store address time cannot be empty! Store phone number cannot be empty! Store email cannot be empty! Employee discount rate cannot be empty! Point to cash ratio cannot be empty!", error);
	}
	@Test
	public void testCreateStoreEmpty(){
		assertNull(service.getStore());
		Integer storeID = 1;
		String name = "";
		String address = "";
		String phoneNumber = "";
		String email = "";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(storeID, name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals("Store name cannot be empty! Store address time cannot be empty! Store phone number cannot be empty! Store email cannot be empty!", error);
	}

	@Test
	public void testCreateStoreSpace(){
		assertNull(service.getStore());
		Integer storeID = 1;
		String name = " ";
		String address = " ";
		String phoneNumber = " ";
		String email = " ";
		Integer employeeDiscountRate = 15;
		Float pointToCashRatio = 5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(storeID, name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals("Store name cannot be empty! Store address time cannot be empty! Store phone number cannot be empty! Store email cannot be empty!", error);
	}

	@Test
	public void testCreateStoreIllegalDiscount() {
		assertNull(service.getStore());
		Integer storeID = 1;
		String name = "Store";
		String address = "123 test street";
		String phoneNumber = "666";
		String email = "tes@example.ex";
		Integer employeeDiscountRate = 200;
		Float pointToCashRatio = 5.5f;
		String error = null;
		Store store = null;
		try {
			store = service.createStore(storeID, name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(store);
		assertEquals("Employee discount rate must be between 0 and 100!", error);
	}
	@Test
	public void testGetExistingStore() {
		Store store = new Store();
		store.setStoreID(123);
		lenient().when(storeDao.findAll()).thenReturn(Collections.singletonList(store));

		assertEquals(123, service.getStore().getStoreID());
	}
	@Test
	public void testGetNonExistingStore() {
		assertNull(service.getStore());
	}

	// Business Hour test
	@Test
	public void testcreateBusinessHour() {
		Integer id = 123;
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = GroceryStoreSoftwareSystem.DayOfWeek.Monday;
		Time startTime = Time.valueOf("7:59:59");
		Time endTime = Time.valueOf("23:59:59");
		BusinessHour businessHour = null;
		String error = null;
		try {
			businessHour = service.createBusinessHour(id, dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNotNull(businessHour);
		assertEquals(id, businessHour.getBusinessHourId());
		assertEquals(dayOfWeek, businessHour.getDayOfWeek());
		assertEquals(startTime, businessHour.getStartTime());
		assertEquals(endTime, businessHour.getEndTime());
		assertNull(error);
	}
	@Test
	public void testcreateBusinessHourNull() {
		Integer id = null;
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = null;
		Time startTime = null;
		Time endTime = null;
		BusinessHour businessHour = null;
		String error = null;
		try {
			businessHour = service.createBusinessHour(id, dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(businessHour);
		assertEquals("Business hour id cannot be empty! Business hour day of the week cannot be empty! Business hour start time cannot be empty! Business hour end time cannot be empty!", error);
	}
	@Test
	public void testcreateBusinessHourEndTimeBeforeStartTime() {
		Integer id = 123;
		GroceryStoreSoftwareSystem.DayOfWeek dayOfWeek = GroceryStoreSoftwareSystem.DayOfWeek.Monday;
		Time startTime = Time.valueOf("17:59:59");
		Time endTime = Time.valueOf("3:59:59");
		BusinessHour businessHour = null;
		String error = null;
		try {
			businessHour = service.createBusinessHour(id, dayOfWeek, startTime, endTime);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(businessHour);
		assertEquals("Business hour end time cannot be before Business hour start time!" ,error);
	}
	@Test
	public void testGetAllBusinessHours() {
		assertNotNull(service.getAllBusinessHours());
	}
	@Test
	public void testGetExistingBusinessHours() {
		assertEquals(service.getBusinessHourByDay(GroceryStoreSoftwareSystem.DayOfWeek.Monday).getDayOfWeek(), GroceryStoreSoftwareSystem.DayOfWeek.Monday);
	}
	@Test
	public void testGetBusinessHoursNull() {
		String error = null;
		try {
			service.getBusinessHourByDay(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Day of week cannot be empty!",error);
	}
}
