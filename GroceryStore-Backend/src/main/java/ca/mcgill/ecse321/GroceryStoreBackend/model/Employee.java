package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Time;

@MappedSuperclass
public abstract class Employee extends UserRole {

	private Date employmentDate;

	public Date getEmploymentDate() {
		return this.employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

}
