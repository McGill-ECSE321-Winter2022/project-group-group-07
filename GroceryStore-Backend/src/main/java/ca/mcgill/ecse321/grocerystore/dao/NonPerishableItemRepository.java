package ca.mcgill.ecse321.grocerystore.dao;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;

public interface NonPerishableItemRepository extends CrudRepository<NonPerishableItem, Integer> {

	NonPerishableItem findByItemID(Integer itemiID);

	List<NonPerishableItem> findByProductName(String productName);
	
	NonPerishableItem deleteByItemID(Integer itemID);
}