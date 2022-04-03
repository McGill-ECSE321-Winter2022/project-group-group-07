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
import ca.mcgill.ecse321.grocerystore.dto.AddressDto;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.Address;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private GroceryStoreService service;

	@PostMapping(value = { "/address/{username}", "/address/{username}/" })
	public ResponseEntity<?> createAddress(@PathVariable("username") String username, @RequestParam Integer buildingNo,
			@RequestParam String street, @RequestParam String town) {

		try {
			Account account = service.getAccount(username);
			Address address = service.createAddress(buildingNo, street, town, account);
			return new ResponseEntity<>(convertToDto(address, convertToDto(account, account.getAccountRole())),
					HttpStatus.OK);
		} catch (Exception e) {
			String message = e.getMessage();
			String[] array = message.split("!");
			return new ResponseEntity<>(array[0], HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = { "/address", "/address/" })
	public ResponseEntity<?> getAllAddresses() {

		List<AddressDto> addresses = new ArrayList<AddressDto>();

		for (Address a : service.getAllAddresses()) {
			if (a != null) {
				addresses.add(convertToDto(a, convertToDto(a.getAccount(), a.getAccount().getAccountRole())));
			}
		}
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = { "/address/{username}", "/address/{username}/" })
	public ResponseEntity<?> getAddressByAccount(@PathVariable("username") String username) {

		Account account = service.getAccount(username);
		return new ResponseEntity<>(
				convertToDto(service.getAddressByUsername(username), convertToDto(account, account.getAccountRole())),
				HttpStatus.OK);

	}

	@PutMapping(value = { "/updateAddress/{username}", "/updateAddress/{username}/" })
	public ResponseEntity<?> updateAddress(@PathVariable("username") String username, @RequestParam Integer buildingNo,
			@RequestParam String street, @RequestParam String town) {
		try {
			Address address = service.updateAddress(username, buildingNo, street, town);
			return new ResponseEntity<>(
					convertToDto(address, convertToDto(address.getAccount(), address.getAccount().getAccountRole())),
					HttpStatus.OK);
		} catch (Exception e) {
			String message = e.getMessage();
			String[] array = message.split("!");
			return new ResponseEntity<>(array[0], HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = { "/deleteAddress/{username}", "/deleteAddress/{username}/" })
	public void deleteAddressByAccount(@PathVariable("username") String username) {
		service.deleteAddressByAccount(username);
	}

	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

	private AddressDto convertToDto(Address address, AccountDto account) {

		return new AddressDto(address.getBuildingNo(), address.getStreet(), address.getTown(), account);
	}

}
