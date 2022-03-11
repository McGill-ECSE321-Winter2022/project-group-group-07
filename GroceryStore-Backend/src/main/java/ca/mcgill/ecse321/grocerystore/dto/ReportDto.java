package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.util.Set;

import ca.mcgill.ecse321.grocerystore.model.Order;

public class ReportDto {
	private Integer reportID;
	private Date startDate;
	private Date endDate;
	private Float totalValue;

	//association fields
	private Set<Order> orders;

	
	/**
	 * @param reportID
	 * @param startDate
	 * @param endDate
	 * @param totalValue
	 * @param orders
	 */
	public ReportDto(Integer reportID, Date startDate, Date endDate, Float totalValue, Set<Order> orders) {
		this.reportID = reportID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalValue = totalValue;
		this.orders = orders;
	}
	
	
	public ReportDto() {
		super();
	}

	/**
	 * @return the reportID
	 */
	public Integer getReportID() {
		return reportID;
	}

//	/**
//	 * @param reportID the reportID to set
//	 */
//	public void setReportID(Integer reportID) {
//		this.reportID = reportID;
//	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

//	/**
//	 * @param startDate the startDate to set
//	 */
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

//	/**
//	 * @param endDate the endDate to set
//	 */
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}

	/**
	 * @return the totalValue
	 */
	public Float getTotalValue() {
		return totalValue;
	}

//	/**
//	 * @param totalValue the totalValue to set
//	 */
//	public void setTotalValue(Float totalValue) {
//		this.totalValue = totalValue;
//	}

	/**
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return orders;
	}

//	/**
//	 * @param orders the orders to set
//	 */
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}

	
	
}
