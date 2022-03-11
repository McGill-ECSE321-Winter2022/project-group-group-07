package ca.mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer> {

	DeliveryOrder findByOrderID(Integer orderID);
	
	List<DeliveryOrder> findByAccount(Account account);
}