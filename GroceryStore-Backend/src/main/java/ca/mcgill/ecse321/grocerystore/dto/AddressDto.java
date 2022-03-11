package ca.mcgill.ecse321.grocerystore.dto;

public class AddressDto {

	// attributes
	private Integer buildingNo;
	private String street;
	private String town;

	// constructor
	public AddressDto() {
	}

	public AddressDto(Integer buildingNo, String street, String town) {
		this.buildingNo = buildingNo;
		this.street = street;
		this.town = town;
	}

	// getter
	public Integer getBuildingNo() {
		return this.buildingNo;
	}

	public String getStreet() {
		return this.street;
	}

	public String getTown() {
		return this.town;
	}
}