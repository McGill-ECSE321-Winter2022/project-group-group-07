package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Order {

	//attribute fields
	private Integer orderID;
	private Float totalValue;
	private Date date;
	private Time purchaseTime;

	//association fields
	private Set<Item> items;
	private Account account;

	@ManyToOne(optional = true)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setOrderID(Integer aOrderID) {
		this.orderID = aOrderID;
	}

	public void setTotalValue(Float aTotalValue) {
		this.totalValue = aTotalValue;
	}

	public void setDate(Date aDate) {
		date = aDate;
	}

	public void setPurchaseTime(Time aTime) {
		this.purchaseTime = aTime;
	}



	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> itemss) {
		this.items = itemss;
	}

	@Id
	public Integer getOrderID() {
		return orderID;
	}

	public Float getTotalValue() {
		return totalValue;
	}

	public Date getDate() {
		return date;
	}

	public Time getPurchaseTime() {
		return purchaseTime;
	}

}