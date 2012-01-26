package com.epimarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.database.EMF;
import com.epimarket.entity.Book;

@Controller
@RequestMapping(value = "market")
public class MarketController {
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{

		Criteria crit = EMF.getSession().createCriteria(Book.class);
		@SuppressWarnings("unchecked")
		List<Book> list = crit.list();
		rqst.setAttribute("books", list);
		return ("market");
	}


}
