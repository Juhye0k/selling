package sell.selling.domain.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Item {
    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    private String whoAddItem;



    public Item(String itemName,Integer price,Integer quantity)
    {
        this.itemName=itemName;
        this.price=price;
        this.quantity=quantity;
    }
    public Item()
    {

    }


}
