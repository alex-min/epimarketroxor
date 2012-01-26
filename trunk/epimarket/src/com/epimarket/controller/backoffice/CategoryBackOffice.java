package com.epimarket.controller.backoffice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epimarket.database.EMF;
import com.epimarket.entity.Book;
import com.epimarket.entity.Category;

@Controller
public class CategoryBackOffice {

	@RequestMapping(value = "ajax/change/category/{type}/{id}",
			method=RequestMethod.POST)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("type") String type,
			@PathVariable("id") int id,
			@RequestParam("data") String data
			) {
		Criteria crit = EMF.getSession().createCriteria(Category.class);
		crit.add(Restrictions.eq("id", id));
		Category cat = (Category) crit.uniqueResult();
		try {
			System.out.println("type:" + type);

			PropertyUtils.setSimpleProperty(cat, type,
					data
					);
			EMF.update(cat);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "raw";
	}

	@RequestMapping(
			value = "admin/category/",
			method = RequestMethod.GET)
	public ModelAndView all()
	{
		Criteria crit = EMF.getSession().createCriteria(Category.class);
		@SuppressWarnings("unchecked")
		List<Book> list = crit.list();
		EMF.getHttpSession().setAttribute("categories", list);
		return new ModelAndView("backoffice_category", "categoryadd", new Category());
	}


	@RequestMapping(
			value = "admin/category/delete/{id}/",
			method = RequestMethod.GET)
	public String addToCart(Model model, @PathVariable("id") int id)
	{
		// TODO : uncomment
		/* if ((WD.getData().getCategory().getRight() != WebCategory.ADMIN))
		{
			EMF.getHttpSession().setAttribute("lasterror", "Access Denied");
			return "processerror";
		} */

		Criteria c = EMF.getSession().createCriteria(Category.class);
		c.add(Restrictions.eq("id", id));
		Category b = (Category) c.uniqueResult();
		if (b != null)
		{
			try {
				EMF.getSession().delete(b);
			} catch (Exception e) {}
		} else { System.out.println("Uset is null"); }
		return "redirect:/app/admin/category/";
	}

	@RequestMapping(
			value = "categoryadd",
			method = RequestMethod.GET)
	public ModelAndView showContacts() {
		return new ModelAndView("admin", "bookadd", new Category());
	}

	@RequestMapping(value="admin/category/categoryaddaction",
			method=RequestMethod.POST)
	public String addContact(@ModelAttribute("category")
	@Valid Category category, BindingResult result, ModelMap m) {
		System.out.println("category : " + category);
		EMF.getHttpSession().setAttribute("lasterror", "");
		if (result.hasErrors()) {
			String errorOutput = "Registration failed : </br>";
			List<ObjectError> e = result.getAllErrors();
			for (ObjectError a : e) {
				errorOutput += a.getObjectName() + " : " + a.getDefaultMessage() + "</br>";
			}
			EMF.getHttpSession().setAttribute("lasterror", errorOutput);
			return "redirect:/app/admin/category/";
		}
		try {
			EMF.begin();
			EMF.save(category);
		} catch (Exception e) {
			m.addAttribute("error", e.getMessage());
		}
		return "redirect:/app/admin/category/";
	}
}
