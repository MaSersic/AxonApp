package backend.event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderConfirmedEvent extends BaseEvent<String> {

    public OrderConfirmedEvent (String id) {
        super(id);
    }

}
