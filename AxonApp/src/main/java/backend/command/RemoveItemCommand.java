package backend.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RemoveItemCommand extends BaseCommand<String> {

    private String itemId;

    public RemoveItemCommand (String id, String itemId) {
        super(id);
        this.itemId = itemId;
    }

}
