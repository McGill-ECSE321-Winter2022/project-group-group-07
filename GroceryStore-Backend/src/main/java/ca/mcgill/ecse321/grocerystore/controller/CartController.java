package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.AccountDto;
import ca.mcgill.ecse321.grocerystore.dto.CartDto;
import ca.mcgill.ecse321.grocerystore.dto.ItemDto;
import ca.mcgill.ecse321.grocerystore.dto.NonPerishableItemDto;
import ca.mcgill.ecse321.grocerystore.dto.PerishableItemDto;
import ca.mcgill.ecse321.grocerystore.dto.TimeSlotDto;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.Cart;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private GroceryStoreService service;

	@GetMapping(value = { "/cart/{username}", "/cart/{username}/" })
	public ResponseEntity<?> getCartByAccount(@PathVariable("username") String username) {
		try {
			return new ResponseEntity<>(convertToDto(service.getCartByAccount(username)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = { "/cart/{username}", "/cart/{username}/" })
	public ResponseEntity<?> createCart(@PathVariable("username") String username) {
		try {
			return new ResponseEntity<>(convertToDto(service.createCart(username)), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = { "/addToCart/{id}", "/addToCart/{id}/" })
	public ResponseEntity<?> addToCart(@PathVariable("id") Long id, String username) {
		try {
			return new ResponseEntity<>(convertToDto(service.addToCart(id, username)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(convertToDto(service.addToCart(id, username)), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = { "/pickTimeSlot/{username}", "/pickTimeSlot/{username}/" })
	public ResponseEntity<?> addTimeSlotToCart(@PathVariable("username") String username, @RequestParam Date startDate,
			@RequestParam Date endDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		try {
			return new ResponseEntity<>(convertToDto(service.addTimeSlotToCart(username,
					service.createTimeSlot(startDate, endDate, Time.valueOf(startTime), Time.valueOf(endTime)))),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PutMapping(value = { "/chooseOrderType/{username}", "/chooseOrderType/{username}/" })
	public ResponseEntity<?> chooseOrderTypeForCart(@PathVariable("username") String username,
			@RequestParam String orderType) {
		try {
			return new ResponseEntity<>(
					convertToDto(service.chooseOrderTypeForCart(username, OrderType.valueOf(orderType))),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	private CartDto convertToDto(Cart cart) {
		List<ItemDto> items = new ArrayList<ItemDto>();
		AccountDto account = convertToDto(cart.getaccount(), cart.getaccount().getAccountRole());
		TimeSlotDto timeSlot = convertToDto(cart.getTimeSlot());
		for (Item i : cart.getItems()) {
			if (i instanceof PerishableItem) {
				items.add(convertToDto((PerishableItem) i));
			}
			if (i instanceof NonPerishableItem) {
				items.add(convertToDto((NonPerishableItem) i));
			}
		}
		return new CartDto(cart.getOrderType(), cart.getTotalValue(), cart.getNumOfItems(), items, timeSlot, account);
	}

	private TimeSlotDto convertToDto(TimeSlot t) {
		return new TimeSlotDto(t.getStartDate(), t.getEndDate(), t.getStartTime(), t.getEndTime());
	}

	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

	private PerishableItemDto convertToDto(PerishableItem perishableItem) {
		return new PerishableItemDto(perishableItem.getItemID(), perishableItem.getProductName(),
				perishableItem.getPrice(), perishableItem.getAvailableOnline(), perishableItem.getNumInStock(),
				perishableItem.getPointPerItem());
	}

	private NonPerishableItemDto convertToDto(NonPerishableItem nonPerishableItem) {
		return new NonPerishableItemDto(nonPerishableItem.getItemID(), nonPerishableItem.getProductName(),
				nonPerishableItem.getPrice(), nonPerishableItem.getAvailableOnline(), nonPerishableItem.getNumInStock(),
				nonPerishableItem.getPointPerItem());
	}

}
