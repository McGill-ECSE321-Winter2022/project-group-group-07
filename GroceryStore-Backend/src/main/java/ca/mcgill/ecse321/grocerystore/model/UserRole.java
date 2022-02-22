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
public abstract class UserRole {

	private Integer roleID;

	@Id
	public Integer getRoleID() {
		return this.roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

}
