package ca.mcgill.ecse321.GroceryStoreBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Terminal {
    private int terminalID;

    @Id
    public int getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(int terminalID) {
        this.terminalID = terminalID;
    }
}
