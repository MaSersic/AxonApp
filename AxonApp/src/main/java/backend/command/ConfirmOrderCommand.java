package backend.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConfirmOrderCommand extends BaseCommand<String> {

    public ConfirmOrderCommand (String id) {
        super(id);
    }

}
