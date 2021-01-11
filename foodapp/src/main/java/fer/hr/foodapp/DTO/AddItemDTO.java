package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddItemDTO {
    private String id;
    private String itemId;
    private String name;
    private Float price;
    private String ingredients;

    public AddItemDTO(String id, String itemId, String name, Float price, String ingredients) {
        this.id = id;
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }
}
