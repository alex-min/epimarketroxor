package com.epimarket.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epimarket.database.EMF;
import com.epimarket.entity.Author;
import com.epimarket.entity.Book;
import com.epimarket.entity.Person;
import com.epimarket.hibernateconf.HibernateUtil;


public class Main {

	public static void main(String[] args) {
		//SessionFactory sessionFactory = HibernateUtil.getMySessionFactory();

		//Session session = sessionFactory.openSession();

		//Transaction tx = session.beginTransaction();

//		Person p = new Person();
//
//		p.setId(1);
//		p.setFirstName("Gandalf");
//		p.setLastName("The Grey");
		
		Author	a = new Author();
		a.setId(1);
		a.setFirstName("Miam");
		a.setLastName("Plop");
		
		Book	b1 = new Book();
		b1.setAuthor(a);
		b1.setId(1);
		b1.setTitle("Glouglou");

		List<Book> lBook = new ArrayList<Book>();
		lBook.add(b1);
		
		a.setListBooks(lBook);
		
		try {
			EMF.save(a);
			EMF.save(b1);
			EMF.commit();
			//session.save(p);
			//tx.commit();
		} catch (RuntimeException e) {
			EMF.rollBack();
			//tx.rollback();
		}

		Object o = null;
		o = EMF.getSession().get("com.epimarket.entity.Book", 1);

		System.out.println(o.toString());
	}

}