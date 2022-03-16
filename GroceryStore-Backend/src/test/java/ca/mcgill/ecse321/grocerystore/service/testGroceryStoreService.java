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
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.Customer;

@ExtendWith(MockitoExtension.class)
public class testGroceryStoreService {
	@Mock
	private AccountRepository accountDao;
	@Mock
	private CustomerRepository customerRoleDao;
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
	
}
