package ca.mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.grocerystore.dto.TerminalDto;
import ca.mcgill.ecse321.grocerystore.model.Terminal;
import ca.mcgill.ecse321.grocerystore.service.GroceryStoreService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/terminal")
public class TerminalController {


	@Autowired
	private GroceryStoreService service;

	@GetMapping(value = { "/terminals", "/terminals/" })
	public ResponseEntity<?> getAllTerminals() {

		List<TerminalDto> terminals = new ArrayList<TerminalDto>();
		for (Terminal t : service.getAllTerminals()) {
			terminals.add(convertToDto(t));
		}

		return new ResponseEntity<>(terminals, HttpStatus.OK);

	}

	@PostMapping(value = { "/terminal", "/terminal/" })
	public ResponseEntity<?> createTerminal() {

		return new ResponseEntity<>(convertToDto(service.createTerminal()), HttpStatus.OK);
	}

	@DeleteMapping(value = { "/deleteTerminal/{id}", "/deleteTerminal/{id}/" })
	public ResponseEntity<?> deleteTerminalbyID(@PathVariable("id") Long terminalID) {
		try {
			service.deleteTerminal(terminalID);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	private TerminalDto convertToDto(Terminal terminal) {
		return new TerminalDto(terminal.getTerminalID());
	}


}
