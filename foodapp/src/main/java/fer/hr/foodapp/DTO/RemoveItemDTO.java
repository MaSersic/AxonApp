package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveItemDTO {
    private String id;
    private String itemId;

    public RemoveItemDTO(String id, String itemId) {
        this.id = id;
        this.itemId = itemId;
    }
}
