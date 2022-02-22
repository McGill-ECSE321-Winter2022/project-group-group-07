package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee extends UserRole {

	private Date employmentDate;

	public Date getEmploymentDate() {
		return this.employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

}
