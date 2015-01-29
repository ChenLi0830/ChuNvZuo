package com.springapp.controller;

import com.springapp.bean.Offer;
import com.springapp.service.OffersService;
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
	OffersService offersService;

	@RequestMapping("/")
	public String showHome(Model model, Principal principal){
		List<Offer> offerList = offersService.getCurrent();
		model.addAttribute("message", "Spring dispatcher servlet starts");
		model.addAttribute("offerList", offerList);

		Boolean hasOffers = false;
		if (principal!=null){
			hasOffers = offersService.hasOffers(principal.getName());
		}
		model.addAttribute("hasOffers",hasOffers);
		return "home";
	}
}
