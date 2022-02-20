package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

    private String username;
    private String password;
    private String name;
    private int pointBalance;

//    private UserRole userRole;

    @Id
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getPointBalance() {
        return pointBalance;
    }
    //TODO: uncomment when UserRole is available
    //    @OneToOne (optional=false)
    //    public UserRole getUserRole() { return this.userRole;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPointBalance(int pointBalance) {
        this.pointBalance = pointBalance;
    }
}
