package ca.mcgill.ecse321.grocerystore.dto;

public class AccountDto {

    // attributes
    private String username;
    private String name;
    private Integer pointBalance;
// TODO: uncomment once Dto is available
//    private List<OrderDto> orders;
//    private CartDto cart;

    // constructor

    public AccountDto() {
    }

    public AccountDto(String username, String name) {
        this.username = username;
        this.name = name;
        this.pointBalance = 0;
    }

    // getter
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Integer getPointBalance() {
        return pointBalance;
    }
// TODO: uncomment once Dto is available
//    public List<OrderDto> getOrders{
//        return orders;
//    }
//    public CartDto getCart{
//        return cart;
//    }
}
