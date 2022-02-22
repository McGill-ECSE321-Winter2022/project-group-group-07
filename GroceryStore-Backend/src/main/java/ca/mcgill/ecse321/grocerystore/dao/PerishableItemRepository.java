package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.grocerystore.model.PerishableItem;

public interface PerishableItemRepository extends CrudRepository<PerishableItem, Integer> {

}