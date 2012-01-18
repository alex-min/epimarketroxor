package com.epimarket.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.epimarket.database.EMF;
import com.epimarket.entity.Book;
import com.epimarket.webdata.WD;


@Controller
public class CartController
{
	@RequestMapping(
			value = "market/cart",
			method = RequestMethod.GET)
	public String cart(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		System.out.println("cart Page");
		rqst.setAttribute("title", "Cart");
		
		Criteria crit = EMF.getSession().createCriteria(Book.class);
		System.out.println("size cart" + WD.getData().getUser().getCart().size());
		if (WD.getData().getUser().getCart().size() > 0) {
			crit.add(Restrictions.in("id", WD.getData().getUser().getCart().keySet()));
			List<Book> list = crit.list();
			rqst.setAttribute("listCart", list);
			rqst.setAttribute("cartMap", WD.getData().getUser().getCart());
		}
		return "cart";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="market/book/{id}/")
	public String getBookInformation(Model model, @PathVariable("id") int id)
	{
		Object o = null;
		o = EMF.getSession().get("com.epimarket.entity.Book", id);
		model.addAttribute("bookId", o);
		return "book";
	}

	@RequestMapping(method=RequestMethod.GET, value="market/book/{id}/addCart")
	public String addToCart(Model model, @PathVariable("id") int id)
	{
		Object o = null;
		o = EMF.getSession().get("com.epimarket.entity.Book", id);
		model.addAttribute("bookId", o);
		model.addAttribute("added", true);
		
		WD.getData().getUser().addToCart(id);
		
		return "book";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="market/cart/removeOne/{id}")
	public String removeOne(Model model, @PathVariable("id") int id)
	{
		WD.getData().getUser().removeOneFromCart(id);
		
		return "redirect:/app/market/cart/";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="market/cart/removeAll/{id}")
	public String removeAll(Model model, @PathVariable("id") int id)
	{
		WD.getData().getUser().removeAllFromCart(id);
		
		return "redirect:/app/market/cart/";
	}

}