package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.webdata.WD;


@Controller
//@RequestMapping(value = "index")
public class IndexSpring {

	@RequestMapping(
			value = "",
			method = RequestMethod.GET)
	public String all(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
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

	@RequestMapping(
			value = "registration",
			method = RequestMethod.POST)
	public String registration(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		rqst.getSession().getAttribute("auth");

		System.out.println("Register page");
		rqst.setAttribute("title", "Register");
		rqst.setAttribute("reg", "true");
		return "login";
	}
}
