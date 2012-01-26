package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.database.EMF;
import com.epimarket.entity.User;
import com.epimarket.webdata.WD;


@Controller
//@RequestMapping(value = "index")
public class IndexSpring {

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
		return "login";
	}


	@RequestMapping(
			value = "logout",
			method = RequestMethod.GET)
	public String logout(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		WD.getData().getUser().setLogged(false);
		WD.getData().getUser().setUser(null);
		return "index";
	}


	@RequestMapping(
			value = "login",
			method = RequestMethod.POST)
	public String loginaction(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		String login = rqst.getParameter("login");
		String password = rqst.getParameter("password");
		Criteria crit = EMF.getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("login", login));
		User user = (User) crit.uniqueResult();
		if (user == null) {
			EMF.getHttpSession().setAttribute("lasterror",
					"login or password is wrong");
			return "redirect:/app/processerror";
		}
		if (user.getPassword().compareTo(password) != 0) {
			EMF.getHttpSession().setAttribute("lasterror",
					"login or password is wrong");
			return "redirect:/app/processerror";
		}
		WD.getData().getUser().setLogged(true);
		WD.getData().getUser().setUser(user);

		System.out.println("THE end");
		rqst.setAttribute("title", "Login");

		return "index";
	}







}
