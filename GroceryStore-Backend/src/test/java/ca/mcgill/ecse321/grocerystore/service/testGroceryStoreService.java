package ca.mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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
	
	private static final Integer PerishableItem_ID = 1;
	private static final String PerishableItem_name = "Apple";
	private static final Float PerishableItem_price = (float) 2.5;
	private static final Boolean PerishableItem_availableOnline = false;
	private static final Integer PerishableItem_numInStock = 10;
	private static final Integer PerishableItem_pointPerItem = 2;
	
	private static final Integer NonPerishableItem_ID = 2;
	private static final String NonPerishableItem_name = "Desk";
	private static final Float NonPerishableItem_price = (float) 20.0;
	private static final Boolean NonPerishableItem_availableOnline = true;
	private static final Integer NonPerishableItem_numInStock = 5;
	private static final Integer NonPerishableItem_pointPerItem = 10;
	
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
		lenient().when(perishableItemDao.findByItemID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PerishableItem_ID)) {
                PerishableItem pitem = new PerishableItem ();
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
		lenient().when(perishableItemDao.findByProductName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(PerishableItem_name)) {
            	List<PerishableItem> plist = new ArrayList<PerishableItem>();
                PerishableItem pitem = new PerishableItem ();
                pitem.setItemID(PerishableItem_ID);
                pitem.setProductName(PerishableItem_name);
                pitem.setPrice(PerishableItem_price);
                pitem.setAvailableOnline(PerishableItem_availableOnline);
                pitem.setNumInStock(PerishableItem_numInStock);
                pitem.setPointPerItem(PerishableItem_pointPerItem);
                plist.add(pitem);
                
                PerishableItem pitem2 = new PerishableItem ();
                pitem2.setItemID(2);
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
		lenient().when(nonPerishableItemDao.findByItemID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(NonPerishableItem_ID)) {
                NonPerishableItem npitem = new NonPerishableItem ();
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
		lenient().when(nonPerishableItemDao.findByProductName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(NonPerishableItem_name)) {
            	List<NonPerishableItem> nplist = new ArrayList<NonPerishableItem>();
                NonPerishableItem npitem = new NonPerishableItem ();
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
	public void testCreatePerishableItemNoName() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		Integer id = 1;
		String name = "";
		Float price = (float) 2.5;
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
		assertEquals("Item name is empty!", error);
	}
	
	@Test
	public void testCreatePerishableItemInvalidEverything() {
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
	public void testGetPerishableItem() {
		assertEquals(0, service.getAllPerishableItems().size());
		
		PerishableItem getPitem = null;
		try {
			getPitem = service.getPerishableItemsByID(PerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}	
		assertEquals(PerishableItem_ID,getPitem.getItemID());
		assertEquals(PerishableItem_name,getPitem.getProductName());
		assertEquals(PerishableItem_availableOnline,getPitem.getAvailableOnline());
		assertEquals(PerishableItem_price,getPitem.getPrice());
		assertEquals(PerishableItem_numInStock,getPitem.getNumInStock());
		assertEquals(PerishableItem_pointPerItem,getPitem.getPointPerItem());
	}
	
	@Test
	public void testGetPerishableItemInvalidID() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;
		
		PerishableItem getPitem = null;
		
		try {
			getPitem = service.getPerishableItemsByID(NonPerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error =e.getMessage();
		}	
		assertNull(getPitem);
		assertEquals("No such perishable item. Please search by another ID.",error);
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
		assertEquals(2,getPitem.size());
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
			error =e.getMessage();
		}	
		assertNull(getPitem);
		assertEquals("No such perishable items. Please search by another name.", error);
	}
	
	@Test
	public void testUpdatePerishableItem() {
		PerishableItem getPitem = service.getPerishableItemsByID(PerishableItem_ID);
		try {
			getPitem = service.updatePerishableItem(getPitem, "AppleUpdated", NonPerishableItem_price, NonPerishableItem_availableOnline, NonPerishableItem_numInStock, NonPerishableItem_pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}	
		assertEquals(PerishableItem_ID,getPitem.getItemID());
		assertEquals("AppleUpdated",getPitem.getProductName());
		assertEquals(NonPerishableItem_availableOnline,getPitem.getAvailableOnline());
		assertEquals(NonPerishableItem_price,getPitem.getPrice());
		assertEquals(NonPerishableItem_numInStock,getPitem.getNumInStock());
		assertEquals(NonPerishableItem_pointPerItem,getPitem.getPointPerItem());
	}
	
	@Test
	public void testUpdatePerishableItemInvalidParameters() {
		PerishableItem getPitem = service.getPerishableItemsByID(PerishableItem_ID);
		String error = null;
		try {
			getPitem = service.updatePerishableItem(getPitem, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		assertEquals("Item unchanged. Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
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
	public void testCreateNonPerishableItemNoName() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;
		
		Integer id = 1;
		String name = "";
		Float price = (float) 20.0;
		Boolean availableOnline = true;
		Integer numInStock = 10;
		Integer pointPerItem = 2;
		
		NonPerishableItem npitem = null;
		
		try {
			npitem = service.createNonPerishableItem(id, name, price, availableOnline, numInStock, pointPerItem);
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
	@Test
	public void testGetNonPerishableItem() {
		assertEquals(0, service.getAllNonPerishableItems().size());
		
		NonPerishableItem getNPitem = null;
		try {
			getNPitem = service.getNonPerishableItemsByID(NonPerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}	
		assertEquals(NonPerishableItem_ID,getNPitem.getItemID());
		assertEquals(NonPerishableItem_name,getNPitem.getProductName());
		assertEquals(NonPerishableItem_availableOnline,getNPitem.getAvailableOnline());
		assertEquals(NonPerishableItem_price,getNPitem.getPrice());
		assertEquals(NonPerishableItem_numInStock,getNPitem.getNumInStock());
		assertEquals(NonPerishableItem_pointPerItem,getNPitem.getPointPerItem());
	}
	
	@Test
	public void testGetNonPerishableItemInvalidID() {
		assertEquals(0, service.getAllPerishableItems().size());
		String error = null;
		
		NonPerishableItem getNPitem = null;
		
		try {
			getNPitem = service.getNonPerishableItemsByID(PerishableItem_ID);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error =e.getMessage();
		}	
		assertNull(getNPitem);
		assertEquals("No such non perishable item. Please search by another ID.", error);
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
		assertEquals(1,getNPitem.size());
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
			error =e.getMessage();
		}	
		assertNull(getNPitem);
		assertEquals("No such non perishable items. Please search by another name.", error);
	}
	
	@Test
	public void testUpdateNonPerishableItem() {
		NonPerishableItem getNPitem = service.getNonPerishableItemsByID(NonPerishableItem_ID);
		try {
			getNPitem = service.updateNonPerishableItem(getNPitem, "DeskUpdated", PerishableItem_price, PerishableItem_availableOnline, PerishableItem_numInStock, PerishableItem_pointPerItem);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}	
		assertEquals(NonPerishableItem_ID,getNPitem.getItemID());
		assertEquals("DeskUpdated",getNPitem.getProductName());
		assertEquals(PerishableItem_availableOnline,getNPitem.getAvailableOnline());
		assertEquals(PerishableItem_price,getNPitem.getPrice());
		assertEquals(PerishableItem_numInStock,getNPitem.getNumInStock());
		assertEquals(PerishableItem_pointPerItem,getNPitem.getPointPerItem());
	}
	
	@Test
	public void testUpdateNonPerishableItemInvalidParameters() {
		NonPerishableItem getNPitem = service.getNonPerishableItemsByID(NonPerishableItem_ID);
		String error = null;
		try {
			getNPitem = service.updateNonPerishableItem(getNPitem, null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		assertEquals("Item unchanged. Item name is empty!, Price is empty!, Please state whether this item is available online!, "
				+ "Please state the amount of stock!, Please state the amount of point given per item!", error);
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
		assertEquals("Please enter an item to update.", error);
	}
	
	@Test
	public void testDeletePerishableItem() {
		PerishableItem pitem = service.createPerishableItem(PerishableItem_ID, PerishableItem_name, PerishableItem_price, PerishableItem_availableOnline, PerishableItem_numInStock, PerishableItem_pointPerItem);
		PerishableItem deletedPitem = service.deletePerishableItems(pitem);
		assertNotNull(deletedPitem);
		assertEquals(PerishableItem_ID,deletedPitem.getItemID());
	}
	
	@Test
	public void testDeletePerishableItemInvalidPerishableItem() {
		
		PerishableItem deletedPitem = null;
		String error = null;
		try {
			deletedPitem = service.deletePerishableItems(null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		
		assertEquals("Please enter an item to delete." , error);
	}
	
	@Test
	public void testDeleteNonPerishableItem() {
		NonPerishableItem npitem = service.createNonPerishableItem(NonPerishableItem_ID, NonPerishableItem_name, NonPerishableItem_price, NonPerishableItem_availableOnline, NonPerishableItem_numInStock, NonPerishableItem_pointPerItem);
		NonPerishableItem deletedNPitem = service.deleteNonPerishableItems(npitem);
		assertNotNull(deletedNPitem);
		assertEquals(NonPerishableItem_ID,deletedNPitem.getItemID());
	}
	
	@Test
	public void testDeleteNonPerishableItemInvalidNonPerishableItem() {
		
		NonPerishableItem deletedNPitem = null;
		String error = null;
		try {
			deletedNPitem = service.deleteNonPerishableItems(null);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}	
		
		assertEquals("Please enter an item to delete." , error);
	}
}
