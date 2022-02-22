package ca.mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Terminal {
    private Integer terminalID;

    @Id
    public Integer getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(Integer terminalID) {
        this.terminalID = terminalID;
    }
}
