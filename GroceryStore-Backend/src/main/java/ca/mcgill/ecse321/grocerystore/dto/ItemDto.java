package ca.mcgill.ecse321.grocerystore.dto;


public abstract class ItemDto {

	private Long itemID;
	private String productName;
	private Float price;
	private Boolean availableOnline;
	private Integer numInStock;
	private Integer pointPerItem;
	private String imageLink;
	private String category;

	/**
	 * @param itemID
	 * @param productName
	 * @param price
	 * @param availableOnline
	 * @param numInStock
	 * @param pointPerItem
	 */
	public ItemDto(Long itemID, String productName, Float price, Boolean availableOnline, Integer numInStock,
			Integer pointPerItem) {
		super();
		this.itemID = itemID;
		this.productName = productName;
		this.price = price;
		this.availableOnline = availableOnline;
		this.numInStock = numInStock;
		this.pointPerItem = pointPerItem;
	}

	public ItemDto(Long itemID, String productName, Float price, Boolean availableOnline, Integer numInStock,
			Integer pointPerItem, String category) {
		super();
		this.itemID = itemID;
		this.productName = productName;
		this.price = price;
		this.availableOnline = availableOnline;
		this.numInStock = numInStock;
		this.pointPerItem = pointPerItem;
		this.category = category;
	}

	public ItemDto(Long itemID, String productName, Float price, Boolean availableOnline, Integer numInStock,
			Integer pointPerItem, String imageLink, String category) {
		super();
		this.itemID = itemID;
		this.productName = productName;
		this.price = price;
		this.availableOnline = availableOnline;
		this.numInStock = numInStock;
		this.pointPerItem = pointPerItem;
		this.imageLink = imageLink;
		this.category = category;
	}

	/**
	 * @return the itemID
	 */
	public Long getItemID() {
		return itemID;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}

	/**
	 * @return the availableOnline
	 */
	public Boolean getAvailableOnline() {
		return availableOnline;
	}

	/**
	 * @return the numInStock
	 */
	public Integer getNumInStock() {
		return numInStock;
	}

	/**
	 * @return the pointPerItem
	 */
	public Integer getPointPerItem() {
		return pointPerItem;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public String getImageLink() {
		return imageLink;
	}

}
