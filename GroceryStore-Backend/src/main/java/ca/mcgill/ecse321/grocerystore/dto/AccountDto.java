package ca.mcgill.ecse321.grocerystore.dto;

import java.util.Collections;
import java.util.List;

public class AccountDto {

	// attributes
	private String username;
	private String name;
	private Integer pointBalance;
	private String role;

	private List<OrderDto> orders;
	private AddressDto address;

	/**
	 * @param username
	 * @param name
	 * @param pointBalance
	 * @param orders
	 * @param address
	 * @param role
	 */
	public AccountDto(String username, String name, Integer pointBalance, List<OrderDto> orders, AddressDto address,
			String role) {

		this.username = username;
		this.name = name;
		this.pointBalance = pointBalance;
		this.orders = orders;
		this.address = address;
		this.role = role;
	}

	/**
	 * @param username
	 * @param name
	 * @param pointBalance
	 * @param address
	 * @param role
	 */
	@SuppressWarnings("unchecked")
	public AccountDto(String username, String name, Integer pointBalance, AddressDto address, String role) {

		this(username, name, pointBalance, Collections.EMPTY_LIST, address, role);
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
	 * @return the orders
	 */
	public List<OrderDto> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}

	/**
	 * @return the address
	 */
	public AddressDto getAddress() {
		return address;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
}
