package backend.controllers;

import backend.DTO.CreateItemDTO;
import backend.DTO.CreateMenuDTO;
import backend.DTO.DeleteItemDTO;
import backend.DTO.ItemDTO;
import backend.services.impl.MenuCommandServiceImpl;
import backend.services.impl.MenuQueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping("menu/*")
@RestController
public class MenuController {
    private MenuQueryServiceImpl menuQueryService;
    private MenuCommandServiceImpl menuCommandService;

    @Autowired
    private MenuController (MenuQueryServiceImpl menuQueryService, MenuCommandServiceImpl menuCommandService) {
        this.menuCommandService = menuCommandService;
        this.menuQueryService = menuQueryService;
    }

    @PostMapping("create/")
    private CompletableFuture<String> createMenu (@RequestBody CreateMenuDTO dto) {
        return menuCommandService.createMenu(dto);
    }

    @PostMapping("createItem/")
    private CompletableFuture<String> createItem (@RequestBody CreateItemDTO dto) {
        return menuCommandService.createItem(dto);
    }

    @PostMapping("deleteItem/")
    private CompletableFuture<String> deleteItem (@RequestBody DeleteItemDTO dto) {
        return menuCommandService.deleteItem(dto);
    }

    @GetMapping("listItems/")
    private List<ItemDTO> listItems (@RequestParam String id) {
        return menuQueryService.listItems(id);
    }
}
