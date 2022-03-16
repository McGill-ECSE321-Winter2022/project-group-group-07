package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {

	// attribute fields
	private Long addressID;
	private Integer buildingNo;
	private String street;
	private String town;

	// association fields
	private Account account;

	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getAddressID() {
		return addressID;
	}

	public void setAddressID(Long addressID) {
		this.addressID = addressID;
	}

	public Integer getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(Integer buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@OneToOne(optional = false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
