package com.epimarket.hibernateconf;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public final class HibernateUtil {

	private static final SessionFactory mySessionFactory;

	static {
		try {
			mySessionFactory = new Configuration().configure("META-INF/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("SessionFactory initialisation failed:" + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns a global SessionFactory object, which can open up new Session's. A Session represents a single-threaded unit of work, the SessionFactory is a thread-safe global object, instantiated once.
	 *
	 * @return the SessionFactory object
	 */
	public static SessionFactory getMySessionFactory() {
		return mySessionFactory;
	}

}