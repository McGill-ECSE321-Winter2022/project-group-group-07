package ca.mcgill.ecse321.grocerystore.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Cashier;

public interface CashierRepository extends CrudRepository<Cashier, Integer> {
	
	
	
}