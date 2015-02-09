package com.springapp.controller;

import com.springapp.bean.Email;
import com.springapp.bean.User;
import com.springapp.service.UsersService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Chen on 15-01-18.
 */
//@SessionAttributes("user")
@Controller
public class LoginController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/logout")
    public String showLogout() {

        return "logout";
    }

    @RequestMapping("/admin")
    public String showAdmin(Model model) {
        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @RequestMapping("/newaccount")
    public String showNewAccount(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "newaccount";
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(Model model, @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "newaccount";
        }

        user.setEnabled(true);
        user.setAuthority("ROLE_USER");

        try {
            usersService.createUser(user);
        } catch (DuplicateKeyException ex) {
            bindingResult.rejectValue("username", "DuplicateKey.user.username", "This username already exists.");
            return "newaccount";
        }

        System.out.println(user);
        model.addAttribute("email",new Email());
        model.addAttribute("user",user);

        return "accountcreated";
    }



//    @RequestMapping(value = "/addemail", method = RequestMethod.POST)
//    public String addEmail(Model model, @Valid Email email, @ModelAttribute("user") User user, BindingResult bindingResult){
//        System.out.println(email);
//
//        //todo implement
//        // emailService.save(email);
//        user.addEmail(email);
//        return "accountcreated";
//    }
}



