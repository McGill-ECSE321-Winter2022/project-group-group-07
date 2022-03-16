package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AccountRole {

	// attribute field
	private Long roleID;

	// getters and setters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getRoleID() {
		return this.roleID;
	}

	public void setRoleID(Long roleID2) {
		this.roleID = roleID2;
	}

	public String toString() {

		if (this instanceof Customer) {
			return "Customer";
		}
		if (this instanceof Cashier) {

			return "Cashier\nEmployment Date: " + ((Cashier) this).getEmploymentDate();
		}
		if (this instanceof Clerk) {
			return "Clerk\nEmployment Date: " + ((Clerk) this).getEmploymentDate();
		}
		if (this instanceof DeliveryPerson) {
			return "Delivery Person\nEmployment Date: " + ((DeliveryPerson) this).getEmploymentDate();
		}
		if (this instanceof Owner) {
			return "Owner";
		}
		return null;
	}
}
