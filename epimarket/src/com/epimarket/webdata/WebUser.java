package com.epimarket.webdata;

import java.util.HashMap;

import com.epimarket.entity.User;

public class WebUser {
	public static final int ADMIN = 3;
	User			user = null;
	boolean			logged = false;
	boolean			checkoutAvailable = false;
	java.util.Map<Integer, Integer> cart = new HashMap<Integer, Integer>();//<Integer>	cart = new //ArrayList<Integer>();


	int				right = 0;

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}




	public boolean		isLogged() { return logged; }
	public boolean		isCheckoutAvailable() { return checkoutAvailable; }
	public User			getUser() { return user; }

	public void			setUser(User user) { user = user; }
	public void			setCheckoutAvailable(boolean checkoutAvailable) {
		this.checkoutAvailable = checkoutAvailable;
	}
	public void			setLogged(boolean logged) { logged = logged; }

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
