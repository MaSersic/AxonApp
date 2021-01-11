package backend.event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDeletedEvent extends BaseEvent<String> {

    private String itemId;

    public ItemDeletedEvent (String id, String itemId) {
        super(id);
        this.itemId = itemId;
    }

}
