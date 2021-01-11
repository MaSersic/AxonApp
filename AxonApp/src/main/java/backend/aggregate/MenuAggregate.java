package backend.aggregate;

import backend.DTO.ItemDTO;
import backend.command.CreateItemCommand;
import backend.command.CreateMenuCommand;
import backend.command.DeleteItemCommand;
import backend.event.ItemCreatedEvent;
import backend.event.ItemDeletedEvent;
import backend.event.MenuCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Aggregate
public class MenuAggregate {

    @AggregateIdentifier
    private String menuId;

    private List<ItemDTO> items;

    public MenuAggregate () {}

    @CommandHandler
    public MenuAggregate (CreateMenuCommand command) {
        AggregateLifecycle.apply(new MenuCreatedEvent(command.id));
    }

    @EventSourcingHandler
    protected void on(MenuCreatedEvent event){
        this.menuId = event.id;
        this.items = new ArrayList<>();
    }

    @CommandHandler
    public void createItem(CreateItemCommand command) {
        AggregateLifecycle.apply(new ItemCreatedEvent(command.id, command.getItemId(),
                command.getName(), command.getPrice(), command.getIngredients()));
    }

    @EventSourcingHandler
    protected void on(ItemCreatedEvent event){
        this.items.add(new ItemDTO(event.getItemId(), event.getName(), event.getPrice(), event.getIngredients()));
    }

    @CommandHandler
    public void on(DeleteItemCommand command) {
        AggregateLifecycle.apply(new ItemDeletedEvent(command.id, command.getItemId()));
    }

    @EventSourcingHandler
    protected void on(ItemDeletedEvent event){
        this.items = this.items.stream().filter( e -> !e.getItemId().equals(event.id) ).collect(Collectors.toList());
    }

}
