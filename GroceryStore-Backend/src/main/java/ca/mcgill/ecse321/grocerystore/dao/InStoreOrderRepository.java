package ca.mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.InStoreOrder;

public interface InStoreOrderRepository extends CrudRepository<InStoreOrder, Integer> {

	InStoreOrder findByOrderID(Long orderID);
	
	List<InStoreOrder> findByAccount(Account account);
}