package ca.mcgill.ecse321.grocerystore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.model.Account;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class GroceryStoreBackendApplication {
	
	public static Account currUser;

	public static Account getCurrUser() {
		return currUser;
	}

	public static void setCurrUser(Account currUser) {
		GroceryStoreBackendApplication.currUser = currUser;
	}

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreBackendApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

}