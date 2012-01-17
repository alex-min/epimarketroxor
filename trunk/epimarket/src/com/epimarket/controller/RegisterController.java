package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epimarket.webdata.WD;

@Controller
public class RegisterController {
	@RequestMapping(
			value = "registration",
			method = RequestMethod.POST)
	public String registration(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		return "register_complete";
	}

	@RequestMapping(
			value = "register",
			method = RequestMethod.GET)
	public String register(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		WD.getData(rqst);
		System.out.println("Register page");
		rqst.setAttribute("title", "Register");
		return "register";
	}

	@RequestMapping(value="registration",
			method=RequestMethod.POST)
	public ModelAndView signUpForm() {
		System.out.println("Sign up");
		return null;
	}

}
