package com.epimarket.controller.backoffice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "ajax/rights/{rights}")
public class RightsAjaxCompleteController {


	@RequestMapping(method = RequestMethod.GET)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("rights") String rights)
	{
		return "ajax_rights";
	}

}
