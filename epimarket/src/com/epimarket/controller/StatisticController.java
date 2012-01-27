package com.epimarket.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.database.EMF;
import com.epimarket.entity.Purchase;

@Controller
@RequestMapping("stat")
public class StatisticController
{
	@RequestMapping(
			value = "/monthly",
			method = RequestMethod.GET)
	public String monthly(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		Criteria				crit		= null;
		DefaultPieDataset		pieDataSet	= new DefaultPieDataset();
		GregorianCalendar		cal			= new GregorianCalendar();
		SimpleDateFormat		fo			= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date	end	= cal.getTime();
		cal.add(Calendar.MONTH, -7);
		Date	begin = cal.getTime();
		//System.out.println("begin:" + begin);
		//System.out.println("end:" + end);
		
		for (Integer i = 1; i < 5; ++i) {
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			crit.add(Restrictions.eq("nbArticles", i));
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?>		l = crit.list();
			//System.out.println("ICCICIIIIIIIIIIIIIIIIIIi =========>" + l.size());
			pieDataSet.setValue(i.toString(), new Integer(l.size()));
			crit.list().clear();
		}
		crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
		crit.add(Restrictions.ge("nbArticles", 5));
		try {
			crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
		} catch (ParseException e) { e.printStackTrace(); }
		pieDataSet.setValue("5 et +", new Integer(crit.list().size()));
		JFreeChart chart = ChartFactory.createPieChart
				("Number book on a command during the last 6 months", // Title
				pieDataSet, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
				ChartUtilities.saveChartAsJPEG(new File("/home/colin_f/chart.jpg"), chart, 500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
		}
		
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		DefaultCategoryDataset ds2 = new DefaultCategoryDataset();
		//cal.add(Calendar.MONTH, -1);
		end = cal.getTime();
		for (Integer i = 6; i >= 0; --i) {
			begin = end;
			cal.add(Calendar.MONTH, 1);
			end = cal.getTime();
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?> l = crit.list();
			ds.setValue(l.size(), "nbCommands", i.toString());
			int sum = 0;
			for (Object elm : l) {
				sum += ((Purchase) elm).getFullPrice();
				//System.out.println("===>" + sum);
			}
			ds2.addValue(sum, "Total sold", i.toString());
		}
		JFreeChart chart2 = ChartFactory.createBarChart("Numbers of command per month",
				"Months ago", "Commands number", ds, PlotOrientation.VERTICAL,
				false, true, false);
		JFreeChart chart3 = ChartFactory.createBarChart("Total of prices per month",
				"Months ago", "Total of commands prices ($)", ds2, PlotOrientation.VERTICAL,
				false, true, false);
		try {
			ChartUtilities.saveChartAsJPEG(new File("/home/colin_f/chart2.jpg"), chart2, 500, 300);
			ChartUtilities.saveChartAsJPEG(new File("/home/colin_f/chart3.jpg"), chart3, 500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
		}
		System.out.println("===>" + System.getProperty("user.dir"));
		return "displayStat";
	}

	@RequestMapping(
			value = "/weekly",
			method = RequestMethod.GET)
	public String weekly(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		Criteria				crit		= null;
		DefaultPieDataset		pieDataSet	= new DefaultPieDataset();
		GregorianCalendar		cal			= new GregorianCalendar();
		SimpleDateFormat		fo			= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date	end	= cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, -8);
		Date	begin = cal.getTime();
		//System.out.println("begin:" + begin);
		//System.out.println("end:" + end);
		
		for (Integer i = 1; i < 5; ++i) {
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			crit.add(Restrictions.eq("nbArticles", i));
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?>		l = crit.list();
			//System.out.println("ICCICIIIIIIIIIIIIIIIIIIi =========>" + l.size());
			pieDataSet.setValue(i.toString(), new Integer(l.size()));
			crit.list().clear();
		}
		crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
		crit.add(Restrictions.ge("nbArticles", 5));
		try {
			crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
		} catch (ParseException e) { e.printStackTrace(); }
		pieDataSet.setValue("5 et +", new Integer(crit.list().size()));
		JFreeChart chart = ChartFactory.createPieChart
				("Number book on a command during the last 6 months", // Title
				pieDataSet, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		try {
				ChartUtilities.saveChartAsJPEG(new File("/home/colin_f/chart.jpg"), chart, 500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
		}
		
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		DefaultCategoryDataset ds2 = new DefaultCategoryDataset();
		end = cal.getTime();
		for (Integer i = 6; i >= 0; --i) {
			begin = end;
			cal.add(Calendar.DAY_OF_WEEK, 1);
			end = cal.getTime();
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?> l = crit.list();
			ds.setValue(l.size(), "nbCommands", i.toString());
			int sum = 0;
			for (Object elm : l) {
				sum += ((Purchase) elm).getFullPrice();
				//System.out.println("===>" + sum);
			}
			ds2.addValue(sum, "Total sold", i.toString());
		}
		JFreeChart chart2 = ChartFactory.createBarChart("Numbers of command per week",
				"Months ago", "Commands number", ds, PlotOrientation.VERTICAL,
				false, true, false);
		JFreeChart chart3 = ChartFactory.createBarChart("Total of prices per week",
				"Months ago", "Total of commands prices ($)", ds2, PlotOrientation.VERTICAL,
				false, true, false);
		try {
			ChartUtilities.saveChartAsJPEG(new File("/home/colin_f/chart2.jpg"), chart2, 500, 300);
			ChartUtilities.saveChartAsJPEG(new File("/home/colin_f/chart3.jpg"), chart3, 500, 300);
		} catch (Exception e) {
			System.out.println("Problem occurred creating chart.");
			e.printStackTrace();
		}

		return "displayStat";
	}

//	@RequestMapping(
//			value = "/daily",
//			method = RequestMethod.GET)
//	public String daily(HttpServletRequest rqst, HttpServletResponse resp, Model model)
//	{
//		return "displayStat";
//	}
}