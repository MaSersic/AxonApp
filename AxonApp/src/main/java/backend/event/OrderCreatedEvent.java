package backend.event;

public class OrderCreatedEvent extends BaseEvent<String> {

    public OrderCreatedEvent (String id) {
        super(id);
    }

}
