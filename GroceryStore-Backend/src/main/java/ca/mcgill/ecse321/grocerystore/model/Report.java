package ca.mcgill.ecse321.grocerystore.model;

import java.sql.Date;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Report {

	private Integer reportID;
	private Date startDate;
	private Date endDate;
	private Float totalValue;

	public void setReportID(Integer reportIdReplacement) {
		this.reportID = reportIdReplacement;
	}

	public void setStartDate(Date aStartDate) {
		this.startDate = aStartDate;
	}

	public void setEndDate(Date aEndDate) {
		this.endDate = aEndDate;
	}

	public void setTotalValue(Float totalValue) {
		this.totalValue = totalValue;
	}

	@Id
	public Integer getReportID() {
		return reportID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Float getTotalValue() {
		return totalValue;
	}

	private Set<Order> orders;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> newOrders) {
		this.orders = newOrders;
	}

}