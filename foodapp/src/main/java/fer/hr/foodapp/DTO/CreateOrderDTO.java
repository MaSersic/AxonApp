package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDTO {

    String id;

    public CreateOrderDTO (String id) {
        this.id = id;
    }
}
