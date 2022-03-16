package ca.mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.PickUpOrder;

public interface PickUpOrderRepository extends CrudRepository<PickUpOrder, Integer> {

	PickUpOrder findByOrderID(Integer orderID);
	
	List<PickUpOrder> findByAccount(Account account);
}