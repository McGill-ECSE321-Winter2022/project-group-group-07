package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {

	Cart findByCartID(Integer cartID);
	
	Cart findByAccount(Account account);
}