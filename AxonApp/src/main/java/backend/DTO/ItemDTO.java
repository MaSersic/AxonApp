package backend.DTO;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ItemDTO {
    private String itemId;
    private String name;
    private Float price;
    private String ingredients;

    public ItemDTO (String itemId, String name, Float price, String ingredients) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }
}
