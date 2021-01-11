package fer.hr.foodapp.controller;

import fer.hr.foodapp.DTO.CreateMenuDTO;
import fer.hr.foodapp.DTO.CreateOrderDTO;
import fer.hr.foodapp.helper.HttpRequestHelper;
import fer.hr.foodapp.helper.OrderHelper;
import fer.hr.foodapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class LoginController {
    UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHome(RedirectAttributes attr) {

        CreateMenuDTO createMenuDTO = new CreateMenuDTO ("0");
        try {
            String str2 = HttpRequestHelper.postRequest("menu/create/", createMenuDTO);
            attr.addAttribute("menuId", "0");
        } catch (IOException e) {
            e.printStackTrace();
        }

        OrderHelper.LAST_ORDER_NO ++;
        Integer orderNo = OrderHelper.LAST_ORDER_NO;
        CreateOrderDTO dto = new CreateOrderDTO (orderNo.toString());
        try {
            String str1 = HttpRequestHelper.postRequest("orders/create/", dto);
            attr.addAttribute("orderId", orderNo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        OrderHelper.LAST_ORDER_NO = 0;

        return "redirect:/home/";
    }




}
