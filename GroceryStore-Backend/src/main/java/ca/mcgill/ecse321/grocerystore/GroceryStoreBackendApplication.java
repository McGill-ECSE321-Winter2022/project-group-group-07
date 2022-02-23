package ca.mcgill.ecse321.grocerystore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class GroceryStoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryStoreBackendApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello world!";
	}

}