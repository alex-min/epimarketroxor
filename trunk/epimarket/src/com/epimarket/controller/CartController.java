package com.epimarket.controller;

import java.util.ArrayList;
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
import com.epimarket.entity.Purchase;
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

	@RequestMapping(
			value = "market/cart/checkout/",
			method = RequestMethod.GET)
	public String checkout(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		double	fullPrice = 0;
		if (WD.getData().getUser().getCart().isEmpty() == true)
			return "redirect:/app/market/cart/";
		WD.getData().getUser().setCheckoutAvailable(false);
		List<String>	lNotAvailable = new ArrayList<String>();
		for (Integer idBook : WD.getData().getUser().getCart().keySet()) {
			Book b = (Book) EMF.getSession().get(Book.class.getCanonicalName(), idBook);
			fullPrice += (b.getPrice() * WD.getData().getUser().getCart().get(idBook));
			if (b.getStock() < WD.getData().getUser().getCart().get(idBook))
				lNotAvailable.add(b.getTitle());
		}
		rqst.setAttribute("fullPrice", fullPrice);
		rqst.setAttribute("lNotAvailable", lNotAvailable);
		if (lNotAvailable.isEmpty() == true) {
			System.out.println("GRRRRRRRRROOOOOOOOOOOOSSSSSS");
			WD.getData().getUser().setCheckoutAvailable(true);
		}
		return "checkout";
	}

	@RequestMapping(
			value = "market/cart/checkout/validation",
			method = RequestMethod.GET)
	public String checkoutValidation(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		Purchase	command = new Purchase();

		if (WD.getData().getUser().isCheckoutAvailable() == false) {
			return "redirect:/app/market/cart/checkout/";
		}
		try {
			EMF.begin();
			for (Integer idBook : WD.getData().getUser().getCart().keySet()) {
				Book b = (Book) EMF.getSession().get(Book.class.getCanonicalName(), idBook);
				b.setStock(b.getStock() - WD.getData().getUser().getCart().get(idBook));
				EMF.update(b);
				for (int i = 0; i < WD.getData().getUser().getCart().get(idBook); ++i)
					command.addBook(b);
			}
			//WD.getData().getUser().getUser().addPurchase(command);
			EMF.save(command);
			//EMF.save(WD.getData().getUser().getUser());
			EMF.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "errorCheckout";
		}
		BillGenerator.generate(command);
		WD.getData().getUser().setCheckoutAvailable(false);
		WD.getData().getUser().getCart().clear();
		return "checkoutValidation";
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