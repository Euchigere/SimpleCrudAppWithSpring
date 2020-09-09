package dev.euchigere.facebookclonewithspring.controller;

import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.service.UserService;
import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth/")
public class LoginController {
    @Autowired
    private UserService userService;
    public static User currentUser;


    @GetMapping("login")
    public String login(User user) {
        return "login-and-signup.html";
    }

    @PostMapping("login_user")
    public String loginUser(
            @RequestParam(value = "login-email", required = true) String email,
            @RequestParam(value = "login-password", required = true) String password,
            RedirectAttributes redirectAttr
        ) {

        ResponseObject<User> responseObject
                = userService.authenticateUser(email, password);
        if (!responseObject.isStatus()) {
            redirectAttr.addFlashAttribute("errorMessage", responseObject.getMessage());
            return "redirect:/auth/login";
        }
        currentUser = responseObject.getObject();
        return "redirect:/";
    }

    @PostMapping("add_user")
    public String addUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttr) {
        if (result.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", result.getFieldError().getDefaultMessage());
            return "redirect:/auth/login";
        }
        ResponseObject<User> responseObject = userService.validateUser(user.getEmail());

        if (!responseObject.isStatus()) {
            redirectAttr.addFlashAttribute("errorMessage", responseObject.getMessage());
            return "redirect:/auth/login";
        }
        userService.saveUserData(user);
        redirectAttr.addFlashAttribute("flashMessage", responseObject.getMessage());
        return "redirect:/auth/login";
    }
}
