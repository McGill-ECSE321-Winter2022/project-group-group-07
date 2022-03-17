package ca.mcgill.ecse321.grocerystore.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.NonPerishableItem;

public interface NonPerishableItemRepository extends CrudRepository<NonPerishableItem, Integer> {

	NonPerishableItem findByItemID(Long nonPerishableID);

	List<NonPerishableItem> findByProductName(String productName);
}