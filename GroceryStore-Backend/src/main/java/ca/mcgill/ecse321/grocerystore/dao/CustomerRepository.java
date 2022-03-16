package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	Customer findByRoleID(Long roleID);
}