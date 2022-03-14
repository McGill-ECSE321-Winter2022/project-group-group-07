package ca.mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.PerishableItem;

public interface PerishableItemRepository extends CrudRepository<PerishableItem, Integer> {

	PerishableItem findByItemID(Integer itemID);

	List<PerishableItem> findByProductName(String productName);
	
	PerishableItem deleteByItemID(Integer itemID);
}