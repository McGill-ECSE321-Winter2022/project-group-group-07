package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;

public interface NonPerishableItemRepository extends CrudRepository<NonPerishableItem, Integer> {

	NonPerishableItem findByItemID(Integer itemiID);

	NonPerishableItem findByProductName(String productName);
}