package ca.mcgill.ecse321.grocerystore.service;

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
import java.util.Calendar;

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
import ca.mcgill.ecse321.grocerystore.dao.PerishableItemRepository;
import ca.mcgill.ecse321.grocerystore.dao.NonPerishableItemRepository;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;

@ExtendWith(MockitoExtension.class)
public class testGroceryStoreService {
	@Mock
	private AccountRepository accountDao;
	@Mock
	private CustomerRepository customerRoleDao;
	@Mock
	private PerishableItemRepository perishableItemDao;
	@Mock
	private NonPerishableItemRepository nonPerishableItemDao;
	@InjectMocks
	private GroceryStoreService service;
	private static final String Account_KEY = "TestAccount";
	
	@BeforeEach
	public void setMockOutput() {
	    lenient().when(accountDao.findByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(Account_KEY)) {
	            Account account = new Account();
	            account.setName(Account_KEY);
	            return account;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRoleDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(perishableItemDao.save(any(PerishableItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(nonPerishableItemDao.save(any(NonPerishableItem.class))).thenAnswer(returnParameterAsAnswer);
	}
	@Test
	public void testCreateAccount() {
		assertEquals(0, service.getAllAccounts().size());

		String name = "Lello";
		Account account = null;
		Customer customer= service.createCustomerRole();
		try {
			account = service.createAccount("lel","batata",name,0,customer);
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
		Customer customer= service.createCustomerRole();
		try {
			account = service.createAccount("lel","batata",name,0,customer);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(account);
		// check error
		assertEquals("Account name cannot be empty!", error);
	}
	@Test
	public void testCreatePerishableItem() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		Integer id = 1;
		String name = "Apple";
		Float price = (float) 2.5;
		Boolean availableOnline = false;
		Integer numInStock = 10;
		Integer pointPerItem = 2;
		
		PerishableItem pitem = null;
		
		try {
			pitem = service.createPerishableItem(id, name, price, availableOnline, numInStock, pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}	
		assertNotNull(pitem);
	}
	
	@Test
	public void testCreatePerishableItemNoNameOrPrice() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		Integer id = 1;
		String name = null;
		Float price = null;
		Boolean availableOnline = false;
		Integer numInStock = 10;
		Integer pointPerItem = 2;
		
		PerishableItem pitem = null;
		String error = null;
		
		try {
			pitem = service.createPerishableItem(id, name, price, availableOnline, numInStock, pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		assertNull(pitem);
		assertEquals("Item name is empty!, Price is empty!", error);
	}
	
	@Test
	public void testCreatePerishableItemNullEverything() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		Integer id = null;
		String name = null;
		Float price = null;
		Boolean availableOnline = null;
		Integer numInStock = null;
		Integer pointPerItem = null;
		
		PerishableItem pitem = null;
		String error = null;
		
		try {
			pitem = service.createPerishableItem(id, name, price, availableOnline, numInStock, pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		assertNull(pitem);
		assertEquals("ItemID is empty!, Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
	}
	
	@Test
	public void testCreateNonPerishableItem() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		Integer id = 1;
		String name = "Desk";
		Float price = (float) 20.0;
		Boolean availableOnline = true;
		Integer numInStock = 10;
		Integer pointPerItem = 2;
		
		NonPerishableItem npitem = null;
		
		try {
			npitem = service.createNonPerishableItem(id, name, price, availableOnline, numInStock, pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}	
		assertNotNull(npitem);
	}
	
	@Test
	public void testCreateNonPerishableItemNullEverything() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		Integer id = null;
		String name = null;
		Float price = null;
		Boolean availableOnline = null;
		Integer numInStock = null;
		Integer pointPerItem = null;
		
		NonPerishableItem npitem = null;
		String error = null;
		
		try {
			npitem = service.createNonPerishableItem(id, name, price, availableOnline, numInStock, pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		assertNull(npitem);
		assertEquals("ItemID is empty!, Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
	}
}
