package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Order {

	// attribute fields
	private Long orderID;
	private Float totalValue;
	private Date date;
	private Time purchaseTime;

	// association fields
	private Set<Item> items;
	private Account account;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long aOrderID) {
		this.orderID = aOrderID;
	}

	public Float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Float aTotalValue) {
		this.totalValue = aTotalValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date aDate) {
		date = aDate;
	}

	public Time getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Time aTime) {
		this.purchaseTime = aTime;
	}

	@ManyToOne(optional = true)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@OneToMany
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

}