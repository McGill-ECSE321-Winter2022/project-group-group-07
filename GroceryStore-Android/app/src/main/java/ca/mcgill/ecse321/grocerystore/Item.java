package ca.mcgill.ecse321.grocerystore;

public class Item {
    private String itemID;
    private String productName;
    private String price;

    public Item(String itemID, String productName, String price) {
        this.itemID = itemID;
        this.productName = productName;
        this.price = price;
    }
    public Item(){

    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
