package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.AccountRole;

public interface AccountRoleRepository extends CrudRepository<AccountRole, Integer> {

}
