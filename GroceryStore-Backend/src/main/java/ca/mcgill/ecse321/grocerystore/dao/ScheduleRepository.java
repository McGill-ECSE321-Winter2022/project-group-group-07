package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Employee;
import ca.mcgill.ecse321.grocerystore.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule,Integer>{

    Schedule findByScheduleID(Long scheduleID);

    Schedule findByEmployee(Employee employee);
}