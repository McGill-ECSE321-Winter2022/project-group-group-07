package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	Order findByOrderID(Long orderID);
}