package backend.services.impl;

import backend.DTO.CreateItemDTO;
import backend.DTO.CreateMenuDTO;
import backend.DTO.DeleteItemDTO;
import backend.command.CreateItemCommand;
import backend.command.CreateMenuCommand;
import backend.command.DeleteItemCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MenuCommandServiceImpl {

    private final CommandGateway commandGateway;

    @Autowired
    public MenuCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createMenu (CreateMenuDTO createMenuDTO) {
        return commandGateway.send(new CreateMenuCommand("menu" + createMenuDTO.getId()));
    }

    public CompletableFuture<String> createItem (CreateItemDTO createItemDTO) {
        return commandGateway.send(new CreateItemCommand("menu" + createItemDTO.getId(), createItemDTO.getItemId(), createItemDTO.getName(), createItemDTO.getPrice(), createItemDTO.getIngredients()));
    }

    public CompletableFuture<String> deleteItem (DeleteItemDTO deleteItemDTO) {
        return commandGateway.send(new DeleteItemCommand("menu" + deleteItemDTO.getId(), deleteItemDTO.getItemId()));
    }
}