package backend.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddItemCommand extends BaseCommand<String> {

    private String itemId;
    private String name;
    private Float price;
    private String ingredients;

    public AddItemCommand (String id, String itemId, String name, Float price, String ingredients) {
        super(id);
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

}
