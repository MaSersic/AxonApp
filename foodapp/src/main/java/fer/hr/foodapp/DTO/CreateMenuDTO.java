package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMenuDTO {
    private String id;

    public CreateMenuDTO (String id) {
        this.id = id;
    }
}
