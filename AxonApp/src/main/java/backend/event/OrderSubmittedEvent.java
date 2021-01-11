package backend.event;

import backend.DTO.ItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderSubmittedEvent extends BaseEvent<String> {

    public OrderSubmittedEvent (String id) {
        super(id);
    }
}
