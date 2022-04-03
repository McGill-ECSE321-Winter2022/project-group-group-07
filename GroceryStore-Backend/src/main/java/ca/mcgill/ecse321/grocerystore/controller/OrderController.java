package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.AccountDto;
import ca.mcgill.ecse321.grocerystore.dto.DeliveryOrderDto;
import ca.mcgill.ecse321.grocerystore.dto.InStoreOrderDto;
import ca.mcgill.ecse321.grocerystore.dto.ItemDto;
import ca.mcgill.ecse321.grocerystore.dto.NonPerishableItemDto;
import ca.mcgill.ecse321.grocerystore.dto.OrderDto;
import ca.mcgill.ecse321.grocerystore.dto.PerishableItemDto;
import ca.mcgill.ecse321.grocerystore.dto.PickUpOrderDto;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystore.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.Order;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PickUpOrder;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private GroceryStoreService service;

	@GetMapping(value = { "/order/{username}", "/order/{username}/" })
	public List<OrderDto> getOrdersByAccount(@PathVariable("username") String username) {
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (Order order : service.getDeliveryOrdersByAccount(service.getAccount(username))) {
			orders.add(convertToDto(order));
		}
		for (Order order : service.getInStoreOrdersByAccount(service.getAccount(username))) {
			orders.add(convertToDto(order));
		}
		for (Order order : service.getPickUpOrdersByAccount(service.getAccount(username))) {
			orders.add(convertToDto(order));
		}
		return orders;
	}
	@GetMapping(value = { "/pickUpOrders", "/pickUpOrders/" })
	public List<OrderDto> getAllPickUpOrders() {
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (Order order : service.getAllPickUpOrders()) {
			orders.add(convertToDto(order));
		}
		return orders;
	}
	@GetMapping(value = { "/deliveryOrders", "/deliveryOrders/" })
	public List<OrderDto> getAllDeliveryOrders() {
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (Order order : service.getAllDeliveryOrders()) {
			orders.add(convertToDto(order));
		}
		return orders;
	}
	@GetMapping(value = { "/order/{id}", "/order/{id}/" })
	public OrderDto getOrderByID(@PathVariable("id") Long id) {
		Order order = service.getOrderById(id);
		return convertToDto(order);
	}

	@PostMapping(value = { "/createInPersonOrder", "/createInPersonOrder/" })
	public InStoreOrderDto createInStoreOrder(@RequestParam Date date, @RequestParam Time purchaseTime,
			@RequestParam Set<Item> items) {
		return (InStoreOrderDto) convertToDto(service.createInStoreOrder(date, purchaseTime, items));
	}

	@PostMapping(value = { "/createInPersonOrder/{username}", "/createInPersonOrder/{username}/" })
	public InStoreOrderDto createInStoreOrder(@RequestParam Date date, @RequestParam Time purchaseTime,
			@RequestParam Set<Item> items, @PathVariable("username") String username) {
		return (InStoreOrderDto) convertToDto(
				service.createInStoreOrder(date, purchaseTime, items, service.getAccount(username)));
	}

	@PostMapping(value = { "/createDeliveryOrder/{username}", "/createDeliveryOrder/{username}/" })
	public DeliveryOrderDto createDeliveryOrder(@PathVariable("username") String username, @RequestParam Date date,
			@RequestParam Time purchaseTime, @RequestParam Set<Item> items, @RequestParam Date startDate,
			@RequestParam Date endDate, @RequestParam Time startTime, @RequestParam Time endTime) {
		return (DeliveryOrderDto) convertToDto(service.createDeliveryOrder(date, purchaseTime, items,
				service.createTimeSlot(startDate, endDate, startTime, endTime), service.getAccount(username)));
	}

	@PostMapping(value = { "/createPickUpOrder/{username}", "/createPickUpOrder/{username}/" })
	public PickUpOrderDto createPickUpOrder(@RequestParam Date date, @RequestParam Time purchaseTime,
			@RequestParam Set<Item> items, @RequestParam Date startDate, @RequestParam Date endDate,
			@RequestParam Time startTime, @RequestParam Time endTime, @PathVariable("username") String username) {
		return (PickUpOrderDto) convertToDto(service.createPickUpOrder(date, purchaseTime, items,
				service.createTimeSlot(startDate, endDate, startTime, endTime), service.getAccount(username)));
	}

	@PutMapping(value = { "/deliveryUpdate/{id}", "/deliveryUpdate/{id}/" })
	public DeliveryOrderDto updateDeliveryStatus(@PathVariable("id") long id, @RequestParam String status) {
		return (DeliveryOrderDto) convertToDto(
				service.updateDeliveryOrderStatus((DeliveryOrder) service.getOrderById(id), status));
	}

	@PutMapping(value = { "/PickUpUpdate/{id}", "/PickUpUpdate/{id}/" })
	public PickUpOrderDto updatePickUpStatus(@PathVariable("id") long id, @RequestParam String status) {
		return (PickUpOrderDto) convertToDto(
				service.updatePickUpOrderStatus((PickUpOrder) service.getOrderById(id), status));
	}

	@PostMapping(value = { "/checkout/{username}", "/checkout/{username}/" })
	public OrderDto checkout(@PathVariable("username") String username) {
		return convertToDto(service.checkout(username));
	}

	private OrderDto convertToDto(Order order) {
		Long orderID = order.getOrderID();
		Float totalValue = order.getTotalValue();
		Date date = order.getDate();
		Time purchaseTime = order.getPurchaseTime();
		AccountDto account = convertToDto(order.getAccount(), order.getAccount().getAccountRole());
		TimeSlot timeSlot = new TimeSlot();
		if (order instanceof DeliveryOrder)
			timeSlot = ((DeliveryOrder) order).getTimeSlot();
		if (order instanceof PickUpOrder)
			timeSlot = ((PickUpOrder) order).getTimeSlot();
		List<ItemDto> items = new ArrayList<ItemDto>();
		if (order.getItems() != null && order.getItems().size() > 0) {
			for (Item i : order.getItems()) {
				if (i instanceof PerishableItem) {
					items.add(convertToDto((PerishableItem) i));
				} else {
					items.add(convertToDto((NonPerishableItem) i));
				}
			}
		}
		if (order instanceof PickUpOrder) {
			if (order.getItems() != null && order.getItems().size() > 0) {
				if (order.getAccount() == null) {
					return new PickUpOrderDto(orderID, totalValue, date, purchaseTime, items, timeSlot);
				} else {
					return new PickUpOrderDto(orderID, totalValue, date, purchaseTime, account, items, timeSlot);
				}
			} else {
				return new PickUpOrderDto(orderID, totalValue, date, purchaseTime, account, timeSlot);
			}
		} else if (order instanceof InStoreOrder) {
			if (order.getItems() != null && order.getItems().size() > 0) {
				if (order.getAccount() == null) {
					return new InStoreOrderDto(orderID, totalValue, date, purchaseTime, items);
				} else {
					return new InStoreOrderDto(orderID, totalValue, date, purchaseTime, account, items);
				}
			} else {
				return new InStoreOrderDto(orderID, totalValue, date, purchaseTime, account);
			}
		} else {
			if (order.getItems() != null && order.getItems().size() > 0) {
				if (order.getAccount() == null) {
					return new DeliveryOrderDto(orderID, totalValue, date, purchaseTime, items, timeSlot);
				} else {
					return new DeliveryOrderDto(orderID, totalValue, date, purchaseTime, account, items, timeSlot);
				}
			} else {
				return new DeliveryOrderDto(orderID, totalValue, date, purchaseTime, account, timeSlot);
			}
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

	private AccountDto convertToDto(Account account, AccountRole role) {

		return new AccountDto(account.getUsername(), account.getName(), account.getPointBalance(), role.toString());
	}

}
