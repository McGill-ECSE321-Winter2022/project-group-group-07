package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Terminal;

public interface TerminalRepository extends CrudRepository<Terminal,Integer>{
	
	Terminal findByTerminalID(Long terminalID);
}