package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.DeliveryOrder;

public interface DeliveryOrderRepository extends CrudRepository<DeliveryOrder, Integer> {

	DeliveryOrder findByOrderID(Integer orderID);
		
}