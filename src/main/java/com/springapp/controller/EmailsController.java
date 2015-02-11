package com.springapp.controller;

import com.springapp.bean.Email;
import com.springapp.bean.Offer;
import com.springapp.bean.PurchasedItem;
import com.springapp.bean.User;
import com.springapp.dao.OffersDao;
import com.springapp.dao.UsersDao;
import com.springapp.service.EmailsService;
import com.springapp.service.OffersService;
import com.springapp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class EmailsController {
//    @Autowired
//    private OffersDao offersDao;
//    @Autowired
//    private OffersService offersService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private EmailsService emailsService;

    @RequestMapping("scanemails")
    public String scanEmails(ModelMap modelMap, @ModelAttribute("user") User user){
        return null;
    }


    @RequestMapping("emailform")
    public String emailForm(ModelMap modelMap){
        Email email = new Email();
        modelMap.addAttribute("email",email);
        return "emailform";
    }

    @RequestMapping(value = "/addemail", method = RequestMethod.POST)
    public String addEmail(Model model, @Valid Email email, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "emailform";
        }
        emailsService.saveOrUpdate(email);

        User user = usersService.getUserByName(principal.getName());
        System.out.println(email);
        //todo implement
        // emailService.save(email);
        user.addEmail(email);
        List <PurchasedItem> purchasedItemList = email.fetchPurchasedItem();
        user.addPurchasedItems(purchasedItemList);
        usersService.addPurchasedItemList(purchasedItemList);
        usersService.saveOrUpdateUser(user);
        return "forward:/";
    }
}