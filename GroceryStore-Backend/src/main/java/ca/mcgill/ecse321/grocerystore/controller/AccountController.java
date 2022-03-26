package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

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

	private GroceryStoreService service;


	@PostMapping(value = {"/login","/login/"})
	public Boolean login(@RequestParam String username, @RequestParam String password) {
		return service.login(username,password);
	}
	
	@PostMapping(value = { "/customerAccount/{username}", "/customerAccount/{username}/" })
	public AccountDto createCustomerAccount(@PathVariable("username") String username, @RequestParam String name,
			@RequestParam String password) {

		Customer role = service.createCustomerRole();
		Account account = service.createAccount(username, password, name, 0, role);

		return convertToDto(account, role);
	}

	@PostMapping(value = { "/employeeAccount/{username}", "/employeeAccount/{username}/" })
	public AccountDto createEmployeeAccount(@PathVariable("username") String username, @RequestParam String name,
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
			throw new IllegalArgumentException("No such employee role exists");
		}

		Account account = service.createAccount(username, password, name, 0, employeeRole);

		return convertToDto(account, employeeRole);
	}

	@GetMapping(value = { "/accounts", "/accounts/" })
	public List<AccountDto> getAllAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return accounts;
	}

	@GetMapping(value = { "/customerAccounts", "/customerAccounts/" })
	public List<AccountDto> getAllCustomerAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllAccounts()) {
			if (a != null && a.getAccountRole() instanceof Customer) {
				accounts.add(convertToDto(a, a.getAccountRole()));
			}
		}
		return accounts;
	}

	@GetMapping(value = { "/employeeAccounts", "/employeeAccounts/" })
	public List<AccountDto> getAllEmployeeAccounts() {

		List<AccountDto> accounts = new ArrayList<AccountDto>();

		for (Account a : service.getAllEmployees()) {
			accounts.add(convertToDto(a, a.getAccountRole()));
		}
		return accounts;
	}

	@DeleteMapping(value = { "/deleteAccount/{username}", "/deleteAccount/{username}/" })
	public void deleteAccount(@PathVariable("username") String username, @RequestParam String password) {
		service.deleteAccount(username, password);
	}

	@PutMapping(value = { "/updatePassword/{username}", "/updatePassword/{username}/" })
	public AccountDto updatePassword(@PathVariable("username") String username, @RequestParam String oldPassword,
			@RequestParam String newPassword) {

		Account account = service.updatePassword(username, oldPassword, newPassword);

		return convertToDto(account, account.getAccountRole());
	}

	@PutMapping(value = { "/updateName/{username}", "/updateName/{username}/" })
	public AccountDto updateName(@PathVariable("username") String username, @RequestParam String newName) {

		Account account = service.updateName(username, newName);

		return convertToDto(account, account.getAccountRole());
	}


	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

	
}
