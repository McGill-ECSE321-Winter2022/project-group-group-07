package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;
import java.sql.Time;

@MappedSuperclass
public abstract class UserRole {
	
	private Long roleID;
	
	@Id
	public Long getID() {
		return this.roleID;
	}
	
	public void setId(Long id) {
		this.roleID=id;
	}

}
