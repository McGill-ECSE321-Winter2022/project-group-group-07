package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Report {

	// attribute fields
	private Long reportID;
	private Date startDate;
	private Date endDate;
	private Float totalValue;

	// association fields
	private Set<Order> orders;

	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getReportID() {
		return reportID;
	}

	public void setReportID(Long reportIdReplacement) {
		this.reportID = reportIdReplacement;
	}

	public void setStartDate(Date aStartDate) {
		this.startDate = aStartDate;
	}

	public Date getStartDate() {
		return startDate;

	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date aEndDate) {
		this.endDate = aEndDate;
	}

	public Float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Float totalValue) {
		this.totalValue = totalValue;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> newOrders) {
		this.orders = newOrders;
	}

}