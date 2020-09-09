package dev.euchigere.facebookclonewithspring.controller;

import dev.euchigere.facebookclonewithspring.models.User;
import dev.euchigere.facebookclonewithspring.service.UserService;
import dev.euchigere.facebookclonewithspring.dto.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

/**
 * Login controller
 * Authenticates user at login and sign up
 */
@Controller
@RequestMapping("/auth/")
public class LoginController {
    @Autowired
    private UserService userService;
    public static User currentUser;


    /**
     * Directs to the  login page
     * @param user
     * @return
     */
    @GetMapping("login")
    public String login(User user) {
        return "login-and-signup.html";
    }

    /**
     * Method to Authenticate user login details and log in user if the details are valid
     * @param email
     * @param password
     * @param redirectAttr
     * @return
     */
    @PostMapping("login_user")
    public String loginUser(
            @RequestParam(value = "login-email", required = true) String email,
            @RequestParam(value = "login-password", required = true) String password,
            RedirectAttributes redirectAttr
        ) {

        // method to authenticate user and return a response object from the service layer
        ResponseObject<User> responseObject
                = userService.authenticateUser(email, password);
        // if response status is false, the user is redirected to login page
        // and error message is displayed
        if (!responseObject.isStatus()) {
            redirectAttr.addFlashAttribute("errorMessage", responseObject.getMessage());
            return "redirect:/auth/login";
        }
        currentUser = responseObject.getObject();
        return "redirect:/";
    }

    /**
     * Method to sign up user
     * if there are error in the signup field, user is redirected to login page with error message
     * if the email provided is already taken, user is redirected to login page with error message
     * If the user details are valid, user is redirected to login page with success message
     * @param user
     * @param result
     * @param redirectAttr
     * @return
     */
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
