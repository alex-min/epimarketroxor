/*package com.epimarket.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
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

		GregorianCalendar cal = new GregorianCalendar();
		Date	end	= cal.getTime();
		cal.roll(Calendar.MONTH, -6);
		Date	begin = cal.getTime();
		for (Integer i = 1; i < 5; ++i) {
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			crit.add(Restrictions.eq("nbArticles", i));
			crit.add(Restrictions.between("date", begin, end));
			pieDataSet.setValue(i.toString(), new Integer(crit.list().size()));
		}
		crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
		crit.add(Restrictions.sizeGe("nbArticles", 5));
		crit.add(Restrictions.between("date", begin, end));
		pieDataSet.setValue("5 et +", new Integer(crit.list().size()));
		JFreeChart chart = ChartFactory.createPieChart
				("Number book on a command during the last 6 months", // Title
				pieDataSet, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
				ChartUtilities.saveChartAsJPEG(new File("C:\\chart.jpg"), chart, 500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
		}
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
}*/