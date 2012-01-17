package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

	@RequestMapping(
			value = "processerror",
			method = RequestMethod.GET)
	public String processerror(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		return "processerror";
	}
}
