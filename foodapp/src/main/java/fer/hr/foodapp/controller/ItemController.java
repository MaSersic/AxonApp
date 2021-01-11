package fer.hr.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fer.hr.foodapp.DTO.CreateItemDTO;
import fer.hr.foodapp.DTO.DeleteItemDTO;
import fer.hr.foodapp.form.ItemForm;
import fer.hr.foodapp.helper.HttpRequestHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemController {

    public ItemController() {
    }

    @GetMapping("/newItem")
    public String getNewItem (Model model, @RequestParam String menuId, @RequestParam String orderId) {
        model.addAttribute("menuId", menuId);
        model.addAttribute("orderId", orderId);

        return "newItem";
    }

    @GetMapping("/deleteItem")
    public String getDeleteItem (Model model, @RequestParam String menuId, @RequestParam String orderId) {
        model.addAttribute("menuId", menuId);
        model.addAttribute("orderId", orderId);

        return "deleteItem";
    }

    @PostMapping("/newItem")
    public String newItem (RedirectAttributes attr, @ModelAttribute ItemForm form) {
        attr.addAttribute("menuId", form.getMenuId());
        CreateItemDTO itemDTO = new CreateItemDTO(form.getMenuId(), form.getItemId(), form.getName(), form.getPrice(), form.getIngredients());
        try {
            String resp = HttpRequestHelper.postRequest("menu/createItem/", itemDTO);
            attr.addAttribute("orderId", form.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/home/";
    }

    @PostMapping("/deleteItem")
    public String deleteItem (RedirectAttributes attr, @ModelAttribute ItemForm form) {
        attr.addAttribute("menuId", form.getMenuId());
        DeleteItemDTO itemDTO = new DeleteItemDTO(form.getMenuId(), form.getItemId());
        try {
            String resp = HttpRequestHelper.postRequest("menu/deleteItem/", itemDTO);
            attr.addAttribute("orderId", form.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/home/";
    }
}
