package com.epimarket.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;


import com.epimarket.database.EMF;
import com.epimarket.entity.Author;
import com.epimarket.entity.Book;
import com.epimarket.entity.Category;
import com.epimarket.entity.Person;
import com.epimarket.entity.User;


public class Main {

	public static void main(String[] args) {

//		Person p = new Person();
//
//		p.setId(1);
//		p.setFirstName("Gandalf");
//		p.setLastName("The Grey");
		User	u = new User();
		u.setLogin("Toto");
		u.setMail("pdmiam.lol");
		System.out.println("----------->" + u.getMail());

		Category	c = new Category();
		c.setName("Mouhahaha");

		Author	a = new Author();
		a.setId(1);
		a.setFirstName("Miam");
		a.setLastName("Plop");

		Book	b1 = new Book();
		b1.setAuthor(a);
		b1.setId(1);
		b1.setCategory(c);
		b1.setTitle("Glouglou");
		b1.setStock(5);

		List<Book> lBook = new ArrayList<Book>();
		lBook.add(b1);

		a.setListBooks(lBook);

		try {
			EMF.begin();
			EMF.save(a);
			EMF.save(b1);
			EMF.save(c);
			
			EMF.commit();
		} catch (RuntimeException e) {
			EMF.rollBack();
		} catch (Exception e) {
			System.out.println("groooooooossssss");
			e.printStackTrace();
		}
		
		try {
			EMF.begin();
			EMF.save(u);
		} catch (RuntimeException e) {
			EMF.rollBack();
		} catch (Exception e) {
			System.out.println("groooooooossssss");
			e.printStackTrace();
		}

//		Object o = null;
//		o = EMF.getSession().get("com.epimarket.entity.Book", 1);
//
//		System.out.println(o.toString());
	}

}