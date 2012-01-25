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
import com.epimarket.entity.User;
import com.epimarket.webdata.WD;
import com.epimarket.webdata.WebUser;


@Controller
public class UserBackOffice {
	// REDIRECT admin -> admin/
	/*@RequestMapping(
			value = "admin",
			method = RequestMethod.GET)
	public String redirect(HttpServletRequest rqst, HttpServletResponse resp, Model model) {
		return "redirect:app/admin/";
	}*/

	@RequestMapping(
			value = "admin/user/",
			method = RequestMethod.GET)
	public ModelAndView all()
	{
		WD.getData().getUser().setRight(WebUser.ADMIN);
		if (WD.getData().getUser().getRight() != WebUser.ADMIN)
		{
			EMF.getHttpSession().setAttribute("lasterror", "Access Denied");
			return new ModelAndView("processerror");
		}

		Criteria crit = EMF.getSession().createCriteria(User.class);
		@SuppressWarnings("unchecked")
		List<Book> list = crit.list();
		EMF.getHttpSession().setAttribute("users", list);
		return new ModelAndView("backoffice_user", "useradd", new User());
	}


	@RequestMapping(
			value = "admin/user/delete/{id}/",
			method = RequestMethod.GET)
	public String addToCart(Model model, @PathVariable("id") int id)
	{
		// TODO : uncomment
		/* if ((WD.getData().getUser().getRight() != WebUser.ADMIN))
		{
			EMF.getHttpSession().setAttribute("lasterror", "Access Denied");
			return "processerror";
		} */

		Criteria c = EMF.getSession().createCriteria(User.class);
		c.add(Restrictions.eq("id", id));
		User b = (User) c.uniqueResult();
		if (b != null)
		{
			try {
				EMF.getSession().delete(b);
			} catch (Exception e) {}
		} else { System.out.println("Uset is null"); }
		return "redirect:/app/admin/user/";
	}

	@RequestMapping(
			value = "useradd",
			method = RequestMethod.GET)
	public ModelAndView showContacts() {
		return new ModelAndView("admin", "bookadd", new Book());
	}

	@RequestMapping(value="admin/user/useraddaction",
			method=RequestMethod.POST)
	public String addContact(@ModelAttribute("user")
	@Valid User user, BindingResult result, ModelMap m) {
		System.out.println("user : " + user);
		EMF.getHttpSession().setAttribute("lasterror", "");
		if (result.hasErrors()) {
			String errorOutput = "Registration failed : </br>";
			List<ObjectError> e = result.getAllErrors();
			for (ObjectError a : e) {
				errorOutput += a.getObjectName() + " : " + a.getDefaultMessage() + "</br>";
			}
			EMF.getHttpSession().setAttribute("lasterror", errorOutput);
			return "redirect:/app/admin/user/";
		}
		try {
			EMF.begin();
			EMF.save(user);
			EMF.commit();
		} catch (Exception e) {
			m.addAttribute("error", e.getMessage());
		}
		return "redirect:/app/admin/user/";
	}
}
