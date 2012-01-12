package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "lol")
public class SpringTest {
	
	@RequestMapping(
			value = "miam/*",
			method = RequestMethod.GET)
	public String all(HttpServletRequest rqst, HttpServletResponse resp, Model model) 
	{
		System.out.println("IIIIIIIIIICCCCCCCCCCCCCIIIIIIIIIIii");
		return "test";
	}
	
	@RequestMapping(
			value = "",
			method = RequestMethod.GET)
	public String all2(HttpServletRequest rqst, HttpServletResponse resp, Model model) 
	{
		rqst.setAttribute("title", "Test page");
		System.out.println("LLLLLLLLLLLLLLLLAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaa");
		return "menu";
	}
}
