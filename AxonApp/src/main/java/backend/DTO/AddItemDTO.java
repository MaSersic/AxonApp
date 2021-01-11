package backend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddItemDTO {
    private String id;
    private String itemId;
    private String name;
    private Float price;
    private String ingredients;
}
