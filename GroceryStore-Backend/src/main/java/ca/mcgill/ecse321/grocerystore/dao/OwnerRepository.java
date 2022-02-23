package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.grocerystore.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Integer> {
	
	Owner findByRoleID(Integer roleID);
}