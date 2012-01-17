package com.epimarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epimarket.entity.User;

@Controller
public class RegisterController {


	@RequestMapping(
			value = "register",
			method = RequestMethod.GET)
	public ModelAndView showContacts() {
		return new ModelAndView("register", "command", new User());
	}


	@RequestMapping(value="registration",
			method=RequestMethod.POST)
	public String addContact(@ModelAttribute("user")
	User contact, BindingResult result) {
		System.out.println("First Name:" + contact.getLogin() +
				"Last Name:" + contact.getPassword());

		return "redirect:login";
	}

}
