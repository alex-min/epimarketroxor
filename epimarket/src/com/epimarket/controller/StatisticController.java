package com.epimarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.database.EMF;
import com.epimarket.entity.Purchase;
import com.epimarket.webdata.WD;



@Controller
@RequestMapping("stat/")
public class StatisticController
{
	@RequestMapping(
			value = "/monthly",
			method = RequestMethod.GET)
	public String monthly(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		Criteria				crit = null;
		DefaultPieDataset		pieDataSet = new DefaultPieDataset();

		for (Integer i = 1; i < 5; ++i) {
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			crit.add(Restrictions.eq("nbArticles", i));
			pieDataSet.setValue(i.toString(), new Integer(crit.list().size()));
		}
		crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
		crit.add(Restrictions.sizeGe("nbArticles", 5));
		pieDataSet.setValue("5 et +", new Integer(crit.list().size()));
		return "displayStat";
	}

	@RequestMapping(
			value = "/weekly",
			method = RequestMethod.GET)
	public String weekly(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		return "displayStat";
	}

	@RequestMapping(
			value = "/daily",
			method = RequestMethod.GET)
	public String daily(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		return "displayStat";
	}
}