package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.DeliveryPerson;

public interface DeliveryPersonRepository extends CrudRepository<DeliveryPerson, Integer> {
	
	
	
}