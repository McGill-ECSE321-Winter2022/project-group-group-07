package ca.mcgill.ecse321.grocerystore.dto;

public class TerminalDto {
    // attribute
    private Long terminalID;

    // constructor
    public TerminalDto() {
    }

    public TerminalDto(Long terminalID){
        this.terminalID = terminalID;
    }
    // getter
    public Long getTerminalID() {
        return terminalID;
    }
}
