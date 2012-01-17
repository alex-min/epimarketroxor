package com.epimarket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
	@Valid User contact, BindingResult result, ModelMap m) {
		System.out.println("First Name:" + contact.getLogin() +
				"Last Name:" + contact.getPassword());
		if (result.hasErrors()) {
			String errorOutput = "Registration failed : </br>";
			List<ObjectError> e = result.getAllErrors();
			for (ObjectError a : e) {
				errorOutput += a.getObjectName() + " : " + a.getDefaultMessage() + "</br>";
			}
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession s = attr.getRequest().getSession(true); // true == allow create

			s.setAttribute("lasterror", errorOutput);
			return "redirect:processerror";
		}
		return "redirect:login";
	}

}
