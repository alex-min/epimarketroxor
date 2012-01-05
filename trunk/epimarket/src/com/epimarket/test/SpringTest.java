package com.epimarket.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SpringTest {
	@RequestMapping(
			value = "miam/*",
			method = RequestMethod.GET)
	public String all(HttpServletRequest rqst, HttpServletResponse resp, Model model) 
	{
		return "test";
	}
}
