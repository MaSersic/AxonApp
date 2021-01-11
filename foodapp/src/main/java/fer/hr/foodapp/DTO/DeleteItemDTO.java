package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteItemDTO {
    private String id;
    private String itemId;

    public DeleteItemDTO(String id, String itemId) {
        this.id = id;
        this.itemId = itemId;
    }
}
