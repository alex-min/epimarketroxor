package com.epimarket.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.epimarket.database.EMF;
import com.epimarket.entity.Person;
import com.epimarket.hibernateconf.HibernateUtil;


public class Main {

	public static void main(String[] args) {
		//SessionFactory sessionFactory = HibernateUtil.getMySessionFactory();

		//Session session = sessionFactory.openSession();

		//Transaction tx = session.beginTransaction();

		Person p = new Person();

		p.setId(1);
		p.setFirstName("Gandalf");
		p.setLastName("The Grey");

		try {
			EMF.save(p);
			EMF.commit();
			//session.save(p);
			//tx.commit();
		} catch (RuntimeException e) {
			EMF.rollBack();
			//tx.rollback();
		}

		Object o = null;
		o = EMF.getSession().get("com.epimarket.entity.Person", 1);

		System.out.println(o.toString());
	}

}