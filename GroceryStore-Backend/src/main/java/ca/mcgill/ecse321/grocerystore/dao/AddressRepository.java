package ca.mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	Address findByAddressID(Long addressID);
	
	Address findByAccount(Account account);
	
	List<Address> findByTown(String town);
}