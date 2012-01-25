package com.epimarket.controller.backoffice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epimarket.database.EMF;
import com.epimarket.entity.User;

@Controller
public class BookAjaxController {

	@RequestMapping(value = "ajax/change/user/{type}/{id}",
			method=RequestMethod.POST)
	public String getList
	(HttpServletRequest rqst, HttpServletResponse resp, Model model,
			@PathVariable("type") String type,
			@PathVariable("id") int id,
			@RequestParam("data") String data
			) {
		Criteria crit = EMF.getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("id", id));
		User user = (User) crit.uniqueResult();

		System.out.println("user : " + user);
		System.out.println("id : " + id);
		try {
			System.out.println("type:" + type);
			if (type.compareTo("rights") == 0) {
				PropertyUtils.setSimpleProperty(user, type,
						Integer.parseInt(data)
						);
			} else {
				PropertyUtils.setSimpleProperty(user, type,
						data
						);
			}
			EMF.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "raw";
	}
}
