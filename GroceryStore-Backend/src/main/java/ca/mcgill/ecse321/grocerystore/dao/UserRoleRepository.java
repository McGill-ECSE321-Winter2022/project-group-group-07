package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole,Integer> {

}
