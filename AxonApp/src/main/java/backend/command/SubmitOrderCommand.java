package backend.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubmitOrderCommand extends BaseCommand<String>{

    public SubmitOrderCommand (String id) {
        super(id);
    }
}
