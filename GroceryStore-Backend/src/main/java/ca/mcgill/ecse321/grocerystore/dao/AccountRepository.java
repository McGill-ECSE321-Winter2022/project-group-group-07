package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;

public interface AccountRepository extends CrudRepository<Account,String> {
	
	Account findByUsername(String username);
	
	Account findByUsernameAndPassword(String username, String password);
	
	Account findByName(String name);
	
	Account findByAccountRole(AccountRole accountRole);
}
