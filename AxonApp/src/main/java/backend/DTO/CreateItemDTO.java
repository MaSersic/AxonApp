package backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateItemDTO {
    private String id;
    private String itemId;
    private String name;
    private Float price;
    private String ingredients;
}
