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
import com.epimarket.entity.Category;

@Controller
@RequestMapping(value = "ajax/category/{category}")
public class CategoryAjaxCompleteController {


	@RequestMapping(method = RequestMethod.GET)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("category") String category)
	{
		Criteria crit = EMF.getSession().createCriteria(Category.class);
		crit.add(Restrictions.like("name", "%" + category + "%"));
		@SuppressWarnings("unchecked")
		List<Category> list = crit.list();
		rqst.setAttribute("categorylist", list);
		return "ajax_category";
	}

}
