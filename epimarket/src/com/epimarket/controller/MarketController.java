package com.epimarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
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
		int pageBegin = ServletRequestUtils.getIntParameter(rqst, "b", 0);
		int pageEnd = ServletRequestUtils.getIntParameter(rqst, "e", 10);

		EMF.getSession().get(
				Book.class.getCanonicalName(),
				1);

		Criteria crit = EMF.getSession().createCriteria(Book.class);

		crit.add(Restrictions.between("id", pageBegin, pageEnd));

		List<Book> list = crit.list();


		return ("market");
	}


}
