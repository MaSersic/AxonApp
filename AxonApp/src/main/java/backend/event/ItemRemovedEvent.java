package backend.event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemRemovedEvent extends BaseEvent<String> {

    private String itemId;

    public ItemRemovedEvent (String id, String itemId) {
        super(id);
        this.itemId = itemId;
    }

}
