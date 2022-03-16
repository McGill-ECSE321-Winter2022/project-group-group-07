package ca.mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.grocerystore.model.Store;

public interface StoreRepository extends CrudRepository<Store,Integer>{

		Store findByStoreID(Long storeID);	
}