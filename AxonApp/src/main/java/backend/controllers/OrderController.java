package backend.controllers;

import backend.DTO.*;
import backend.services.impl.OrderCommandServiceImpl;
import backend.services.impl.OrderQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping ("orders/*")
@RestController
public class OrderController {

    private OrderQueryServiceImpl orderQueryService;
    private OrderCommandServiceImpl orderCommandService;

    @Autowired
    private OrderController (OrderQueryServiceImpl orderQueryService, OrderCommandServiceImpl orderCommandService) {
        this.orderQueryService = orderQueryService;
        this.orderCommandService = orderCommandService;
    }

    @PostMapping("create/")
    private CompletableFuture<String> createOrder (@RequestBody CreateOrderDTO dto) {
        return orderCommandService.createOrder(dto);
    }

    @PostMapping("submit/")
    private CompletableFuture<String> submitOrder (@RequestBody SubmitOrderDTO dto) {
        return orderCommandService.submitOrder(dto);
    }

    @PostMapping("cancel/")
    private CompletableFuture<String> cancelOrder (@RequestBody CancelOrderDTO dto) {
        return orderCommandService.cancelOrder(dto);
    }

    @PostMapping("confirm/")
    private CompletableFuture<String> confirmOrder (@RequestBody ConfirmOrderDTO dto) {
        return orderCommandService.confirmOrder(dto);
    }

    @PostMapping("addItem/")
    private CompletableFuture<String> addItem (@RequestBody AddItemDTO dto) {
        return orderCommandService.addItem(dto);
    }

    @PostMapping("removeItem/")
    private CompletableFuture<String> removeItem (@RequestBody RemoveItemDTO dto) {
        return orderCommandService.removeItem(dto);
    }

    @GetMapping("listItems")
    private List<ItemDTO> listItemsInOrder (@RequestParam String id) {
        return orderQueryService.listItemsInOrder(id);
    }
}
