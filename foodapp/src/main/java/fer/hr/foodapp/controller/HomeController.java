package fer.hr.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fer.hr.foodapp.DTO.AddItemDTO;
import fer.hr.foodapp.DTO.ItemDTO;
import fer.hr.foodapp.DTO.RemoveItemDTO;
import fer.hr.foodapp.form.ItemForm;
import fer.hr.foodapp.helper.HttpRequestHelper;
import fer.hr.foodapp.helper.OrderHelper;
import fer.hr.foodapp.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    public HomeController() {
    }

    @GetMapping("/home")
    public String login(@AuthenticationPrincipal User user, Model model, @RequestParam String menuId, @RequestParam(required = false) String orderId) {
        if (user.getRole().equals("ADMIN")) {
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }

        String jsonString = HttpRequestHelper.getRequest("menu/listItems/", "?id=" + menuId);

        ObjectMapper mapper = new ObjectMapper();
        try {
            List<ItemDTO> items = mapper.readValue(jsonString, new TypeReference<List<ItemDTO>>() {
            });
            model.addAttribute("items", items);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("items", new ArrayList());
        }
        if (orderId == null) {
            model.addAttribute("orderItems", new ArrayList());
        } else {
            List<ItemDTO> orderItems = OrderHelper.fetchOrder(orderId);
            model.addAttribute("orderItems", orderItems);
        }
        model.addAttribute("menuId", menuId);
        model.addAttribute("orderId", orderId);

        return "home";
    }

    @PostMapping("/home/addItem")
    public String addItem(RedirectAttributes attr, @ModelAttribute ItemForm itemForm) {
        attr.addAttribute("menuId", itemForm.getMenuId());
        AddItemDTO itemDTO = new AddItemDTO(itemForm.getOrderId(), itemForm.getItemId(), itemForm.getName(), itemForm.getPrice(), itemForm.getIngredients());
        try {
            String resp = HttpRequestHelper.postRequest("orders/addItem/", itemDTO);
            attr.addAttribute("orderId", itemForm.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/home/";
    }

    @PostMapping("/home/removeItem")
    public String removeItem(RedirectAttributes attr, @ModelAttribute ItemForm itemForm) {
        attr.addAttribute("menuId", itemForm.getMenuId());
        RemoveItemDTO itemDTO = new RemoveItemDTO(itemForm.getOrderId(), itemForm.getItemId());
        try {
            String resp = HttpRequestHelper.postRequest("orders/removeItem/", itemDTO);
            attr.addAttribute("orderId", itemForm.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/home/";
    }

}
