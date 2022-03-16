package ca.mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.PerishableItem;

public interface PerishableItemRepository extends CrudRepository<PerishableItem, Integer> {

	PerishableItem findByItemID(Long perishableID);

	List<PerishableItem> findByProductName(String productName);
}