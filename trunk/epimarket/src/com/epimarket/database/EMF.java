package com.epimarket.database;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;


public final class EMF
{
	private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("librairie");
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	private static  boolean sessionOpened = false;
	private static  Session session = null;
	
    private EMF() {}

    public static EntityManagerFactory get()
    {
        return emfInstance;
    }
    
    public static void persist(Object o) {
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		em.getTransaction().commit();
		em.close();
    }
 
    public static void save(Object o) {
		if (!sessionOpened)
		{
			session = sessionFactory.openSession();
			sessionOpened = true;
		}
		session.save(o);
    }
}

