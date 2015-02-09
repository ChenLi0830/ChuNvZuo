package com.springapp.controller;

import com.springapp.bean.Offer;
import com.springapp.bean.PurchasedItem;
import com.springapp.bean.User;
import com.springapp.dao.UsersDao;
import com.springapp.service.OffersService;
import com.springapp.service.UsersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by root on 1/16/15.
 */
@Controller

public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);
    @Autowired
    private UsersService usersService;

    @Autowired
    OffersService offersService;

    @RequestMapping("/")
    public String showHome(Model model, Principal principal) {
        List<Offer> offerList = offersService.getCurrent();
        model.addAttribute("message", "Spring dispatcher servlet starts");
        model.addAttribute("offerList", offerList);

        Boolean hasOffers = false;
        if (principal != null) {
            hasOffers = offersService.hasOffers(principal.getName());
        }
        model.addAttribute("hasOffers", hasOffers);

        Boolean hasItems = false;
        if (principal != null) {
            System.out.println(principal.getName());
            User user = usersService.getUserByName(principal.getName());
            List<PurchasedItem> purchasedItemList = (List<PurchasedItem>) user.getPurchasedItems();
            hasItems = purchasedItemList.size()>0;
            model.addAttribute("purchasedItemList", purchasedItemList);
        }
        model.addAttribute("hasItems", hasItems);
        return "home";
    }
}
