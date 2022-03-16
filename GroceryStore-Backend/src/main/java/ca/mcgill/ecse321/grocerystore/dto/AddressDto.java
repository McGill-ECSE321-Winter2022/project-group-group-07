package ca.mcgill.ecse321.grocerystore.dto;

public class AddressDto {

	// attributes

	private Integer buildingNo;
	private String street;
	private String town;
	private AccountDto account;

	// constructor
	public AddressDto() {
	}

	public AddressDto(Integer buildingNo, String street, String town, AccountDto account) {
		this.buildingNo = buildingNo;
		this.street = street;
		this.town = town;
		this.account = account;
	}

	//getter
	public Integer getBuildingNo() {
		return this.buildingNo;
	}

	public String getStreet() {
		return this.street;
	}

	public String getTown() {
		return this.town;
	}
	
	public AccountDto getUsername() {
		return account;
	}


	
}