package com.epimarket.webdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;

import com.epimarket.entity.User;

public class WebUser {
	User			user = null;
	boolean			logged = false;
	boolean			checkoutAvailable = false;
	java.util.Map<Integer, Integer> cart = new HashMap<Integer, Integer>();//<Integer>	cart = new //ArrayList<Integer>();

	public boolean		isLogged() { return logged; }
	public boolean		isCheckoutAvailable() { return checkoutAvailable; }
	public User			getUser() { return user; }

	public void			setUser(User user) { this.user = user; }
	public void			setCheckoutAvailable(boolean checkoutAvailable) {
		this.checkoutAvailable = checkoutAvailable;
	}
	public void			setLogged(boolean logged) { this.logged = logged; }

	public void			addToCart(Integer id) {
		checkoutAvailable = false;
		if (cart.containsKey(id) == true)
			cart.put(id, cart.get(id) + 1);
		else
			cart.put(id, 1);
	}
	
	public Boolean			removeOneFromCart(Integer id) {
		checkoutAvailable = false;
		Boolean ret = false;
		if (cart.containsKey(id) == true) {
			cart.put(id, cart.get(id) - 1);
			ret = true;
		}
		if (cart.get(id) <= 0)
			cart.remove(id);
		return ret;
	}
	
	public Boolean			removeAllFromCart(Integer id) {
		checkoutAvailable = false;
		return (cart.remove(id) != null);
	}
	
	public java.util.Map<Integer, Integer>	getCart() {
		return cart;
	}
}
