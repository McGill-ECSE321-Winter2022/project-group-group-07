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

import ca.mcgill.ecse321.grocerystore.dto.ItemDto;
import ca.mcgill.ecse321.grocerystore.dto.NonPerishableItemDto;
import ca.mcgill.ecse321.grocerystore.dto.PerishableItemDto;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.ItemCategory;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/item")
public class ItemController {

	@Autowired
	private GroceryStoreService service;

	@PostMapping(value = { "/perishable/", "/perishable" })
	public ResponseEntity<?> createPerishableItem(@RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock, @RequestParam Integer pointPerItem,
			@RequestParam String imageLink, @RequestParam String category) {
		try {
			PerishableItem perishableItem = service.createPerishableItem(productName, price, availableOnline,
					numInStock, pointPerItem, imageLink, ItemCategory.valueOf(category));
			return new ResponseEntity<>(convertToDto(perishableItem), HttpStatus.OK);
		} catch (Exception e) {
			String message = e.getMessage();
			String[] array = message.split("!");
			return new ResponseEntity<>(array[0], HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = { "/nonperishable", "/nonperishable/" })
	public ResponseEntity<?> createNonPerishableItem(@RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock, @RequestParam Integer pointPerItem,
			@RequestParam String imageLink, @RequestParam String category) {
		try {
			NonPerishableItem nonPerishableItem = service.createNonPerishableItem(productName, price, availableOnline,
					numInStock, pointPerItem,imageLink, ItemCategory.valueOf(category));
			return new ResponseEntity<>(convertToDto(nonPerishableItem), HttpStatus.OK);
		} catch (Exception e) {
			String message = e.getMessage();
			String[] array = message.split("!");
			return new ResponseEntity<>(array[0], HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = { "/items", "/items/" })
	public ResponseEntity<?> getAllItems() {
		List<ItemDto> items = new ArrayList<ItemDto>();

		for (PerishableItem i : service.getAllPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		for (NonPerishableItem i : service.getAllNonPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return new ResponseEntity<>(items, HttpStatus.OK);

	}

	@GetMapping(value = { "/perishableitems", "/perishableitems/" })
	public ResponseEntity<?> getAllPerishableItems() {
		List<ItemDto> items = new ArrayList<ItemDto>();

		for (PerishableItem i : service.getAllPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping(value = { "/nonperishableitems", "/nonperishableitems/" })
	public ResponseEntity<?> getAllNonPerishableItems() {

		List<ItemDto> items = new ArrayList<ItemDto>();
		for (NonPerishableItem i : service.getAllNonPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@GetMapping(value = { "/items/id:{id}", "/items/id:{id}/" })

	public ResponseEntity<?> getItemByID(@PathVariable("id") String id) {
		Long ID = Long.parseLong(id);
		PerishableItem pitems = null;
		NonPerishableItem npitems = null;
		try {

			pitems = service.getPerishableItemByID(ID);
		} catch (Exception e) {
		}
		try {
			npitems = service.getNonPerishableItemByID(ID);
		} catch (Exception e) {
		}
		ItemDto itemsDto = null;
		if (pitems == null && npitems == null) {
			return new ResponseEntity<>("There is no item with the given ID.", HttpStatus.BAD_REQUEST);
		} else if (pitems != null) {
			itemsDto = convertToDto(pitems);
		} else {
			itemsDto = convertToDto(npitems);
		}

		return new ResponseEntity<>(itemsDto, HttpStatus.OK);
	}

	@GetMapping(value = { "/items/name:{name}", "/items/name:{name}/" })
	public ResponseEntity<?> getItemsByName(@PathVariable("name") String name) throws IllegalArgumentException {
		List<PerishableItem> pitems = null;
		List<NonPerishableItem> npitems = null;
		try {
			pitems = service.getPerishableItemsByProductName(name);
		} catch (Exception e) {
		}
		try {
			npitems = service.getNonPerishableItemsByProductName(name);
		} catch (Exception e) {
		}
		List<ItemDto> items = new ArrayList<ItemDto>();
		if (pitems != null) {
			for (PerishableItem p : pitems) {
				items.add(convertToDto(p));
			}
		}
		if (npitems != null) {
			for (NonPerishableItem p : npitems) {
				items.add(convertToDto(p));
			}
		}
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deleteItems/{id}", "/deleteItems/{id}/" })
	public void deleteItemByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Long ID = Long.parseLong(id);
		PerishableItem pitem = null;
		NonPerishableItem npitem = null;
		try {
			pitem = service.getPerishableItemByID(ID);
		} catch (Exception e) {

		}
		try {
			npitem = service.getNonPerishableItemByID(ID);
		} catch (Exception e) {

		}
		if (pitem != null) {
			service.deletePerishableItem(pitem);

		} else {
			service.deleteNonPerishableItem(npitem);
		}
	}

	@PutMapping(value = { "/items/{id}", "/items/{id}/" })
	public ResponseEntity<?> updateItem(@PathVariable String id, @RequestParam String productName,
			@RequestParam Float price, @RequestParam Boolean availableOnline, @RequestParam Integer numInStock,
			@RequestParam Integer pointPerItem) {
		Long ID = Long.parseLong(id);
		PerishableItem pitems = null;
		NonPerishableItem npitems = null;
		try {
			pitems = service.getPerishableItemByID(ID);
		} catch (Exception e) {
		}
		try {
			npitems = service.getNonPerishableItemByID(ID);
		} catch (Exception e) {
		}
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item!");
		} else if (pitems != null) {
			PerishableItem perishableItemToUpdate = service.getPerishableItemByID(ID);
			perishableItemToUpdate = service.updatePerishableItem(perishableItemToUpdate, productName, price,
					availableOnline, numInStock, pointPerItem);
			PerishableItemDto updatedPerishableItem = convertToDto(perishableItemToUpdate);
			return new ResponseEntity<>(updatedPerishableItem, HttpStatus.OK);
		} else {
			NonPerishableItem nonPerishableItemToUpdate = service.getNonPerishableItemByID(ID);
			nonPerishableItemToUpdate = service.updateNonPerishableItem(nonPerishableItemToUpdate, productName, price,
					availableOnline, numInStock, pointPerItem);
			NonPerishableItemDto nonUpdatedPerishableItem = convertToDto(nonPerishableItemToUpdate);
			return new ResponseEntity<>(nonUpdatedPerishableItem, HttpStatus.OK);
		}

	}

	private PerishableItemDto convertToDto(PerishableItem perishableItem) {
		return new PerishableItemDto(perishableItem.getItemID(), perishableItem.getProductName(),
				perishableItem.getPrice(), perishableItem.getAvailableOnline(), perishableItem.getNumInStock(),
				perishableItem.getPointPerItem(), perishableItem.getImageLink(),
				perishableItem.getCategory().toString());
	}

	private NonPerishableItemDto convertToDto(NonPerishableItem nonPerishableItem) {
		return new NonPerishableItemDto(nonPerishableItem.getItemID(), nonPerishableItem.getProductName(),
				nonPerishableItem.getPrice(), nonPerishableItem.getAvailableOnline(), nonPerishableItem.getNumInStock(),
				nonPerishableItem.getPointPerItem(), nonPerishableItem.getImageLink(),
				nonPerishableItem.getCategory().toString());
	}

}
