package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Terminal {
	//attribute fields
	private Long terminalID;
	//getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getTerminalID() {
		return terminalID;
	}
	public void setTerminalID(Long terminalID) {
		this.terminalID = terminalID;
	}
}
