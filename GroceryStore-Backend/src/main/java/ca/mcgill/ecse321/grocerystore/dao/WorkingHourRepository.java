package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.WorkingHour;

public interface WorkingHourRepository extends CrudRepository<WorkingHour, Integer> {
	
	WorkingHour findByWorkingHourID(Long workingHourID);
}
