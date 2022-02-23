package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

	//attribute fields
	private Integer itemID;
	private String productName;
	private Float price;
	private Boolean availableOnline;
	private Integer numInStock;
	private Integer pointPerItem;


	//getter and setters
	public void setItemID(Integer aItemID) {
		this.itemID = aItemID;
	}
	@Id
	public Integer getItemID() {
		return this.itemID;
	}


	public void setProductName(String aProductName) {
		this.productName = aProductName;
	}

	public String getProductName() {
		return productName;
	}


	public void setPrice(Float aPrice) {
		this.price = aPrice;
	}

	public Float getPrice() {
		return price;
	}


	public void setAvailableOnline(Boolean isAvailableOnline) {
		this.availableOnline = isAvailableOnline;
	}

	public Boolean getAvailableOnline() {
		return availableOnline;
	}


	public void setNumInStock(Integer numInStock) {
		this.numInStock = numInStock;
	}

	public Integer getNumInStock() {
		return numInStock;
	}


	public void setPointPerItem(Integer somePointPerItem) {
		this.pointPerItem = somePointPerItem;
	}

	public Integer getPointPerItem() {
		return pointPerItem;
	}
}
