package com.epimarket.webdata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class WD {
	private static  WebData data = null;

	public static WebData getData(HttpServletRequest request) {
		WebData wb = (WebData) request.getSession().getAttribute("webdata");
		if (wb == null)
		{
			wb = new WebData();
			request.getSession().setAttribute("webdata", wb);
		}
		return wb;

	}
	
	public static WebData getData() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession s = attr.getRequest().getSession(true); // true == allow create
		
		WebData wb = (WebData) s.getAttribute("webdata");
		if (wb == null)
		{
			wb = new WebData();
			s.setAttribute("webdata", wb);
		}
		return wb;

	}

}
