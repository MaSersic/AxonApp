package backend.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMenuCommand extends BaseCommand<String> {

    public CreateMenuCommand (String id) {
        super(id);
    }

}
