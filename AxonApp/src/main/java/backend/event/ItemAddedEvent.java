package backend.event;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ItemAddedEvent extends BaseEvent<String> {

    private String itemId;
    private String name;
    private Float price;
    private String ingredients;

    public ItemAddedEvent (String id, String itemId, String name, Float price, String ingredients) {
        super(id);
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

}
