package backend.services.impl;

import backend.DTO.*;
import backend.command.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderCommandServiceImpl {

    private final CommandGateway commandGateway;

    @Autowired
    public OrderCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createOrder (CreateOrderDTO createOrderDTO) {
        return commandGateway.send(new CreateOrderCommand("order" + createOrderDTO.getId()));
    }

    public CompletableFuture<String> submitOrder (SubmitOrderDTO submitOrderDTO) {
        return commandGateway.send(new SubmitOrderCommand("order" + submitOrderDTO.getId()));
    }

    public CompletableFuture<String> cancelOrder (CancelOrderDTO cancelOrderDTO) {
        return commandGateway.send(new CancelOrderCommand("order" + cancelOrderDTO.getId()));
    }

    public CompletableFuture<String> confirmOrder (ConfirmOrderDTO confirmOrderDTO) {
        return commandGateway.send(new ConfirmOrderCommand("order" + confirmOrderDTO.getId()));
    }

    public CompletableFuture<String> addItem (AddItemDTO addItemDTO) {
        return commandGateway.send(new AddItemCommand("order" + addItemDTO.getId(), addItemDTO.getItemId(), addItemDTO.getName(), addItemDTO.getPrice(), addItemDTO.getIngredients()));
    }

    public CompletableFuture<String> removeItem (RemoveItemDTO removeItemDTO) {
        return commandGateway.send(new RemoveItemCommand("order" + removeItemDTO.getId(), removeItemDTO.getItemId()));
    }
}
