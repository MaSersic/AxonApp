package backend.event;

import backend.command.BaseCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuCreatedEvent extends BaseCommand<String> {

    public MenuCreatedEvent(String id) {
        super(id);
    }

}
