package com.epimarket.controller.backoffice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.database.EMF;
import com.epimarket.entity.Book;

@Controller
@RequestMapping(value = "ajax/picture/{picture}")
public class PictureAjaxCompleteController {


	@RequestMapping(method = RequestMethod.GET)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("picture") String picture)
	{
		Criteria crit = EMF.getSession().createCriteria(Book.class);
		crit.add(Restrictions.like("picture", "%" + picture + "%"));
		@SuppressWarnings("unchecked")
		List<Book> list = crit.list();
		rqst.setAttribute("pictures", list);
		return "ajax_picture";
	}

}
