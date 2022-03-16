package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.BusinessHour;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Integer> {

	BusinessHour findByBusinessHourID(Long businessHourID);	
}