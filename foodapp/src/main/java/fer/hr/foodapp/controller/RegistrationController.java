package fer.hr.foodapp.controller;

import fer.hr.foodapp.form.UserForm;
import fer.hr.foodapp.model.User;
import fer.hr.foodapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String userForm(Model model, UserForm userForm) {
        model.addAttribute("userForm", userForm);
        return "registration";
    }

    @PostMapping("/registration")
    public String newUser(@ModelAttribute UserForm userForm) {
        User user = new User(userForm.getUsername(), userForm.getPassword(), userForm.getRole());
        userService.save(user);
        return "redirect:login";
    }
}
