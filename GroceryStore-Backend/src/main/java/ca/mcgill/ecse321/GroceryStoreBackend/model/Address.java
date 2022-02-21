package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Address {
    private int addressID;
    private int buildingNo;
    private String street;
    private String town;

    @Id
    public int getAddressID(){
        return addressID;
    }

    public void setAddressID(int addressID){
        this.addressID = addressID;
    }

    public int getBuildingNo(){
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo){
        this.buildingNo = buildingNo;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getTown(){
        return town;
    }

    public void setTown(String town){
        this.town = town;
    }

    //TODO: Uncomment when Class User is added
    private User user;

    @OneToOne(optional = false)
    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

}
