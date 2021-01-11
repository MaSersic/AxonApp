package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CancelOrderDTO {
    private String id;

    public CancelOrderDTO(String id) {
        this.id = id;
    }
}
