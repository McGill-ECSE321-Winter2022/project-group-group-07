package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
	private Integer itemID;

	public void setItemID(Integer aItemID) {
		this.itemID = aItemID;
	}

	@Id
	public Integer getItemID() {
		return this.itemID;
	}

	private String productName;

	public void setProductName(String aProductName) {
		this.productName = aProductName;
	}

	public String getProductName() {
		return productName;
	}

	private Float price;

	public void setPrice(Float aPrice) {
		this.price = aPrice;
	}

	public Float getPrice() {
		return price;
	}

	private Boolean availableOnline;

	public void setAvailableOnline(Boolean isAvailableOnline) {
		this.availableOnline = isAvailableOnline;
	}

	public Boolean getAvailableOnline() {
		return availableOnline;
	}

	private Integer numInStock;

	public void setNumInStock(Integer numInStock) {
		this.numInStock = numInStock;
	}

	public Integer getNumInStock() {
		return numInStock;
	}

	private Integer pointPerItem;

	public void setPointPerItem(Integer somePointPerItem) {
		this.pointPerItem = somePointPerItem;
	}

	public Integer getPointPerItem() {
		return pointPerItem;
	}
}
