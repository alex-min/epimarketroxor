package com.epimarket.controller.backoffice;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.epimarket.database.EMF;
import com.epimarket.entity.Book;
import com.epimarket.webdata.WD;
import com.epimarket.webdata.WebUser;


@Controller
public class BackOffice {
	// REDIRECT admin -> admin/
	/*@RequestMapping(
			value = "admin",
			method = RequestMethod.GET)
	public String redirect(HttpServletRequest rqst, HttpServletResponse resp, Model model) {
		return "redirect:app/admin/";
	}*/

	@RequestMapping(
			value = "admin/book/",
			method = RequestMethod.GET)
	public ModelAndView all()
	{
		WD.getData().getUser().setRight(WebUser.ADMIN);
		if (WD.getData().getUser().getRight() != WebUser.ADMIN)
		{
			EMF.getHttpSession().setAttribute("lasterror", "Access Denied");
			return new ModelAndView("processerror");
		}

		Criteria crit = EMF.getSession().createCriteria(Book.class);
		@SuppressWarnings("unchecked")
		List<Book> list = crit.list();
		EMF.getHttpSession().setAttribute("books", list);
		return new ModelAndView("backoffice", "bookadd", new Book());
	}


	@RequestMapping(
			value = "admin/book/delete/{id}/",
			method = RequestMethod.GET)
	public String addToCart(Model model, @PathVariable("id") int id)
	{
		// TODO : uncomment
		/* if ((WD.getData().getUser().getRight() != WebUser.ADMIN))
		{
			EMF.getHttpSession().setAttribute("lasterror", "Access Denied");
			return "processerror";
		} */

		Criteria c = EMF.getSession().createCriteria(Book.class);
		c.add(Restrictions.eq("id", id));
		Book b = (Book) c.uniqueResult();
		if (b != null)
		{
			System.err.println( "book is not null : " + b.getAuthor().getFirstName());
			try {
				EMF.begin();
				EMF.getSession().delete(b);
				EMF.commit();
			} catch (Exception e) {}
		} else
			System.err.println( "book is null");
		return "redirect:/app/admin/book/";
	}

	@RequestMapping(
			value = "bookadd",
			method = RequestMethod.GET)
	public ModelAndView showContacts() {
		return new ModelAndView("admin", "bookadd", new Book());
	}

	@RequestMapping(value="admin/book/bookaddaction",
			method=RequestMethod.POST)
	public String addContact(@ModelAttribute("book")
	@Valid Book book, BindingResult result, ModelMap m) {
		System.out.println(book.getAuthor());
		if (result.hasErrors()) {
			String errorOutput = "Registration failed : </br>";
			List<ObjectError> e = result.getAllErrors();
			for (ObjectError a : e) {
				errorOutput += a.getObjectName() + " : " + a.getDefaultMessage() + "</br>";
			}
			System.out.println(errorOutput);
			m.addAttribute("lasterror", errorOutput);
			return "redirect:processerror";
		}
		try {
			EMF.begin();
			EMF.save(book);
			EMF.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			m.addAttribute("error", e.getMessage());
		}

		return "redirect:/app/admin/book/";
	}
}
