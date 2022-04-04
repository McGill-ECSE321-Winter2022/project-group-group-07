package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.AccountDto;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.Customer;
import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@Autowired
	private GroceryStoreService service;

	@PostMapping(value = { "/login", "/login/" })
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
		try {
			Account account = service.login(username, password);
			return new ResponseEntity<>(convertToDto(account, account.getAccountRole()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = { "/customerAccount/{username}", "/customerAccount/{username}/" })
	public ResponseEntity<?> createCustomerAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password) {

		try {
			AccountRole role = service.createCustomerRole();
			Account account = service.createAccount(username, password, name, 0, role);

			return new ResponseEntity<>(convertToDto(account, role), HttpStatus.CREATED);
		} catch (Exception e) {
			String message = e.getMessage();
			String[] array = message.split("!");
			return new ResponseEntity<>(array[0], HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = { "/employeeAccount/{username}", "/employeeAccount/{username}/" })
	public ResponseEntity<?> createEmployeeAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password, @RequestParam String role) {

		Employee employeeRole = null;

		if (role.contains("Cashier")) {
			employeeRole = service.createCashierRole();
		} else if (role.contains("Clerk")) {
			employeeRole = service.createClerkRole();
		} else if (role.contains("DeliveryPerson")) {
			employeeRole = service.createDeliveryPersonRole();
		} else if (role.contains("Owner")) {
			employeeRole = service.createOwnerRole();
		} else {
			return new ResponseEntity<>("No such role exists", HttpStatus.BAD_REQUEST);
		}

		try {
			Account account = service.createAccount(username, password, name, 0, employeeRole);
			return new ResponseEntity<>(convertToDto(account, employeeRole), HttpStatus.CREATED);
		} catch (Exception e) {
			String message = e.getMessage();
			String[] array = message.split("!");
			return new ResponseEntity<>(array[0], HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = { "/accounts", "/accounts/" })
	public ResponseEntity<?> getAllAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = { "/customerAccounts", "/customerAccounts/" })
	public ResponseEntity<?> getAllCustomerAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null && a.getAccountRole() instanceof Customer) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	@GetMapping(value = { "/{username}", "/{username}/" })
	public ResponseEntity<?> getAccount(@PathVariable("username") String username) {

		try {
			Account account = service.getAccount(username);
			return new ResponseEntity<>(convertToDto(account,account.getAccountRole()),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = { "/employeeAccounts", "/employeeAccounts/" })
	public ResponseEntity<?> getAllEmployeeAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllEmployees()) {
			accounts.add(convertToDto(a, a.getAccountRole()));
		}
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deleteAccount/{username}", "/deleteAccount/{username}/" })
	public void deleteAccount(@PathVariable("username") String username, @RequestParam String password) {
		service.deleteAccount(username, password);
	}
	
	@DeleteMapping(value = { "/fireEmployee/{username}", "/fireEmployee/{username}/" })
	public void deleteAccount(@PathVariable("username") String username) {
		service.deleteEmployeeAccount(username);
	}

	@PutMapping(value = { "/updatePassword/{username}", "/updatePassword/{username}/" })
	public ResponseEntity<?> updatePassword(@PathVariable("username") String username, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		try {
			Account account = service.updatePassword(username, oldPassword, newPassword);
			return new ResponseEntity<>(convertToDto(account, account.getAccountRole()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = { "/updateName/{username}", "/updateName/{username}/" })
	public ResponseEntity<?> updateName(@PathVariable("username") String username, @RequestParam String newName) {
		try {
			Account account = service.updateName(username, newName);
			return new ResponseEntity<>(convertToDto(account, account.getAccountRole()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

}
