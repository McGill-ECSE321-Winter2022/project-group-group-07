package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot,Integer>{

	TimeSlot findByTimeSlotID(Long timeSlotID);
}
