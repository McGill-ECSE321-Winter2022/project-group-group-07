package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.InStoreOrder;

public interface InStoreOrderRepository extends CrudRepository<InStoreOrder, Integer> {

	InStoreOrder findByOrderID(Integer orderID);
}