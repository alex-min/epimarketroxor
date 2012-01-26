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
import com.epimarket.entity.Author;
import com.epimarket.entity.Book;

@Controller
public class AuthorBackOffice {

	@RequestMapping(value = "ajax/change/author/{type}/{id}",
			method=RequestMethod.POST)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("type") String type,
			@PathVariable("id") int id,
			@RequestParam("data") String data
			) {
		Criteria crit = EMF.getSession().createCriteria(Author.class);
		crit.add(Restrictions.eq("id", id));
		Author cat = (Author) crit.uniqueResult();
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
			value = "admin/author/",
			method = RequestMethod.GET)
	public ModelAndView all()
	{
		Criteria crit = EMF.getSession().createCriteria(Author.class);
		@SuppressWarnings("unchecked")
		List<Book> list = crit.list();
		EMF.getHttpSession().setAttribute("authors", list);
		return new ModelAndView("backoffice_author", "authoradd", new Author());
	}


	@RequestMapping(
			value = "admin/author/delete/{id}/",
			method = RequestMethod.GET)
	public String addToCart(Model model, @PathVariable("id") int id)
	{
		// TODO : uncomment
		/* if ((WD.getData().getAuthor().getRight() != WebAuthor.ADMIN))
		{
			EMF.getHttpSession().setAttribute("lasterror", "Access Denied");
			return "processerror";
		} */

		Criteria c = EMF.getSession().createCriteria(Author.class);
		c.add(Restrictions.eq("id", id));
		Author b = (Author) c.uniqueResult();
		if (b != null)
		{
			try {
				EMF.getSession().delete(b);
			} catch (Exception e) {}
		} else { System.out.println("Uset is null"); }
		return "redirect:/app/admin/author/";
	}

	@RequestMapping(
			value = "authoradd",
			method = RequestMethod.GET)
	public ModelAndView showContacts() {
		return new ModelAndView("admin", "bookadd", new Author());
	}

	@RequestMapping(value="admin/author/authoraddaction",
			method=RequestMethod.POST)
	public String addContact(@ModelAttribute("author")
	@Valid Author author, BindingResult result, ModelMap m) {
		System.out.println("author : " + author);
		EMF.getHttpSession().setAttribute("lasterror", "");
		if (result.hasErrors()) {
			String errorOutput = "Registration failed : </br>";
			List<ObjectError> e = result.getAllErrors();
			for (ObjectError a : e) {
				errorOutput += a.getObjectName() + " : " + a.getDefaultMessage() + "</br>";
			}
			EMF.getHttpSession().setAttribute("lasterror", errorOutput);
			return "redirect:/app/admin/author/";
		}
		try {
			EMF.begin();
			EMF.save(author);
		} catch (Exception e) {
			m.addAttribute("error", e.getMessage());
		}
		return "redirect:/app/admin/author/";
	}
}
