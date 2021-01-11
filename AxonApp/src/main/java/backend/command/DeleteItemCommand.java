package backend.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteItemCommand extends BaseCommand<String> {

    private String itemId;

    public DeleteItemCommand (String id, String itemId) {
        super(id);
        this.itemId = itemId;
    }

}
