package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account {

	// attribute fields
	private String username;
	private String password;
	private String name;
	private Integer pointBalance;
	// association fields
	private AccountRole accountRole;

	// getters and setters
	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(Integer pointBalance) {
		this.pointBalance = pointBalance;
	}

	@OneToOne(optional = false)
	public AccountRole getAccountRole() {
		return this.accountRole;
	}

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}

}
