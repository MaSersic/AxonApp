package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfirmOrderDTO {
    private String id;

    public ConfirmOrderDTO(String id) {
        this.id = id;
    }
}
