package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {

	Item findByItemID(Integer itemId);

	Item findByProductName(String productName);
}