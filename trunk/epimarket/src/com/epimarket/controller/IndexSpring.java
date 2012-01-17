package com.epimarket.controller;

import javax.servlet.Registration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.entity.User;
import com.epimarket.webdata.WD;


@Controller
//@RequestMapping(value = "index")
public class IndexSpring {
	@Autowired
	User user;


	@RequestMapping(
			value = "",
			method = RequestMethod.GET)
	public String all(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		WD.getData(rqst);
		System.out.println("Index with spring");
		rqst.setAttribute("title", "Index Epimarket");
		return "index";
	}

	@RequestMapping(
			value = "login",
			method = RequestMethod.GET)
	public String login(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		System.out.println("Login page");
		rqst.setAttribute("title", "Login");

		return "login";
	}



	public String validateForm(@Valid Registration regForm,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "regform";
		}

		//user.setUserName(regForm.getName());
		model.addAttribute("regform", regForm);
		return "regsuccess";

	}



}
