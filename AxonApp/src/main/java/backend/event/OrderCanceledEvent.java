package backend.event;

public class OrderCanceledEvent extends BaseEvent<String> {

    public OrderCanceledEvent (String id) {
        super(id);
    }

}
