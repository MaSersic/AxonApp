package fer.hr.foodapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fer.hr.foodapp.DTO.*;
import fer.hr.foodapp.form.ItemForm;
import fer.hr.foodapp.helper.HttpRequestHelper;
import fer.hr.foodapp.helper.OrderHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class OrderController {

    public OrderController() {
    }

    @PostMapping("/submitOrder")
    public String submitOrder (RedirectAttributes attr, @ModelAttribute ItemForm itemForm){
        attr.addAttribute("menuId", itemForm.getMenuId());
        SubmitOrderDTO itemDTO = new SubmitOrderDTO(itemForm.getOrderId());
        try {
            String resp = HttpRequestHelper.postRequest("orders/submit/", itemDTO);
            attr.addAttribute("orderId", itemForm.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "redirect:/orderConfirmation/";
    }

    @GetMapping("/orderConfirmation")
    public String orderConfirmation (Model model, @RequestParam String menuId, @RequestParam String orderId){
        List<ItemDTO> orderItems = OrderHelper.fetchOrder(orderId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("menuId", menuId);
        model.addAttribute("orderId", orderId);

        return "orderConfirmation";
    }

    @PostMapping("/confirmOrder")
    public String confirmOrder (RedirectAttributes attr, @ModelAttribute ItemForm itemForm){
        OrderHelper.LAST_ORDER_NO ++;

        attr.addAttribute("menuId", itemForm.getMenuId());
        ConfirmOrderDTO itemDTO = new ConfirmOrderDTO(itemForm.getOrderId());
        try {
            String resp = HttpRequestHelper.postRequest("orders/confirm/", itemDTO);
            attr.addAttribute("orderId", itemForm.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        int randomNum = ThreadLocalRandom.current().nextInt(30, 61);
        attr.addAttribute("deliveryTime", String.valueOf(randomNum));

        return "redirect:/orderSummary/";
    }

    @GetMapping("/orderSummary")
    public String orderSummary (Model model, @RequestParam String menuId, @RequestParam String orderId, @RequestParam(required = false) String deliveryTime){
        List<ItemDTO> orderItems = OrderHelper.fetchOrder(orderId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("menuId", menuId);
        model.addAttribute("orderId", OrderHelper.LAST_ORDER_NO.toString());

        if (deliveryTime != null) {
            model.addAttribute("deliveryTime", deliveryTime);
        } else {
            model.addAttribute("deliveryTime", "0");
        }

        CreateOrderDTO dto = new CreateOrderDTO (OrderHelper.LAST_ORDER_NO.toString());
        try {
            String str1 = HttpRequestHelper.postRequest("orders/create/", dto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "orderSummary";
    }

    @PostMapping("/cancelOrder")
    public String cancelOrder (RedirectAttributes attr, @ModelAttribute ItemForm itemForm){
        OrderHelper.LAST_ORDER_NO ++;

        attr.addAttribute("menuId", itemForm.getMenuId());
        CancelOrderDTO itemDTO = new CancelOrderDTO(itemForm.getOrderId());
        try {
            String resp = HttpRequestHelper.postRequest("orders/cancel/", itemDTO);
            attr.addAttribute("orderId", OrderHelper.LAST_ORDER_NO.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CreateOrderDTO dto = new CreateOrderDTO (OrderHelper.LAST_ORDER_NO.toString());
        try {
            String str1 = HttpRequestHelper.postRequest("orders/create/", dto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/home/";
    }

    @PostMapping("/order/removeItem")
    public String removeItem(RedirectAttributes attr, @ModelAttribute ItemForm itemForm) {
        attr.addAttribute("menuId", itemForm.getMenuId());
        RemoveItemDTO itemDTO = new RemoveItemDTO(itemForm.getOrderId(), itemForm.getItemId());
        try {
            String resp = HttpRequestHelper.postRequest("orders/removeItem/", itemDTO);
            attr.addAttribute("orderId", itemForm.getOrderId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/orderConfirmation/";
    }
}
