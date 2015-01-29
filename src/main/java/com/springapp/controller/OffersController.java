package com.springapp.controller;

import com.springapp.bean.Offer;
import com.springapp.bean.User;
import com.springapp.dao.OffersDao;
import com.springapp.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class OffersController {
    @Autowired
    private OffersDao offersDao;
    @Autowired
    private OffersService offersService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showTest(ModelMap model, @RequestParam("id") String id) {
        System.out.println("Id is:" + id);
        return "home";
    }

    @RequestMapping("/offers")
    public String printWelcome(ModelMap model) {
        List<Offer> offerList = offersService.getCurrent();

        model.addAttribute("message", "Spring dispatcher servlet starts");
        model.addAttribute("offerList", offerList);
        return "offers";
    }

    @RequestMapping("/createoffer")
    public String createOffer(Model model,Principal principal) {

        Offer offer = null;
        if (principal!=null){
            String username = principal.getName();
            offer = offersService.getOffer(username);
        }

        if (offer == null) {
            offer = new Offer();
        }

        model.addAttribute("offer", offer);
        return "createoffer";
    }

    @RequestMapping(value = "/docreate", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid Offer offer, BindingResult bindingResult, Principal principal, @RequestParam(value = "delete", required = false)String delete) {

        if (bindingResult.hasErrors()) {
            return "createoffer";
        }

        if (delete == null){
            String username = principal.getName();
            offer.getUser().setUsername(username);
            offersService.createOrUpdateOffer(offer);
            return "offercreated";
        } else {
            offersService.delete(offer.getId());
            return "offerdeleted";
        }

    }

//	public String showHome(HttpSession httpSession){
////		httpSession.setAttribute("name","SessionChen");
////		UserDetails userDetails = new UserDetails();
////		userDetails.setUserName("WeiWang");
////		userDetailsDao.save(userDetails);
//
//		List<Offer> offerList= offersService.getCurrent();
//		System.out.println(offerList);
//		return "hello";
//	}

//
//	public String showHome(Model model){
//		model.addAttribute("name","<b>Chen</b>");
//		System.out.println("java.class.path = "+System.getProperty("java.class.path"));
//
//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//
//		URL[] urls = ((URLClassLoader)cl).getURLs();
//
//		for(URL url: urls){
//			System.out.println(url.getFile());
//		}
//
//		return "hello";
//	}
}