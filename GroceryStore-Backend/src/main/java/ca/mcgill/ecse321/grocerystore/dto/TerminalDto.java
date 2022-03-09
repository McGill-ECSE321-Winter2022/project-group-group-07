package ca.mcgill.ecse321.grocerystore.dto;

public class TerminalDto {
    // attribute
    private Integer terminalID;

    // constructor
    public TerminalDto() {
    }

    public TerminalDto(Integer terminalID){
        this.terminalID = terminalID;
    }
    // getter
    public Integer getTerminalID() {
        return terminalID;
    }
}
