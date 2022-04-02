package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public PerishableItemDto createPerishableItem(@RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock, @RequestParam Integer pointPerItem)
			throws IllegalArgumentException {

		PerishableItem perishableItem = service.createPerishableItem(productName, price, availableOnline, numInStock,
				pointPerItem);

		return convertToDto(perishableItem);
	}

	@PostMapping(value = { "/nonperishable", "/nonperishable/" })
	public NonPerishableItemDto createNonPerishableItem(@RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock, @RequestParam Integer pointPerItem)
			throws IllegalArgumentException {

		NonPerishableItem nonPerishableItem = service.createNonPerishableItem(productName, price, availableOnline,
				numInStock, pointPerItem);

		return convertToDto(nonPerishableItem);
	}

	@GetMapping(value = { "/items", "/items/" })
	public List<ItemDto> getAllItems() {
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
		return items;

	}

	@GetMapping(value = { "/perishableitems", "/perishableitems/" })
	public List<ItemDto> getAllPerishableItems() {
		List<ItemDto> items = new ArrayList<ItemDto>();

		for (PerishableItem i : service.getAllPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;
	}

	@GetMapping(value = { "/nonperishableitems", "/nonperishableitems/" })
	public List<ItemDto> getAllNonPerishableItems() {

		List<ItemDto> items = new ArrayList<ItemDto>();
		for (NonPerishableItem i : service.getAllNonPerishableItems()) {
			if (i != null) {
				items.add(convertToDto(i));
			}
		}
		return items;
	}

	@GetMapping(value = { "/items/id:{id}", "/items/id:{id}/" })
	public ItemDto getItemsByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Long ID = Long.parseLong(id);
		PerishableItem pitems = null;
		NonPerishableItem npitems = null;
		try {
			pitems = service.getPerishableItemsByID(ID);
		} catch (Exception e) {
		}
		try {
			npitems = service.getNonPerishableItemsByID(ID);
		} catch (Exception e) {
		}
		ItemDto itemsDto = null;
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item to get!");
		} else if (pitems != null) {
			itemsDto = convertToDto(pitems);
		} else {
			itemsDto = convertToDto(npitems);
		}

		return itemsDto;
	}

	@GetMapping(value = { "/items/name:{name}", "/items/name:{name}/" })
	public List<ItemDto> getItemsByName(@PathVariable("name") String name) throws IllegalArgumentException {
		List<PerishableItem> pitems = service.getPerishableItemsByProductName(name);
		List<NonPerishableItem> npitems = service.getNonPerishableItemsByProductName(name);
		List<ItemDto> items = new ArrayList<ItemDto>();
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item to get!");
		} else if (pitems != null) {
			for (PerishableItem p : pitems) {
				items.add(convertToDto(p));

			}
		} else {
			for (NonPerishableItem p : npitems) {
				items.add(convertToDto(p));

			}
		}

		return items;
	}

	@DeleteMapping(value = { "/deleteItems/{id}", "/deleteItems/{id}/" })
	public ItemDto deleteItemsByID(@PathVariable("id") String id) throws IllegalArgumentException {
		Long ID = Long.parseLong(id);

		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);

		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item to delete!");
		} else if (pitems != null) {
			PerishableItem deletedPitems = service.deletePerishableItems(pitems);
			return convertToDto(deletedPitems);
		} else {
			NonPerishableItem deletedNPitems = service.deleteNonPerishableItems(npitems);
			return convertToDto(deletedNPitems);
		}
	}

	@PutMapping(value = { "/items/{id}", "/items/{id}/" })
	public ItemDto updateItem(@PathVariable String id, @RequestParam String productName, @RequestParam Float price,
			@RequestParam Boolean availableOnline, @RequestParam Integer numInStock,
			@RequestParam Integer pointPerItem) {
		Long ID = Long.parseLong(id);
		PerishableItem pitems = service.getPerishableItemsByID(ID);
		NonPerishableItem npitems = service.getNonPerishableItemsByID(ID);
		if (pitems == null && npitems == null) {
			throw new IllegalArgumentException("There is no such Item!");
		} else if (pitems != null) {
			PerishableItem perishableItemToUpdate = service.getPerishableItemsByID(ID);
			perishableItemToUpdate = service.updatePerishableItem(perishableItemToUpdate, productName, price,
					availableOnline, numInStock, pointPerItem);
			PerishableItemDto updatedPerishableItem = convertToDto(perishableItemToUpdate);
			return updatedPerishableItem;
		} else {
			NonPerishableItem nonPerishableItemToUpdate = service.getNonPerishableItemsByID(ID);
			nonPerishableItemToUpdate = service.updateNonPerishableItem(nonPerishableItemToUpdate, productName, price,
					availableOnline, numInStock, pointPerItem);
			NonPerishableItemDto nonUpdatedPerishableItem = convertToDto(nonPerishableItemToUpdate);
			return nonUpdatedPerishableItem;
		}

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
