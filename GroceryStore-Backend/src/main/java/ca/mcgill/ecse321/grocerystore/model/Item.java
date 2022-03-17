package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.ItemCategory;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

	// attribute fields
	private Long itemID;
	private String productName;
	private Float price;
	private Boolean availableOnline;
	private Integer numInStock;
	private Integer pointPerItem;
	private ItemCategory category;

	// getter and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getItemID() {
		return this.itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
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

	public ItemCategory getCategory() {
		return category;
	}

	public void setCategory(ItemCategory category) {
		this.category = category;
	}
}
