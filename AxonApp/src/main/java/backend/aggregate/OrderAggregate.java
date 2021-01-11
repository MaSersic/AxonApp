package backend.aggregate;

import backend.command.*;
import backend.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class OrderAggregate {
    private enum ORDER_STATUS {
        SUBMITTED,
        CANCELED,
        CONFIRMED,
        CREATED
    }

    @AggregateIdentifier
    private String orderId;

    private ORDER_STATUS status;

    public OrderAggregate () {}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        AggregateLifecycle.apply(new OrderCreatedEvent(command.id));
    }

    @EventSourcingHandler
    protected void on(OrderCreatedEvent event){
        this.orderId = event.id;
        this.status = ORDER_STATUS.CREATED;
    }

    @CommandHandler
    public void on(SubmitOrderCommand command) {
        AggregateLifecycle.apply(new OrderSubmittedEvent(command.id));
    }

    @EventSourcingHandler
    protected void on(OrderSubmittedEvent event){
        this.status = ORDER_STATUS.SUBMITTED;
    }

    @CommandHandler
    public void on(CancelOrderCommand command) {
        AggregateLifecycle.apply(new OrderCanceledEvent(command.id));
    }

    @EventSourcingHandler
    protected void on(OrderCanceledEvent event){
        if(this.status == ORDER_STATUS.SUBMITTED) {
            this.status = ORDER_STATUS.CANCELED;
        }
    }

    @CommandHandler
    public void on(ConfirmOrderCommand command) {
        AggregateLifecycle.apply(new OrderConfirmedEvent(command.id));
    }

    @EventSourcingHandler
    protected void on(OrderConfirmedEvent event){
        this.status = ORDER_STATUS.CONFIRMED;
    }

    @CommandHandler
    public void on(AddItemCommand command) {
        AggregateLifecycle.apply(new ItemAddedEvent(command.id, command.getItemId(), command.getName(), command.getPrice(), command.getIngredients()));
    }

    @EventSourcingHandler
    protected void on(ItemAddedEvent event){

    }

    @CommandHandler
    public void on(RemoveItemCommand command) {
        AggregateLifecycle.apply(new ItemRemovedEvent(command.id, command.getItemId()));
    }

    @EventSourcingHandler
    protected void on(ItemRemovedEvent event){

    }

}
