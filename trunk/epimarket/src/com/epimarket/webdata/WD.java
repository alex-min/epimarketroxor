package com.epimarket.webdata;

import javax.servlet.http.HttpServletRequest;



public class WD {
	private static  WebData data = null;

	public static WebData getData(HttpServletRequest request) {
		WebData wb = (WebData) request.getSession().getAttribute("webdata");
		if (wb == null)
		{
			wb = new WebData();
			request.getSession().setAttribute("webData", wb);
		}
		return wb;

	}

}
