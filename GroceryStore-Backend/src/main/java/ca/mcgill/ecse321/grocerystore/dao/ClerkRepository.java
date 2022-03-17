package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.grocerystore.model.Clerk;

public interface ClerkRepository extends CrudRepository<Clerk, Integer> {
	
	Clerk findByRoleID(Long roleID);
}