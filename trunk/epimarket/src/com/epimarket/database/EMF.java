package com.epimarket.database;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.epimarket.hibernateconf.HibernateUtil;

public final class EMF
{
	//private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("librairie");
	//private static final SessionFactory sessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
	private static  Session			session			= null;
	private static	Transaction		tx				= null;

	private EMF() {}

	public static HttpSession getHttpSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession s = attr.getRequest().getSession(true); // true == allow create
		return (s);
	}

	public static Session getSession() {
		return session;
	}


	//    public static EntityManagerFactory get()
	//    {
	//      //  return emfInstance;
	//    }

	static {
		session	= HibernateUtil.getMySessionFactory().openSession();
		tx = session.beginTransaction();
	}

	public static void		save(Object o) {
		try {
			session.save(o);
		} catch (RuntimeException e) {
			EMF.rollBack();
		}
	}
	
	public static void		update(Object o) {
		try {
			session.update(o);
		} catch (RuntimeException e) {
			EMF.rollBack();
		}
	}

	public static void		begin() {
//		tx = session.beginTransaction();
	}

	public static void		commit() {
		try {
			tx.commit();
		} catch (RuntimeException e) {
			//EMF.rollBack();
		}
	}

	public static void		rollBack() {
		tx.rollback();
	}

}

