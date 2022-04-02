package ca.mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import ca.mcgill.ecse321.grocerystore.dto.ReportDto;
import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;
import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;
import ca.mcgill.ecse321.grocerystore.model.InStoreOrder;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;
import ca.mcgill.ecse321.grocerystore.model.Order;
import ca.mcgill.ecse321.grocerystore.model.PerishableItem;
import ca.mcgill.ecse321.grocerystore.model.PickUpOrder;
import ca.mcgill.ecse321.grocerystore.model.Report;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private GroceryStoreService service;

	@GetMapping(value = { "/reports", "/reports/" })
	public List<ReportDto> getAllReports() {
		List<ReportDto> reports = new ArrayList<ReportDto>();
		for (Report report : service.getAllReports()) {
			reports.add(convertToDto(report));
		}
		return reports;
	}

	@GetMapping(value = { "/report/{id}", "/report/{id}/" })
	public ReportDto getReportByID(@PathVariable("id") Long id) {
		Report report = service.getReportById(id);
		return convertToDto(report);
	}

	@PostMapping(value = { "/report", "/report/" })
	public ReportDto createReport(@RequestParam Date startDate, @RequestParam Date endDate)
			throws IllegalArgumentException {
		Report report = service.createReport(startDate, endDate);
		return convertToDto(report);
	}

	private ReportDto convertToDto(Report report) {
		List<OrderDto> orders = new ArrayList<OrderDto>();
		for (Order ord : report.getOrders()) {
			if (ord instanceof PickUpOrder) {
				orders.add((PickUpOrderDto) convertToDto(ord));
			} else if (ord instanceof InStoreOrder) {
				orders.add((InStoreOrderDto) convertToDto(ord));
			} else {
				orders.add((DeliveryOrderDto) convertToDto(ord));
			}
		}
		return new ReportDto(report.getReportID(), report.getStartDate(), report.getEndDate(), report.getTotalValue(),
				orders);
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