package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "market")
public class MarketSpring {
	@RequestMapping(
			value = "",
			method = RequestMethod.GET)
	public String all(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		System.out.println("Index with spring");
		rqst.setAttribute("title", "Market");
		return "market";
	}
	@RequestMapping(
			value = "cart",
			method = RequestMethod.GET)
	public String cart(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		System.out.println("cart Page");
		rqst.setAttribute("title", "Cart");
		return "cart";
	}

}