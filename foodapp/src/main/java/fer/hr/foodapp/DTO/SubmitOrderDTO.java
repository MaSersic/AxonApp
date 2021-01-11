package fer.hr.foodapp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubmitOrderDTO {
    private String id;

    public SubmitOrderDTO(String id) {
        this.id = id;
    }
}
