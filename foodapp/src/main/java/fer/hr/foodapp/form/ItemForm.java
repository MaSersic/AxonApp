package fer.hr.foodapp.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {
    private String menuId;
    private String itemId;
    private String name;
    private Float price;
    private String ingredients;
    private String orderId;

    public ItemForm (String menuId, String itemId, String name, Float price, String ingredients, String orderId) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.orderId = orderId;
        this.menuId = menuId;
    }
}
