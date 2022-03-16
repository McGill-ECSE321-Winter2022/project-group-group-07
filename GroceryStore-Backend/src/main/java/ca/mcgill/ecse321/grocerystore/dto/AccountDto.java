package ca.mcgill.ecse321.grocerystore.dto;

public class AccountDto {

	// attributes
	private String username;
	private String name;
	private Integer pointBalance;
	private String role;

	/**
	 * @param username
	 * @param name
	 * @param pointBalance
	 * @param role
	 */
	public AccountDto(String username, String name, Integer pointBalance, String role) {
		this.username = username;
		this.name = name;
		this.pointBalance = pointBalance;
		this.role = role;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the pointBalance
	 */
	public Integer getPointBalance() {
		return pointBalance;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

}
